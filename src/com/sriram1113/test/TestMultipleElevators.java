package com.sriram1113.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sriram1113.main.api.Elevator;
import com.sriram1113.main.core.ElevatorController;
import com.sriram1113.main.core.ElevatorMonitor;

public class TestMultipleElevators {
	
	ElevatorMonitor monitor = null;
	ElevatorController controller = null;
	
	@Before
	public void init() {
		controller = new ElevatorController();
		monitor = new ElevatorMonitor();
	}
	
	//Case1 - Allocation is random if both elevators are at same floors
	/* Example
	 * Elevator1 is at floor 2. Elevator2 is also at floor 2. A call from floor 5 will be allocated to either randomly.
	 */
	@Test
	public void testElevatorAllocationCase1() {
		Elevator elevator1 = new Elevator("Elevator1");
		Elevator elevator2 = new Elevator("Elevator2");
		monitor.addElevatorTobeMoitored(elevator1);
		monitor.addElevatorTobeMoitored(elevator2);
		controller.callElevatorAndMoveTo(elevator1, 0, 2);
		controller.callElevatorAndMoveTo(elevator2, 0, 2);
		int start_floor = 5;
		int target_floor = 3;
		controller.serveCallAtFloor(monitor, start_floor,target_floor);
		assertTrue(monitor.getElevatorFloorStatus("Elevator1") == 5 || monitor.getElevatorFloorStatus("Elevator2") == 5);
	}
	
	//Case1 - Allocation is random if both elevators are at equal distance from the requested floor to start.
	/* Example
	 * Elevator1 is at floor 4. Elevator2 is at floor 2. A call from floor 3 will be allocated to either randomly.
	 */
	@Test
	public void testElevatorAllocationCase2() {
		Elevator elevator1 = new Elevator("Elevator1");
		Elevator elevator2 = new Elevator("Elevator2");
		monitor.addElevatorTobeMoitored(elevator1);
		monitor.addElevatorTobeMoitored(elevator2);
		controller.callElevatorAndMoveTo(elevator1, 0, 4);
		controller.callElevatorAndMoveTo(elevator2, 5, 2);
		int start_floor = 3;
		int target_floor = 1;
		controller.serveCallAtFloor(monitor, start_floor,target_floor);
		assertTrue(monitor.getElevatorFloorStatus("Elevator1") == 3 || monitor.getElevatorFloorStatus("Elevator2") == 3);
	}
	
	//Case2 - Allocation is based on nearby elevator if they are not in same floors / at equal distance.
	/* Example
	 * Elevator1 is at floor 1. Elevator2 is at floor 4. A call from floor 2 will be allocated to elevator 1 because its nearby.
	 */
	@Test
	public void testElevatorAllocationCase3() {
		Elevator elevator1 = new Elevator("Elevator1");
		Elevator elevator2 = new Elevator("Elevator2");
		monitor.addElevatorTobeMoitored(elevator1);
		monitor.addElevatorTobeMoitored(elevator2);
		controller.callElevatorAndMoveTo(elevator1, 0, 1);
		controller.callElevatorAndMoveTo(elevator2, 5, 4);
		int start_floor = 2;
		int target_floor = 0;
		controller.serveCallAtFloor(monitor, start_floor,target_floor);
		assertTrue(monitor.getElevatorFloorStatus("Elevator1") == 2);
	}
	
	//Case3 - Allocation is based on nearby elevator if they are not in same floors / at equal distance.
	/* Example
	 * Elevator1 is at floor 1. Elevator2 is at floor 4. A call from floor 0 will be allocated to elevator 1 because its nearby.
	 */
	@Test
	public void testElevatorAllocationCase4() {
		Elevator elevator1 = new Elevator("Elevator1");
		Elevator elevator2 = new Elevator("Elevator2");
		monitor.addElevatorTobeMoitored(elevator1);
		monitor.addElevatorTobeMoitored(elevator2);
		controller.callElevatorAndMoveTo(elevator1, 0, 1);
		controller.callElevatorAndMoveTo(elevator2, 5, 4);
		int start_floor = 0;
		int target_floor = 5;
		controller.serveCallAtFloor(monitor, start_floor,target_floor);
		assertTrue(monitor.getElevatorFloorStatus("Elevator1") == 0);
	}
	
	//Case3 - Allocation is based on nearby elevator if they are not in same floors / at equal distance.
	/* Example
	 * Elevator1 is at floor 1. Elevator2 is at floor 4. A call from floor 3 will be allocated to elevator 2 because its nearby.
	 */
	@Test
	public void testElevatorAllocationCase5() {
		Elevator elevator1 = new Elevator("Elevator1");
		Elevator elevator2 = new Elevator("Elevator2");
		monitor.addElevatorTobeMoitored(elevator1);
		monitor.addElevatorTobeMoitored(elevator2);
		controller.callElevatorAndMoveTo(elevator1, 0, 1);
		controller.callElevatorAndMoveTo(elevator2, 5, 4);
		int start_floor = 3;
		int target_floor = 0;
		controller.serveCallAtFloor(monitor, start_floor,target_floor);
		assertTrue(monitor.getElevatorFloorStatus("Elevator2") == 3);
	}
	
	//Case3 - Allocation is based on nearby elevator if they are not in same floors / at equal distance.
	/* Example
	 * Elevator1 is at floor 1. Elevator2 is at floor 4. A call from floor 5 will be allocated to elevator 2 because its nearby.
	 */
	@Test
	public void testElevatorAllocationCase6() {
		Elevator elevator1 = new Elevator("Elevator1");
		Elevator elevator2 = new Elevator("Elevator2");
		monitor.addElevatorTobeMoitored(elevator1);
		monitor.addElevatorTobeMoitored(elevator2);
		controller.callElevatorAndMoveTo(elevator1, 0, 1);
		controller.callElevatorAndMoveTo(elevator2, 5, 4);
		int start_floor = 5;
		int target_floor = 3;
		controller.serveCallAtFloor(monitor, start_floor,target_floor);
		assertTrue(monitor.getElevatorFloorStatus("Elevator2") == 5);
	}


}