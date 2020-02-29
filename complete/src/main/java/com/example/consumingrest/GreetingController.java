package com.example.consumingrest;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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


	static RestTemplate restTemplate = new RestTemplate();
	@RequestMapping(value="/state",  method = RequestMethod.GET)
	public String Teams () {
		ResponseEntity<List<Arrival>> responses = restTemplate.exchange(
				"https://USERNAME:PASSWORD@opensky-network.org/api/flights/departure?airport=EDDF&begin=1517230000&end=1517230800",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Arrival>>(){});
		List<Arrival> arrival = responses.getBody();
		System.out.print("RESPONSES :" + responses.getBody());
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