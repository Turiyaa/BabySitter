package com.babysitter.service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.babysitter.constant.BabysitterConst;
import com.babysitter.entity.Babysitter;
import com.babysitter.exception.BabySitterException;

@Service
public class Payment {
	Babysitter babysitter;

	public double calculateCharge(String startTime, String bedTime, String endTime, boolean isTest) throws BabySitterException {
		double charge = 0;
		LocalTime st,bt,et;
		
		if(isTest) {
			st = parseToMilitary(startTime);
			bt = parseToMilitary(bedTime);
			et = parseToMilitary(endTime);			
		}else {
			st = LocalTime.parse(startTime);
			bt = LocalTime.parse(bedTime);
			et = LocalTime.parse(endTime);
		}

		boolean isValid = validateInput(st, bt, et);

		if (isValid) {

			babysitter = new Babysitter();

			Duration start_time_hours = Duration.between(st, bt);
			Duration bed_time_hours = Duration.between(bt, (LocalTime.MAX));
			Duration midnight_time_hours = Duration.between(LocalTime.MIDNIGHT, et);

			babysitter.setStartHour((int) start_time_hours.toHours());
			babysitter.setBedTimeHour((int) bed_time_hours.toHours() + 1); // Adding 1 to fix lost hour
			babysitter.setMidnightHour((int) midnight_time_hours.toHours());

			charge = calculateCharge(charge);

		}

		return charge;

	}

	private double calculateCharge(double charge) {
		charge = babysitter.getStartHour() * BabysitterConst.STARTTIME_PAY
				+ babysitter.getBedTimeHour() * BabysitterConst.BEDTIME_PAY
				+ babysitter.getMidnightHour() * BabysitterConst.MIDNIGHT_PAY;
		return charge;
	}

	public boolean validateInput(LocalTime st, LocalTime bt, LocalTime et) throws BabySitterException {

		if (st.isBefore(BabysitterConst.START_TIME)) {
			throw new BabySitterException("Start time can't be earlier then 5:00 PM");
		}
		if (st.isAfter(bt)) {
			throw new BabySitterException("Start time can't be after bedtime");
		}
		if (et.isAfter(BabysitterConst.END_TIME)) {
			throw new BabySitterException("End time can't be after 4:00AM");
		}
		return true;
	}

	public LocalTime parseToMilitary(String time) {
		String localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a"))
				.format(DateTimeFormatter.ofPattern("HH:mm"));
		return LocalTime.parse(localTime);
	}

}
