package com.babysitter.constant;

import java.time.LocalTime;

public interface BabysitterConst {
	double STARTTIME_PAY = 12;
	double BEDTIME_PAY = 8;
	double MIDNIGHT_PAY = 16;
	LocalTime START_TIME = LocalTime.parse("17:00");
	LocalTime END_TIME = LocalTime.parse("04:00");
}
