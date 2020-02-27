package com.sriram1113.main.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.management.modelmbean.RequiredModelMBean;

/**
 * This class satisfies the Basic Elevator requirements. Requirements 1.a and 1.b are implemented in this class.
 *
 */
public class BasicElevator {
	
	private static final Logger LOG = Logger.getLogger("BasicElevator");

	/**
	 * This method is to simply instruct the elevator to decide its movement plan based on the 
	 * inputs provided on direction, floor and number of people, without moving the elevator.
	 * 
	 * arguments
	 * @param number_of_people - to indicate the number of people who has pressed the buttons from inside
	 * @param start_floor - the start floor of this elevator
	 * @param target_floors - target floors to be visited
	 * @param directions - direction of travel
	 * @return - the movement plan as a string
	 */
	public String instructElevator(int number_of_people, int start_floor, int[] target_floors, String[] directions) {
		
		if(!Arrays.asList(directions).contains("UP") && !Arrays.asList(directions).contains("DOWN")) {
			throw new IllegalArgumentException("Invalid Direction of travel for the elevator");
		}
		
		final StringBuilder planOfAction = new StringBuilder();
		Set<Integer> set = null;
		List<Integer> floors = null;
		try {
			int current_floor = start_floor;
			if (target_floors.length == 1) {
				if(target_floors[0] < 0 || target_floors[0] > 5) {
					return "Invalid Floor";
				} else {
					planOfAction.append("[");
					if("UP".equals(directions[0])) {
						planOfAction.append(new String(new char[target_floors[0]-start_floor]).replace("\0", "UP_1,"));
					} else if ("DOWN".equals(directions[0])) {
						planOfAction.append(new String(new char[start_floor-target_floors[0]]).replace("\0", "DOWN_1,"));
					}
					planOfAction.append("OPEN_DOOR,CLOSE_DOOR]");
				}
			} else if(directions.length == 1) {
				//visiting multiple floors in same direction out of sequence
				set = Arrays.stream(target_floors).boxed().collect(Collectors.toSet());
				floors = new ArrayList<Integer>();
				floors.addAll(set);
				Collections.sort(floors);
				if("UP".equals(directions[0])) {
					for(int i=0; i<floors.size(); i++) {
						planOfAction.append("[");
						planOfAction.append(new String(new char[floors.get(i)-current_floor]).replace("\0", "UP_1,"));
						planOfAction.append("OPEN_DOOR,CLOSE_DOOR]");
						current_floor = floors.get(i);
					}
				} else if("DOWN".equals(directions[0])) {
					Collections.reverse(floors);
					for(int i=0; i<floors.size(); i++) {
						planOfAction.append("[");
						planOfAction.append(new String(new char[current_floor-floors.get(i)]).replace("\0", "DOWN_1,"));
						planOfAction.append("OPEN_DOOR,CLOSE_DOOR]");
						current_floor = floors.get(i);
					}
				}
			} else if(directions.length > 1) {
				//visiting multiple floors in same direction out of sequence
				int current_floor_new = start_floor;
				set = Arrays.stream(target_floors).boxed().collect(Collectors.toSet());
				final List<Integer> topList = set.stream().filter(a -> (a > current_floor_new)).collect(Collectors.toList());
				Collections.sort(topList);
				final List<Integer> bottomList = set.stream().filter(a -> (a < current_floor_new)).collect(Collectors.toList());
				Collections.sort(bottomList);
				Collections.reverse(bottomList);
				if("UP".equals(directions[0])) {
					for(int i=0; i<topList.size(); i++) {
						planOfAction.append("[");
						planOfAction.append(new String(new char[topList.get(i)-current_floor]).replace("\0", "UP_1,"));
						planOfAction.append("OPEN_DOOR,CLOSE_DOOR]");
						current_floor = topList.get(i);
					}
					for(int i=0; i<bottomList.size(); i++) {
						planOfAction.append("[");
						planOfAction.append(new String(new char[current_floor-bottomList.get(i)]).replace("\0", "DOWN_1,"));
						planOfAction.append("OPEN_DOOR,CLOSE_DOOR]");
						current_floor = bottomList.get(i);
					}
				} else if("DOWN".equals(directions[0])) {
					for(int i=0; i<bottomList.size(); i++) {
						planOfAction.append("[");
						planOfAction.append(new String(new char[current_floor-bottomList.get(i)]).replace("\0", "DOWN_1,"));
						planOfAction.append("OPEN_DOOR,CLOSE_DOOR]");
						current_floor = bottomList.get(i);
					}
					for(int i=0; i<topList.size(); i++) {
						planOfAction.append("[");
						planOfAction.append(new String(new char[topList.get(i)-current_floor]).replace("\0", "UP_1,"));
						planOfAction.append("OPEN_DOOR,CLOSE_DOOR]");
						current_floor = topList.get(i);
					}
				}
			}
		} catch (Exception e) {
			LOG.info("Resulted in Exception:" + e.getMessage() + "::::" + e.getCause());
		}
		return planOfAction.toString();
	}
}