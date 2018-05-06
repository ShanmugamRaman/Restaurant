package com.nokia.restaurant.model;

public class FoodItemInOrder {
	private int orderId;
	private FoodItem foodItem;
	
	public FoodItemInOrder(int orderId, FoodItem foodItem) {
		this.orderId = orderId;
		this.foodItem = foodItem;
	}
	public int getOrderId() {
		return orderId;
	}
	public FoodItem getFoodItem() {
		return foodItem;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FoodItemInOrder [orderId=");
		builder.append(orderId);
		builder.append(", foodItem=");
		builder.append(foodItem);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
