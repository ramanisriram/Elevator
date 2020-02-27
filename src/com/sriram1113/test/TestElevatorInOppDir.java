package com.sriram1113.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sriram1113.main.core.BasicElevator;

public class TestElevatorInOppDir {

	BasicElevator obj = null;

	@Before
	public void init() {
		obj = new BasicElevator();
	}

	// multiple people select floors in opposite direction
	// Moving downwards
	// starting from 1st floor
	@Test
	public void testCase1() {
		int[] floors = { 0, 3, 5 };
		String[] directions = { "DOWN", "UP", "UP" };
		// System.out.println(obj.instructElevator(3, 1, floors, directions));
		assertEquals(
				"[DOWN_1,OPEN_DOOR,CLOSE_DOOR][UP_1,UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(3, 1, floors, directions));
	}

	// starting from 2nd floor
	@Test
	public void testCase2() {
		int[] floors = { 0, 1, 4, 5 };
		String[] directions = { "DOWN", "DOWN", "UP", "UP" };
		// System.out.println(obj.instructElevator(4, 2, floors, directions));
		assertEquals(
				"[DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR][UP_1,UP_1,UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(4, 2, floors, directions));
	}

	// starting from 3rd floor
	@Test
	public void testCase3() {
		int[] floors = { 1, 0, 5, 4, 2 };
		String[] directions = { "DOWN", "DOWN", "UP", "UP", "DOWN" };
		// System.out.println(obj.instructElevator(5, 3, floors, directions));
		assertEquals(
				"[DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR][UP_1,UP_1,UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(5, 3, floors, directions));
	}

	// starting from 4th floor
	@Test
	public void testCase4() {
		int[] floors = { 3, 0, 2, 5 };
		String[] directions = { "DOWN", "DOWN", "DOWN", "UP" };
		// System.out.println(obj.instructElevator(4, 4, floors, directions));
		assertEquals(
				"[DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR][UP_1,UP_1,UP_1,UP_1,UP_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(4, 4, floors, directions));
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Moving upwards
	// starting from 4th floor
	@Test
	public void testCase5() {
		int[] floors = { 5, 1, 3 };
		String[] directions = { "UP", "DOWN", "DOWN" };
		// System.out.println(obj.instructElevator(3, 4, floors, directions));
		assertEquals(
				"[UP_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(3, 4, floors, directions));
	}

	// starting from 3rd floor
	@Test
	public void testCase6() {
		int[] floors = { 5, 4, 2, 0 };
		String[] directions = { "UP", "UP", "DOWN", "DOWN" };
		// System.out.println(obj.instructElevator(4, 3, floors, directions));
		assertEquals(
				"[UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(4, 3, floors, directions));
	}

	// starting from 2nd floor
	@Test
	public void testCase7() {
		int[] floors = { 4, 3, 1, 0 };
		String[] directions = { "UP", "UP", "DOWN", "DOWN" };
		// System.out.println(obj.instructElevator(4, 2, floors, directions));
		assertEquals(
				"[UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(4, 2, floors, directions));
	}

	// starting from 1st floor
	@Test
	public void testCase8() {
		int[] floors = { 3, 4, 5, 2, 0 };
		String[] directions = { "UP", "UP", "UP", "DOWN", "DOWN" };
		// System.out.println(obj.instructElevator(5, 1, floors, directions));
		assertEquals(
				"[UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR][UP_1,OPEN_DOOR,CLOSE_DOOR][DOWN_1,DOWN_1,DOWN_1,DOWN_1,DOWN_1,OPEN_DOOR,CLOSE_DOOR]",
				obj.instructElevator(5, 1, floors, directions));
	}

}