package com.sriram1113.main.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.sriram1113.main.services.Observer;

/**
 * This is the Elevator object which will serve the purpose of moving up and down
 *
 */
public class Elevator {

	private List<Observer> observers = new ArrayList<Observer>();

	/**
	 * Name of the elevator
	 * 
	 * @param name
	 */
	public Elevator(String name) {
		this.elevator_name = name;
	}

	/**
	 * This function is to attach the observers of this elevator object.
	 * 
	 * @param observer
	 */
	public void attach(Observer observer) {
		observers.add(observer);
	}

	/**
	 * Function used to notify all the observers of this elevator object
	 * 
	 * @param field_name
	 * @param field_value
	 */
	public void notifyAllObservers(String field_name, String field_value) {
		for (Observer observer : observers) {
			observer.update(this.elevator_name, field_name, field_value);
		}
	}

	private String elevator_name;
	private int current_floor;
	private int no_of_people;
	private int start_floor;
	private String direction;
	private String door_status;
	private Set<Integer> target_floors;
	private String movement_plan;

	/**
	 * @return
	 */
	public String getMovement_plan() {
		return movement_plan;
	}

	/**
	 * @param movement_plan
	 */
	public void setMovement_plan(String movement_plan) {
		this.movement_plan = movement_plan;
		notifyAllObservers("movement_plan", movement_plan);
	}

	/**
	 * @return
	 */
	public Set<Integer> getTarget_floors() {
		return target_floors;
	}

	/**
	 * @param target_floors
	 */
	public void setTarget_floors(int[] target_floors) {
		this.target_floors = Arrays.stream(target_floors).boxed().collect(Collectors.toSet());
		notifyAllObservers("target_floors", String.valueOf(this.target_floors));
	}

	/**
	 * @return
	 */
	public String getElevator_name() {
		return elevator_name;
	}

	/**
	 * @param elevator_name
	 */
	public void setElevator_name(String elevator_name) {
		this.elevator_name = elevator_name;
		notifyAllObservers("elevator_name", String.valueOf(this.elevator_name));
	}

	/**
	 * Returns the current floor that this elevator is currently in.
	 * 
	 * @return
	 */
	public int getCurrent_floor() {
		return current_floor;
	}

	/**
	 * @param current_floor
	 */
	public void setCurrent_floor(int current_floor) {
		this.current_floor = current_floor;
		notifyAllObservers("current_floor", String.valueOf(this.current_floor));
	}

	/**
	 * @return
	 */
	public int getNo_of_people() {
		return no_of_people;
	}

	/**
	 * @param no_of_people
	 */
	public void setNo_of_people(int no_of_people) {
		this.no_of_people = no_of_people;
		notifyAllObservers("no_of_people", String.valueOf(this.no_of_people));
	}

	/**
	 * @return
	 */
	public int getStart_floor() {
		return start_floor;
	}

	/**
	 * Start floor of this elevator
	 * 
	 * @param start_floor
	 */
	public void setStart_floor(int start_floor) {
		this.start_floor = start_floor;
		notifyAllObservers("start_floor", String.valueOf(this.start_floor));
	}

	/**
	 * @return
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Direction of travel for this elevator
	 * 
	 * @param direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
		notifyAllObservers("direction", String.valueOf(this.direction));
	}

	/**
	 * @return
	 */
	public String getDoor_status() {
		return door_status;
	}

	/**
	 * To show the status of the dorr of this elevator 
	 * 
	 * @param door_status
	 */
	public void setDoor_status(String door_status) {
		this.door_status = door_status;
		notifyAllObservers("door_status", String.valueOf(this.door_status));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elevator_name == null) ? 0 : elevator_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elevator other = (Elevator) obj;
		if (elevator_name == null) {
			if (other.elevator_name != null)
				return false;
		} else if (!elevator_name.equals(other.elevator_name))
			return false;
		return true;
	}
	
	
}