package com.example.consumingrest;
import java.text.SimpleDateFormat;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Component
@RestController
public class GreetingController {
	@Autowired
	private UserRepository userRepository;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	@Scheduled(fixedRate = 5000)
	public void getArrivalInfo() {
		log.info("Fetching updated info....");
		List<Arrival> arrivals = getArrivals();
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
		for(int i=0;i<arrivals.size();i++) {
		userRepository.save(arrivals.get(i));
		}
		return arrivals;

	}

	@RequestMapping(value="/state",  method = RequestMethod.GET)
	public String Teams () {
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

}