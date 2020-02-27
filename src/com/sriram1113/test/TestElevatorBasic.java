package com.sriram1113.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sriram1113.main.core.BasicElevator;


public class TestElevatorBasic {

	BasicElevator obj = null;

	@Before
	public void initialize() {
		obj = new BasicElevator();
	}

	@Test
	public void testUnderGroundFloors() {
		int[] floors = { -1 };
		String[] directions = { "DOWN" };
		assertEquals("Invalid Floor", obj.instructElevator(1, 0, floors, directions));
	}

	@Test
	public void testAboveTopFloors() {
		int[] floors = { 6 };
		String[] directions = { "UP" };
		assertEquals("Invalid Floor", obj.instructElevator(1, 0, floors, directions));
	}

	@Test
	public void testOnePersonInputUpstairs() {
		int[] floors = { 3 };
		String[] directions = { "UP" };
		assertEquals("[UP_1,UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR]", obj.instructElevator(1, 0, floors, directions));
	}
	
	@Test
	public void testOnePersonInputDownstairs() {
		int[] floors = { 1 };
		String[] directions = { "DOWN" };
		assertEquals("[DOWN_1,DOWN_1,DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR]", obj.instructElevator(1, 5, floors, directions));
	}

}