package com.babysitter.entity;

public class Babysitter {
	private int startHour;
	private int bedTimeHour;
	private int midnightHour;
	private double charge;

	public Babysitter() {

	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getBedTimeHour() {
		return bedTimeHour;
	}

	public void setBedTimeHour(int bedTimeHour) {
		this.bedTimeHour = bedTimeHour;
	}

	public int getMidnightHour() {
		return midnightHour;
	}

	public void setMidnightHour(int midnightHour) {
		this.midnightHour = midnightHour;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}
	
}
