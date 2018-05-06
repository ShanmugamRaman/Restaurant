package com.nokia.restaurant.constants;

/**
 * @author Shanmugam
 * This enum contains the different functionalities available in the restaurant
 *
 */
public enum FunctionsEnum {
	
	VIEW_MENU_CARD (1, "View Food Items"), 
	PLACE_ORDER (2, "Place your Order"), 
	VIEW_PLACED_ORDERS(3, "View Placed Orders"), 
	EXIT (4, "Exit the application");

	private int functionPosition;
	private String functionDescription;
	private FunctionsEnum(int functionPosition, String functionDescription) {
		this.functionPosition = functionPosition;
		this.functionDescription = functionDescription;
	}
	public int getFunctionPosition() {
		return functionPosition;
	}
	public void setFunctionPosition(int functionPosition) {
		this.functionPosition = functionPosition;
	}
	public String getFunctionDescription() {
		return functionDescription;
	}
	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}
	
	
}
