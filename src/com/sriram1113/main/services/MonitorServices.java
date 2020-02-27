package com.sriram1113.main.services;

import com.sriram1113.main.api.Elevator;

/**
 * Provides monitor services for the elevator
 *
 */
public interface MonitorServices {

	public void addElevatorTobeMoitored(Elevator elevator);

	public Elevator findElevatorFromList(String name);

	public int getElevatorFloorStatus(String name);

	public String getElevatorCurrentDirection(String name);

	public String getElevatorDoorStatus(String name);
}