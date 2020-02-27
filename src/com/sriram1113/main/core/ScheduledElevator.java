package com.sriram1113.main.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.sriram1113.main.api.Elevator;
import com.sriram1113.main.services.Observer;

/**
 * This is the ScheduledElevator which serves the scheduled elevator movement
 *
 */
public class ScheduledElevator extends TimerTask implements Observer {

	private static final Logger LOG = Logger.getLogger("ScheduledElevator");

	private Elevator elevator = new Elevator("Elevator1");

	public ScheduledElevator() {

	}

	/**
	 * ScheduledElevator constructor which will also get itself attached to the
	 * monitor service on which the elevator is scheeduled for
	 * 
	 * @param elevator
	 */
	public ScheduledElevator(Elevator elevator) {
		this.elevator = elevator;
		this.elevator.attach(this);
	}

	/**
	 * This function schedules the elevator for the specified date and time. If time
	 * already has passed then the execution will happen immediately. the main
	 * thread will be held for the mentioned timeout seconds
	 * 
	 * @param start_floor
	 * @param movement_plan
	 * @param datetime
	 * @param timeoutToWait
	 * @return
	 * @throws InterruptedException - if the interruption occures
	 * @throws ParseException       - if the data input provided is not in the
	 *                              mentioned format
	 */
	public int scheduleElevatorForExecution(int start_floor, String movement_plan, String datetime, int timeoutToWait)
			throws InterruptedException, ParseException {
		Timer timer = null;
		this.elevator.setStart_floor(start_floor);
		this.elevator.setMovement_plan(movement_plan);
		TimerTask task = new ScheduledElevator(this.elevator);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = df.parse(datetime);
		final CountDownLatch latch = new CountDownLatch(1);
		timer = new Timer();
		timer.schedule(task, date);
		latch.await(timeoutToWait, TimeUnit.SECONDS);
		return elevator.getCurrent_floor();
	}

	@Override
	public void run() {
		int movement = this.elevator.getStart_floor();
		String[] planSplit = this.elevator.getMovement_plan().split(",OPEN_DOOR,CLOSE_DOOR]");
		int[] target_floors = new int[planSplit.length];
		String[] actions = null;
		for (int i = 0; i < planSplit.length; i++) {
			actions = planSplit[i].split(",");
			for (int j = 0; j < actions.length; j++) {
				if (actions[j].contains("UP")) {
					this.elevator.setDirection("UP");
					movement++;
				} else if (actions[j].contains("DOWN")) {
					this.elevator.setDirection("DOWN");
					movement--;
				}
			}
			target_floors[i] = movement;
		}
		Arrays.sort(target_floors);
		this.elevator.setTarget_floors(target_floors);
		this.elevator.setCurrent_floor(target_floors[target_floors.length - 1]);
	}

	@Override
	public void update(String elevator_name, String field_name, String field_value) {

	}

}