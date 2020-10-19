package com.babysitter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.babysitter.exception.BabySitterException;
import com.babysitter.service.Payment;

@SpringBootTest
class BabySitterApplicationTests {

	@Test
	void contextLoads() {
	}
	
	Payment payment;
	private static final double DELTA = 0;
	
	//I would recommend changing value here for calculating charge
	@Test
	public void testCalculateCharge() throws BabySitterException {
		payment = new Payment();
		assertEquals(132, payment.calculateCharge("05:00 PM","08:00 PM", "04:00 AM", true), DELTA);
	}
	
	@Test
	public void testCalculateCharge2() throws BabySitterException {
		payment = new Payment();
		assertEquals(112, payment.calculateCharge("06:00 PM","10:00 PM", "03:00 AM", true), DELTA);
	}
	
	@Test
	public void testCalculateChargeStartTime() throws BabySitterException {
		payment = new Payment();
		assertThrows(BabySitterException.class, () -> payment.calculateCharge("03:00 PM","10:00 PM", "03:00 AM", true));
	}

	@Test//
	public void testCalculateChargeEndTime() throws BabySitterException {
		payment = new Payment();
		assertThrows(BabySitterException.class, () -> payment.calculateCharge("05:00 PM","10:00 PM", "05:00 AM", true));
	}
	
	@Test
	public void testParseToMilitary() {
		payment = new Payment();
		LocalTime expected = LocalTime.parse("17:00");
		assertEquals(expected, parseToMilitary("05:00 PM"));
	}
	
	@Test
	public void testValidation() throws BabySitterException {
		payment = new Payment();
		LocalTime st = LocalTime.parse("17:00");
		LocalTime bt = LocalTime.parse("18:00");
		LocalTime et = LocalTime.parse("04:00");
		
		assertEquals(true, payment.validateInput(st, bt, et));
	}
	
	public LocalTime parseToMilitary(String time) {
		String localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a"))
				.format(DateTimeFormatter.ofPattern("HH:mm"));
		return LocalTime.parse(localTime);
	}

}
