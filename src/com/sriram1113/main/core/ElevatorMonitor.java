package com.sriram1113.main.core;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.logging.Logger;

import com.sriram1113.main.api.Elevator;
import com.sriram1113.main.services.MonitorServices;
import com.sriram1113.main.services.Observer;


/**
 * This is the monitor class. Any changes to the Elevator object will be notified to this Object so that
 * the monitor can keep track. Also this method writes the change in the update method.
 * This class, observer interface and the elevator class follow observer pattern.
 *
 */
public class ElevatorMonitor implements Observer, MonitorServices {
	
	public List<Elevator> elevatorsUnderMonitor = new ArrayList<Elevator>();
	
	private static final Logger LOG = Logger.getLogger("ElevatorMonitor");
	
	
	
	
	Elevator testObj = null;
	
	/**
	 * Add all the elevator which needs to be monitored by the monitor service
	 * 
	 * @param elevator
	 */
	public void addElevatorTobeMoitored(Elevator elevator) {
		requireNonNull(elevator, "Elevator");
		elevator.attach(this);
		this.elevatorsUnderMonitor.add(elevator);
	}
	
	/**
	 * This function is common to return a specific elevator from the list of monitored ones.
	 *  
	 * @param name
	 * @return
	 */
	public Elevator findElevatorFromList(String name) {
		Optional<Elevator> optionalObj = elevatorsUnderMonitor.stream().filter(a->a.getElevator_name().equals(name)).findFirst();
		testObj = optionalObj.orElse(null);
		return testObj;
	}

	/**
	 * Get the current Floor of the elevator provided as input.
	 * 
	 * @param name
	 * @return
	 */
	public int getElevatorFloorStatus(String name) {
		findElevatorFromList(name);
		return testObj.getCurrent_floor();
	}

	/**
	 * Get the current direction set for this elevator to move on.
	 * 
	 * @param name
	 * @return
	 */
	public String getElevatorCurrentDirection(String name) {
		findElevatorFromList(name);
		return testObj.getDirection();
	}

	/**
	 * Get the door status of this elevator
	 * 
	 * @param name
	 * @return
	 */
	public String getElevatorDoorStatus(String name) {
		findElevatorFromList(name);
		return testObj.getDoor_status();
	}

	@Override
	public void update(String elevator_name, String field_name, String field_value) {
		LOG.info("Elevator Monitor--" + elevator_name + " property " + field_name + " changed to " + field_value);
	}
}