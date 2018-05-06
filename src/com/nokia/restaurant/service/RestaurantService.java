package com.nokia.restaurant.service;

import java.util.Date;
import java.util.List;

import com.nokia.restaurant.constants.FoodItemTypeEnum;
import com.nokia.restaurant.model.Chef;
import com.nokia.restaurant.model.FoodItem;
import com.nokia.restaurant.model.Order;

/**
 * @author Shanmugam Raman
 * Interface Restaurant Service exposes methods that are necessary to perform interaction between different entities 
 */
public interface RestaurantService {

	public void initRestaurant();
	public void getMenuCard();
	public void getFunctions();
	public void getPlacedOrders();
	
	public FoodItem getFoodItemByFoodItemId(int foodItemId);
	public List<FoodItem> getFoodItemsByType(FoodItemTypeEnum type);
	public Chef getAvailableChefs(String foodItemType);
	
	public Order addOrder(String customerName, String foodItemsIds) ;
	public boolean addFoodItems(FoodItem foodItem) ;
	public boolean addChef(Chef chef) ;
	
	public String formatTimestamp (Date date);	
	
}
