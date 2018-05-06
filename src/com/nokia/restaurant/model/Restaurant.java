package com.nokia.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shanmugam Raman
 * Singleton Class Approach since we are dealing with only one Restaurant 
 */
public class Restaurant {

	private static Restaurant restaurant;
	
	private List<FoodItem> foodItems = new ArrayList<>();
	private List<Chef> chefs = new ArrayList<>();
	private List<Order> orders = new ArrayList<>();
	
	private Restaurant() {
		
	}
	public static synchronized Restaurant getInstance() {
		if(restaurant  == null) {
			restaurant = new Restaurant();
		}
		return restaurant;
	}
	
	public List<FoodItem> getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(List<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
	public List<Chef> getChefs() {
		return chefs;
	}
	public void setChefs(List<Chef> chefs) {
		this.chefs = chefs;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<Integer> getAllFoodItemsId(){
		return getFoodItems().stream().map(t->t.getFoodItemId()).collect(Collectors.toList());
	}
	@Override
	public String toString() {
		return "Restaurant [foodItems=" + foodItems + ", chefs=" + chefs + ", orders=" + orders + "]";
	}

}
