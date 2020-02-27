package com.sriram1113.main.core;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.sriram1113.main.api.Elevator;
import com.sriram1113.main.services.ControllerServices;
import com.sriram1113.main.services.MonitorServices;

/**
 * This is the controller class which will control the elevator to move in required direction
 * or the floor. This class solves the requirements related to controls of the elevator.
 *
 */
public class ElevatorController implements ControllerServices {
	
	private static final Logger LOG = Logger.getLogger("ElevatorController");
	
	
	/**
	 * This function is to control the elevator to the said start and end / target floor the elevator supposed to be after moving.
	 * 
	 * @param elevator
	 * @param call_floor
	 * @param go_to_floors
	 */
	public void callElevatorAndMoveTo(Elevator elevator, int call_floor, int go_to_floors) {
		requireNonNull(elevator, "Elevator");
		elevator.setStart_floor(call_floor);
		elevator.setCurrent_floor(go_to_floors);
	}
	
	/**
	 * This function is said to control the elevator to the said direction. it sets the current floor and start floor
	 * values in the object
	 * 
	 * @param elevator
	 * @param call_floor
	 * @param go_to_floors
	 */
	public void moveElevatorInSaidDirection(Elevator elevator, int call_floor, int go_to_floors) {
		requireNonNull(elevator, "Elevator");
		elevator.setStart_floor(call_floor);
		elevator.setCurrent_floor(go_to_floors);
		if(go_to_floors > call_floor) {
			elevator.setDirection("UP");
		} else if (go_to_floors < call_floor) {
			elevator.setDirection("DOWN");
		} else {
			throw new IllegalArgumentException("Elevator is not moving anywhere");
		}
	}

	/**
	 * This function is to control the door status of the elevator
	 * 
	 * @param elevator
	 * @param door_status
	 */
	public void setElevatorDoorStatus(Elevator elevator, String door_status) {
		requireNonNull(elevator, "Elevator");
		if(!("OPEN_DOOR".equals(door_status)) && !("CLOSE_DOOR".equals(door_status))) {
			throw new IllegalArgumentException("Invalid Door status input for the elevator");
		}
		elevator.setDoor_status(door_status);		
	}

	/**
	 * This function is to serve a specific request to an elevator based on certain parameters.
	 * If all elevators are in same floor, then random allocation happens.
	 * If all nearby elevators are at equal distance from the new requested floor of call, then still
	 * random allocation happens.
	 * Otherwise whichever elevator is nearby to the the newly requested floor that elevator will serve the call.
	 * 
	 * @param monitor
	 * @param start_floor
	 * @param target_floor
	 */
	public void serveCallAtFloor(ElevatorMonitor monitor, int start_floor, int target_floor) {
		
		requireNonNull(monitor, "Monitor");
		Map<Integer, List<Elevator>> mapbasedOnCurrFloor = monitor
				.elevatorsUnderMonitor.stream().collect(Collectors.groupingBy(Elevator::getCurrent_floor));
		int[] target_floors = {target_floor};
		if(mapbasedOnCurrFloor!= null) {
			if(mapbasedOnCurrFloor.size() == 1) {
				//means both elevator are in the same floor. So allocating randomly
				Optional<Elevator> optionalObj = monitor.elevatorsUnderMonitor.stream().findAny();
				Elevator elevatorToServe = optionalObj.orElse(null);
				elevatorToServe.setStart_floor(start_floor);
				elevatorToServe.setTarget_floors(target_floors);
				elevatorToServe.setCurrent_floor(start_floor);
			} else {
				final Set<Entry<Integer, List<Elevator>>> mapObj = mapbasedOnCurrFloor.entrySet();
				final Map<Integer, List<Elevator>> diffMap = new TreeMap<Integer, List<Elevator>>();
	
				final Iterator<Entry<Integer, List<Elevator>>> it = mapObj.iterator();
				int val = 0;
				List<Elevator> tempList = null;
				while (it.hasNext()) {
					Entry<Integer, List<Elevator>> item = it.next();
					val = Math.abs(start_floor - item.getKey());
					tempList = diffMap.get(val);
					if(tempList != null && tempList.size() > 0) {
						tempList.addAll(item.getValue());
					} else {
						diffMap.put(val, item.getValue());
					}
				}
				
				final Entry<Integer, List<Elevator>> theElevator = diffMap.entrySet().iterator().next();
				theElevator.getValue().get(0).setCurrent_floor(start_floor);
			}
		} else {
			LOG.info("The floorwise elevators is null. No elevators are available");
			throw new IllegalArgumentException("No Elevators Available");
		}
	}
	
}
