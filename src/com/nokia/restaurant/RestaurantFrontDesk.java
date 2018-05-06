package com.nokia.restaurant;

import java.util.Scanner;

import com.nokia.restaurant.model.Order;
import com.nokia.restaurant.service.RestaurantService;
import com.nokia.restaurant.serviceImpl.RestaurantServiceImpl;

/**
 * @author Shanmugam Raman
 * This is the driver class of this applications. 
 * Console driven; user is provided with the functions available in the application.
 **/
public class RestaurantFrontDesk {
	
	public static void main(String[] args) {

		RestaurantService service = new RestaurantServiceImpl();

		service.initRestaurant();
		service.getFunctions();
		
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()) {
			
			String optionSelected = scanner.nextLine();
			//System.out.println("Option Selected : "+optionSelected);
			
			switch (optionSelected) {
			case "1":
				service.getMenuCard();
				System.out.println();
				service.getFunctions();
				break;

			case "2":
				
				System.out.println("Please enter your name : ");
				String customerName = scanner.nextLine();
				
				System.out.println("Please enter your foodItems Id (separated by comma, in case of multiple food items: ");
				String foodItemsIds = scanner.nextLine();
				
				Order orderPlaced = service.addOrder(customerName, foodItemsIds);
				
				if(orderPlaced != null) {
					System.out.println("Order Placed!! Estimated Delivery Time : "+service.formatTimestamp(orderPlaced.getEstimatedDeliveryTime()));
				}else {
					System.out.println("Chefs are busy now.. Please try again later..!");
				}
				
				System.out.println();
				service.getFunctions();
				
				break;
				
			case "3":
				service.getPlacedOrders();
				System.out.println();
				service.getFunctions();
				break;
				
			case "4":
				System.out.println("Hope you enjoyed your food..! Visit us again..!");
				System.exit(1);
				
			default:
				System.out.println("Please enter a valid option..!");
				service.getFunctions();
			}

		}
				
		scanner.close();
		
	}

}
