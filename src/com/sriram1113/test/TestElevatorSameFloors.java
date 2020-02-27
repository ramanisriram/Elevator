package com.sriram1113.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sriram1113.main.core.BasicElevator;

public class TestElevatorSameFloors {

	BasicElevator obj = null;

	@Before
	public void init() {
		obj = new BasicElevator();
	}

	// multiple people select same floors
	// Cases 1 - 5 are for moving upwards
	// starting from 0th floor
	@Test
	public void testMultiplePplCase1() {
		int[] floors = { 5, 1, 5, 4, 3, 3, 2 };
		String[] directions = { "UP" };
		// System.out.println(obj.instructElevator(5, 0, floors, directions));
		assertEquals(
				"[UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(5, 0, floors, directions));
	}

	// starting from 1st floor
	@Test
	public void testMultiplePplCase2() {
		int[] floors = { 5, 2, 4, 2, 5 };
		String[] directions = { "UP" };
		// System.out.println(obj.instructElevator(3, 1, floors, directions));
		assertEquals("[UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(3, 1, floors, directions));
	}

	// starting from 2nd floor
	@Test
	public void testMultiplePplCase3() {
		int[] floors = { 5, 3, 3, 5 };
		String[] directions = { "UP" };
		// System.out.println(obj.instructElevator(3, 1, floors, directions));
		assertEquals("[UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(2, 2, floors, directions));
	}

	// starting from 3rd floor
	@Test
	public void testMultiplePplCase4() {
		int[] floors = { 5, 4, 4 };
		// System.out.println(obj.instructElevator(3, 1, floors, directions));
		String[] directions = { "UP" };
		assertEquals("[UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(2, 3, floors, directions));
	}

	// starting from 4th floor
	@Test
	public void testMultiplePplCase5() {
		int[] floors = { 5 };
		// System.out.println(obj.instr5uctElevator(3, 1, floors, directions));
		String[] directions = { "UP" };
		assertEquals("[UP_1,OPEN_DOOR,CLOSE_DOOR]", obj.instructElevator(1, 4, floors, directions));
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Cases 6-10 are for moving downwards
	// starting from 5th floor
	@Test
	public void testMultiplePplCase6() {
		int[] floors = { 2, 1, 4, 3, 1, 4, 2, 1 };
		// System.out.println(obj.instructElevator(4, 0, floors, directions));
		String[] directions = { "DOWN" };
		assertEquals(
				"[DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(4, 5, floors, directions));
	}

	// starting from 4st floor
	@Test
	public void testMultiplePplCase7() {
		int[] floors = { 3, 0, 1, 0, 1, 3 };
		// System.out.println(obj.instructElevator(3, 4, floors, directions));
		String[] directions = { "DOWN" };
		assertEquals("[DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(3, 4, floors, directions));
	}

	// starting from 3rd floor
	@Test
	public void testMultiplePplCase8() {
		int[] floors = { 0, 2, 0 };
		// System.out.println(obj.instructElevator(2, 3, floors, directions));
		String[] directions = { "DOWN" };
		assertEquals("[DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(2, 3, floors, directions));
	}

	// starting from 2nd floor
	@Test
	public void testMultiplePplCase9() {
		int[] floors = { 0, 1, 1, 0, 1 };
		// System.out.println(obj.instructElevator(2, 2, floors, directions));
		String[] directions = { "DOWN" };
		assertEquals("[DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(2, 2, floors, directions));
	}

	// starting from 1th floor
	@Test
	public void testMultiplePplCase10() {
		int[] floors = { 0 };
		// System.out.println(obj.instructElevator(1, 1, floors, directions));
		String[] directions = { "DOWN" };
		assertEquals("[DOWN_1,OPEN_DOOR,CLOSE_DOOR]", obj.instructElevator(1, 1, floors, directions));
	}

}