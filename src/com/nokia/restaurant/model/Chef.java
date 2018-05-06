package com.nokia.restaurant.model;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

import com.nokia.restaurant.constants.FoodItemTypeEnum;

public class Chef {
	
	private static int counter = 0;
	private int chefId;
	private String chefName;
	private FoodItemTypeEnum cookingSkills;
	//private boolean isAvailable;
	private Queue<FoodItemInOrder> ordersInQueue;
	private Date availableFrom;

	public Chef() {}
	public Chef(String chefName, FoodItemTypeEnum cookingSkills) {
		Chef.counter += 1;
		this.chefId = counter;
		this.chefName = chefName;
		this.cookingSkills = cookingSkills;
		this.ordersInQueue = new ArrayDeque<>();
		this.availableFrom = new Date(System.currentTimeMillis());
	}
	public int getChefId() {
		return chefId;
	}
	public void setChefId(int chefId) {
		this.chefId = chefId;
	}
	public String getChefName() {
		return chefName;
	}
	public void setChefName(String chefName) {
		this.chefName = chefName;
	}
	public FoodItemTypeEnum getCookingSkills() {
		return cookingSkills;
	}
	public void setCookingSkills(FoodItemTypeEnum cookingSkills) {
		this.cookingSkills = cookingSkills;
	}
	/*public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}*/
	public Queue<FoodItemInOrder> getOrdersInQueue() {
		return ordersInQueue;
	}
	public void setOrdersInQueue(Queue<FoodItemInOrder> ordersInQueue) {
		this.ordersInQueue = ordersInQueue;
	}
	public Date getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chef [chefId=");
		builder.append(chefId);
		builder.append(", chefName=");
		builder.append(chefName);
		builder.append(", cookingSkills=");
		builder.append(cookingSkills);
		builder.append(", ordersInQueue=");
		builder.append(ordersInQueue);
		builder.append(", availableFrom=");
		builder.append(availableFrom);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availableFrom == null) ? 0 : availableFrom.hashCode());
		result = prime * result + chefId;
		result = prime * result + ((chefName == null) ? 0 : chefName.hashCode());
		result = prime * result + ((cookingSkills == null) ? 0 : cookingSkills.hashCode());
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
		Chef other = (Chef) obj;
		if (availableFrom == null) {
			if (other.availableFrom != null) {
				return false;
			}
		} else if (!availableFrom.equals(other.availableFrom)) {
			return false;
		}
		if (chefId != other.chefId) {
			return false;
		}
		if (chefName == null) {
			if (other.chefName != null) {
				return false;
			}
		} else if (!chefName.equals(other.chefName)) {
			return false;
		}
		if (cookingSkills != other.cookingSkills) {
			return false;
		}
		return true;
	}
	
}
