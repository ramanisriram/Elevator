package com.sriram1113.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.sriram1113.main.core.ScheduledElevator;

public class TestElevatorScheduling {

	ScheduledElevator controller = null;

	@Before
	public void init() {
		controller = new ScheduledElevator();
	}

	@Test
	public void testSchedule1() {
		int start_floor = 3;
		String movement_plan = "[DOWN_1,OPEN_DOOR,CLOSE_DOOR][UP_1,UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR]";
		String datetime = "2020-02-26 21:18:30";
		int timeoutToWait = 30;
		try {
			assertEquals(5, controller.scheduleElevatorForExecution(start_floor, movement_plan, datetime, timeoutToWait));
		} catch (InterruptedException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}