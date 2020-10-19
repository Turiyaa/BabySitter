package com.babysitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.babysitter.exception.BabySitterException;
import com.babysitter.service.Payment;

@Controller
public class PaymentController {
	
	@Autowired
	Payment payment;

	@GetMapping("/")
	public String greetingForm(Model model) throws BabySitterException {
		model.addAttribute("hour", new Hour());
		return "hour";
	}

	@PostMapping("/hour")
	public String greetingSubmit(@ModelAttribute Hour hour, Model model) throws BabySitterException {
		model.addAttribute("hour", hour);
		Hour.totalCharge = payment.calculateCharge(hour.startHour, hour.bedTimeHour, hour.midnightHour, false);
		return "charge";
	}

	static class Hour {
		String startHour;
		String bedTimeHour;
		String midnightHour;
		public static double totalCharge;

		public String getStartHour() {
			return startHour;
		}

		public void setStartHour(String startHour) {
			this.startHour = startHour;
		}

		public String getBedTimeHour() {
			return bedTimeHour;
		}

		public void setBedTimeHour(String bedTimeHour) {
			this.bedTimeHour = bedTimeHour;
		}

		public String getMidnightHour() {
			return midnightHour;
		}

		public void setMidnightHour(String midnightHour) {
			this.midnightHour = midnightHour;
		}

	}

}
