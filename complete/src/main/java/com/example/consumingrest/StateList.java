package com.example.consumingrest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StateList {

	private double time;
	private List<String[]> states;
	
	public double getTime() {
		return time;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public List<String[]> getStates() {
		return states;
	}
	
	public void setStates(List<String[]> states) {
		this.states = states;
	}
	
	
	@Override
	public String toString() {
		return "State{" +
				"time=" + time +
				", states=" + states.get(0)[0] +
				'}';
	}
}
