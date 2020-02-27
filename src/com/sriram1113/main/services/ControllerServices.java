package com.sriram1113.main.services;

import com.sriram1113.main.api.Elevator;
import com.sriram1113.main.core.ElevatorMonitor;

/**
 * Provides the services for elevator control.
 *
 */
public interface ControllerServices {

	public void callElevatorAndMoveTo(Elevator elevator, int call_floor, int go_to_floors);

	public void moveElevatorInSaidDirection(Elevator elevator, int call_floor, int go_to_floors);

	public void setElevatorDoorStatus(Elevator elevator, String door_status);

	public void serveCallAtFloor(ElevatorMonitor monitor, int start_floor, int target_floor);

}
