package com.nokia.restaurant.model;

import com.nokia.restaurant.constants.FoodItemTypeEnum;

public class FoodItem {
	
	private static int counter;
	private Integer foodItemId;
	private String foodItemName;
	private double price;
	private Integer preparationTime;
	private FoodItemTypeEnum foodType;
	
	public FoodItem() {}
	
	public FoodItem(String foodItemName, double price, Integer preparationTime, FoodItemTypeEnum foodType) {
		counter++;
		this.foodItemId = counter;
		this.foodItemName = foodItemName;
		this.price = price;
		this.preparationTime = preparationTime;
		this.foodType = foodType;
	}

	public Integer getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(Integer foodItemId) {
		this.foodItemId = foodItemId;
	}

	public String getFoodItemName() {
		return foodItemName;
	}
	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getPreparationTime() {
		return preparationTime;
	}
	public void setPreparationTime(Integer preparationTime) {
		this.preparationTime = preparationTime;
	}
	public FoodItemTypeEnum getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodItemTypeEnum foodType) {
		this.foodType = foodType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FoodItem [")
				.append("foodItemName=").append(foodItemName)
				.append(", price=").append(price)
				.append(", foodType=").append(foodType)
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foodItemName == null) ? 0 : foodItemName.hashCode());
		result = prime * result + ((foodType == null) ? 0 : foodType.hashCode());
		result = prime * result + preparationTime;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FoodItem other = (FoodItem) obj;
		if (foodItemName == null) {
			if (other.foodItemName != null) {
				return false;
			}
		} else if (!foodItemName.equals(other.foodItemName)) {
			return false;
		}
		if (foodType != other.foodType) {
			return false;
		}
		if (preparationTime != other.preparationTime) {
			return false;
		}
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
			return false;
		}
		return true;
	}

}
