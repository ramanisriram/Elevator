package com.sriram1113.main.services;

/**
 * This interface has a method to be implemented by the observer so that the
 * actual object value changes can be monitored
 *
 */
public interface Observer {
	public void update(String elevator_name, String field_name, String field_value);
}