package com.nokia.restaurant.constants;

/**
 * @author Shanmugam Raman
 * This enum contains the different types of Food Items available in the restaurant
 *
 */
public enum FoodItemTypeEnum {

	VEG(1, "Veg"), NON_VEG(2, "Non-Veg"), JUICE(4, "Juice"), TEA(5, "Tea / Coffee"), SNACKS(3, "Snacks");
	
	private int foodItemTypeOrder;
	private String foodItemTypeName;
	
	private FoodItemTypeEnum(int foodItemTypeOrder, String foodItemTypeName) {
		this.foodItemTypeOrder = foodItemTypeOrder;
		this.foodItemTypeName = foodItemTypeName;
	}

	public int getFoodItemTypeOrder() {
		return foodItemTypeOrder;
	}

	public void setFoodItemTypeOrder(int foodItemTypeOrder) {
		this.foodItemTypeOrder = foodItemTypeOrder;
	}

	public String getFoodItemTypeName() {
		return foodItemTypeName;
	}

	public void setFoodItemTypeName(String foodItemTypeName) {
		this.foodItemTypeName = foodItemTypeName;
	}
	
}
