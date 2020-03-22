package com.example.consumingrest;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Component
@RestController
public class ArrivalController {

	@Autowired
	private ArrivalRepository arrivalRepository;

	@Autowired
	KafkaProducer producer;

	@Autowired
	MessageStorage storage;


	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	@Scheduled(fixedRate = 10000)
	public void getArrivalInfo() {
		log.info("Fetching updated info....");
		List<Arrival> arrivals = getArrivals();
		for(int i=0;i<arrivals.size();i++) {
			producers("Arrival "+ arrivals.get(i).getIcao24()  +" added");
			arrivalRepository.save(arrivals.get(i));
			}
		log.info("UPDATE :" + arrivals);
	}

	static RestTemplate restTemplate = new RestTemplate();

	public List<Arrival> getArrivals(){
		ResponseEntity<List<Arrival>> responses = restTemplate.exchange(
				"https://USERNAME:PASSWORD@opensky-network.org/api/flights/departure?airport=EDDF&begin=1517230000&end=1517230800",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Arrival>>(){});
		List<Arrival> arrivals = responses.getBody();

		return arrivals;

	}

	@RequestMapping(value="/arrivals",  method = RequestMethod.GET)
	public String Arrivals () {
		List<Arrival> arrival = getArrivals();
		
		String html = "<table>\n" + 
				"  <tr>\n" + 
				"    <th>PlaneID </th>\n" + 
				"    <th>Callsign </th>\n" + 
				"    <th>Departure Airport </th>\n" + 
				"    <th>Arrival Airport </th>\n" +
				"  </tr>\n" ;
		for(int i=0;i<arrival.size();i++) {

			html += "<tr>\n" +
					"<td>" + arrival.get(i).getIcao24() + "</td>" +
					"<td>" + arrival.get(i).getCallsign() + "</td>" +
					"<td>" + arrival.get(i).getEstDepartureAirport() + "</td>" +
					"<td>" + arrival.get(i).getEstArrivalAirport() + "</td>" +
					"</tr>\n";	
		}
		html += "</table>";


		return html;	
	}

	@RequestMapping(value="/stored_arrivals",  method = RequestMethod.GET)
	public String StoredArrivals() {

		List<Arrival> arrival = new ArrayList<Arrival>();
		arrivalRepository.findAll().forEach(arrival::add);
		
		String html = "<table>\n" + 
				"  <tr>\n" + 
				"    <th>PlaneID </th>\n" + 
				"    <th>Callsign </th>\n" + 
				"    <th>Departure Airport </th>\n" + 
				"    <th>Arrival Airport </th>\n" +
				"  </tr>\n" ;
		for(int i=0;i<arrival.size();i++) {

			html += "<tr>\n" +
					"<td>" + arrival.get(i).getIcao24() + "</td>" +
					"<td>" + arrival.get(i).getCallsign() + "</td>" +
					"<td>" + arrival.get(i).getEstDepartureAirport() + "</td>" +
					"<td>" + arrival.get(i).getEstArrivalAirport() + "</td>" +
					"</tr>\n";	
		}
		html += "</table>";


		return html;	
	}

	@RequestMapping(value="/consumer",  method = RequestMethod.GET)
	public String getAllRecievedMessage(){
	  String messages = storage.toString();
		String html ="<table>\n <tr>\n <th> Arrival Additions PlaneID </th>\n  </tr>\n";
			html += "<tr>\n" +"<td>" + messages + "</td>" +	"</tr>\n";
			html += "</table>";
		return html;	
	}	

	public void producers(String data){
		producer.send(data);
		storage.put(data);
	}

}