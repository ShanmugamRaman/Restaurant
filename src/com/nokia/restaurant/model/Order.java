package com.nokia.restaurant.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Order implements Comparable<Order> {
	
	private static int counter = 0 ;
	private int orderId;
	private String customerName;
	
	private List<FoodItem> orderedItems;
	private Set<Chef> chefs;
	
	private Date orderStartTime;
	private Date estimatedDeliveryTime;
	
	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
	
	public Order() {}
	
	public Order(String customerName, List<FoodItem> orderedItems, Set<Chef> chefs, Date orderStartTime) {
		counter ++;
		this.orderId = counter;
		this.customerName = customerName;
		this.orderedItems = orderedItems;
		this.chefs = chefs;
		this.orderStartTime = orderStartTime;
		//this.estimatedDeliveryTime = new Date(this.orderStartTime.getTime() + (getPrepMins(orderedItems) * ONE_MINUTE_IN_MILLIS ));
		this.estimatedDeliveryTime = new Date(chefs.stream().mapToLong(t->t.getAvailableFrom().getTime()).max().getAsLong());
		System.out.println("Order estimated "+estimatedDeliveryTime);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<FoodItem> getOrderedItems() {
		return orderedItems;
	}
	public void setOrderedItems(List<FoodItem> orderedItems) {
		this.orderedItems = orderedItems;
	}
	public Date getOrderStartTime() {
		return orderStartTime;
	}
	public void setOrderStartTime(Date orderStartTime) {
		this.orderStartTime = orderStartTime;
	}
	public Date getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}
	public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}
	public Set<Chef> getChefs() {
		return chefs;
	}

	public void setChefs(Set<Chef> chefs) {
		this.chefs = chefs;
	}

	public String getOrderedItemsAsString() {
		StringBuffer foodItems = new StringBuffer();
		this.getOrderedItems().stream().forEach(t -> foodItems.append(t.getFoodItemName() +" ;"));
		return foodItems.substring(0, foodItems.length()-1).toString();
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [orderId=");
		builder.append(orderId);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", orderedItems=");
		builder.append(orderedItems);
		builder.append(", chefs=");
		builder.append(chefs);
		builder.append(", orderStartTime=");
		builder.append(orderStartTime);
		builder.append(", estimatedDeliveryTime=");
		builder.append(estimatedDeliveryTime);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Order o) {
		return this.orderStartTime.compareTo(o.orderStartTime);
	}

	/*private int getPrepMins(List<FoodItem> orderedItems) {
		
		int totalPrepMins = 0;
		//Calculating maximum time to prepare other food items 
		OptionalInt totalTimeExceptSnacks = orderedItems.stream().filter(t->!t.getFoodType().getFoodItemTypeName().equalsIgnoreCase("Snacks")).mapToInt(t->t.getPreparationTime()).max();
		totalPrepMins = totalTimeExceptSnacks.isPresent() ? totalTimeExceptSnacks.getAsInt() : 0;
		
		//Calculating total time to deliver ALL the snacks in the order.
		totalPrepMins += getOrderedItems().stream().filter(t->t.getFoodType().getFoodItemTypeName().equalsIgnoreCase("Snacks")).mapToInt(t->t.getPreparationTime()).sum();
		
		return totalPrepMins;
	}*/
	
}
