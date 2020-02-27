package com.sriram1113.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sriram1113.main.api.Elevator;
import com.sriram1113.main.core.ElevatorMonitor;
import com.sriram1113.main.core.ElevatorController;

public class TestElevatorMovement {

	ElevatorMonitor monitor = null;
	ElevatorController controller = null;
	Elevator elevator = null;

	@Before
	public void init() {
		controller = new ElevatorController();
		elevator = new Elevator("Elevator1");
		monitor = new ElevatorMonitor();
	}

	@Test
	public void testCase1() {
		int call_floor = 3;
		int go_to_floors = 5;
		controller.callElevatorAndMoveTo(elevator, call_floor, go_to_floors);
		monitor.addElevatorTobeMoitored(elevator);
		assertEquals(5, monitor.getElevatorFloorStatus("Elevator1"));
	}

	@Test
	public void testCase2() {
		controller.setElevatorDoorStatus(elevator, "OPEN_DOOR");
		monitor.addElevatorTobeMoitored(elevator);
		assertEquals("OPEN_DOOR", monitor.getElevatorDoorStatus("Elevator1"));
	}

	@Test
	public void testCase3() {
		int call_floor = 5;
		int go_to_floors = 1;
		controller.moveElevatorInSaidDirection(elevator, call_floor, go_to_floors);
		monitor.addElevatorTobeMoitored(elevator);
		assertEquals("DOWN", monitor.getElevatorCurrentDirection("Elevator1"));
	}

}