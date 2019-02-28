package com.seating.plan.generator.system.resp.mob.view;

public class CityRespView {

	private int id;
	private String name;
	private int pincode;
	private StateRespView state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public StateRespView getState() {
		return state;
	}

	public void setState(StateRespView state) {
		this.state = state;
	}

}