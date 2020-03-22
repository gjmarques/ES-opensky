package com.example.consumingrest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="arrival")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrival {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	
	private String icao24;
	private int firstSeen;
	private String estDepartureAirport;
	private int lastSeen;
	private String estArrivalAirport;
	private String callsign;
	private int estDepartureAirportHorizDistance;
	private int estDepartureAirportVertDistance;
	private int estArrivalAirportHorizDistance;
	private int estArrivalAirportVertDistance;
	private int departureAirportCandidatesCount;
	private int arrivalAirportCandidatesCount;
	
	public String getIcao24() {
		return icao24;
	}
	public void setIcao24(String icao24) {
		this.icao24 = icao24;
	}
	public int getFirstSeen() {
		return firstSeen;
	}
	public void setFirstSeen(int firstSeen) {
		this.firstSeen = firstSeen;
	}
	public String getEstDepartureAirport() {
		return estDepartureAirport;
	}
	public void setEstDepartureAirport(String estDepartureAirport) {
		this.estDepartureAirport = estDepartureAirport;
	}
	public int getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(int lastSeen) {
		this.lastSeen = lastSeen;
	}
	public String getCallsign() {
		return callsign;
	}
	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}
	public String getEstArrivalAirport() {
		return estArrivalAirport;
	}
	public void setEstArrivalAirport(String estArrivalAirport) {
		this.estArrivalAirport = estArrivalAirport;
	}
	public int getEstDepartureAirportHorizDistance() {
		return estDepartureAirportHorizDistance;
	}
	public void setEstDepartureAirportHorizDistance(int estDepartureAirportHorizDistance) {
		this.estDepartureAirportHorizDistance = estDepartureAirportHorizDistance;
	}
	public int getEstDepartureAirportVertDistance() {
		return estDepartureAirportVertDistance;
	}
	public void setEstDepartureAirportVertDistance(int estDepartureAirportVertDistance) {
		this.estDepartureAirportVertDistance = estDepartureAirportVertDistance;
	}
	public int getEstArrivalAirportHorizDistance() {
		return estArrivalAirportHorizDistance;
	}
	public void setEstArrivalAirportHorizDistance(int estArrivalAirportHorizDistance) {
		this.estArrivalAirportHorizDistance = estArrivalAirportHorizDistance;
	}
	public int getEstArrivalAirportVertDistance() {
		return estArrivalAirportVertDistance;
	}
	public void setEstArrivalAirportVertDistance(int estArrivalAirportVertDistance) {
		this.estArrivalAirportVertDistance = estArrivalAirportVertDistance;
	}
	public int getDepartureAirportCandidatesCount() {
		return departureAirportCandidatesCount;
	}
	public void setDepartureAirportCandidatesCount(int departureAirportCandidatesCount) {
		this.departureAirportCandidatesCount = departureAirportCandidatesCount;
	}
	public int getArrivalAirportCandidatesCount() {
		return arrivalAirportCandidatesCount;
	}
	public void setArrivalAirportCandidatesCount(int arrivalAirportCandidatesCount) {
		this.arrivalAirportCandidatesCount = arrivalAirportCandidatesCount;
	}
	
}
