package com.nokia.restaurant.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.nokia.restaurant.constants.FoodItemTypeEnum;
import com.nokia.restaurant.constants.FunctionsEnum;
import com.nokia.restaurant.model.Chef;
import com.nokia.restaurant.model.FoodItem;
import com.nokia.restaurant.model.FoodItemInOrder;
import com.nokia.restaurant.model.Order;
import com.nokia.restaurant.model.Restaurant;
import com.nokia.restaurant.service.RestaurantService;

/**
 * @author Shanmugam Raman 
 * contains method level implementations of RestaurantService
 */
public class RestaurantServiceImpl implements RestaurantService {

	private Restaurant restaurant;

	public RestaurantServiceImpl() {
		restaurant = Restaurant.getInstance();
	}

	private SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

	/**** Adds Food Items & Chefs on init ****/
	@Override
	public void initRestaurant() {

		System.out.println("Welcome to our Restaurant..!!");

		addFoodItems(new FoodItem("Veg Biriyani", 100.00, 10, FoodItemTypeEnum.VEG));
		addFoodItems(new FoodItem("Chapatti", 50.00, 5, FoodItemTypeEnum.VEG));
		addFoodItems(new FoodItem("Chicken Biriyani", 150.00, 10, FoodItemTypeEnum.NON_VEG));
		addFoodItems(new FoodItem("Fish Fry", 150.00, 15, FoodItemTypeEnum.NON_VEG));
		addFoodItems(new FoodItem("Tea & Coffee", 10.00, 10, FoodItemTypeEnum.TEA));
		addFoodItems(new FoodItem("Apple&Pomo", 70.00, 10, FoodItemTypeEnum.JUICE));
		addFoodItems(new FoodItem("Grapes", 60.00, 5, FoodItemTypeEnum.JUICE));
		addFoodItems(new FoodItem("Samosa", 10.00, 10, FoodItemTypeEnum.SNACKS));
		addFoodItems(new FoodItem("Bajji", 10.00, 5, FoodItemTypeEnum.SNACKS));
		addFoodItems(new FoodItem("Sandwich", 60.00, 5, FoodItemTypeEnum.SNACKS));

		addChef(new Chef("Tom Veg", FoodItemTypeEnum.VEG));
		addChef(new Chef("Jerry Veg", FoodItemTypeEnum.VEG));
		addChef(new Chef("Thanos Non-Veg", FoodItemTypeEnum.NON_VEG));
		addChef(new Chef("Stark Juice", FoodItemTypeEnum.JUICE));
		addChef(new Chef("Winterfell Tea", FoodItemTypeEnum.TEA));
		addChef(new Chef("Tyrion Snacks", FoodItemTypeEnum.SNACKS));
		addChef(new Chef("Tywin Snacks", FoodItemTypeEnum.SNACKS));
	}

	/**** Shows the functions available in the application ****/
	@Override
	public void getFunctions() {
		System.out.println("Please enter your choice : ");
		for(FunctionsEnum e : FunctionsEnum.values()) {
			System.out.println(">>"+e.getFunctionPosition()+": "+e.getFunctionDescription());
		}
		
		System.out.println("Chef's State : "+restaurant.getChefs());
		System.out.println("Order's State : "+restaurant.getOrders());
	}

	/**** Retrieves food items based on type ****/
	@Override
	public List<FoodItem> getFoodItemsByType(FoodItemTypeEnum type) {
		return restaurant.getFoodItems().stream()
				.filter(item -> item.getFoodType().getFoodItemTypeName().equalsIgnoreCase(type.getFoodItemTypeName()))
				.collect(Collectors.toList());
	}

	/**** Methods to add new order ****/
	@Override
	public Order addOrder(String customerName, String foodItemsIds) {
		
		System.out.println("Trying to add order for customer "+customerName+"..");
		
		List<FoodItem> orderedItems = new ArrayList<>();

		List<String> foodItemsList = Arrays.asList(foodItemsIds.split(",")).stream().map(t->t.trim()).collect(Collectors.toList());
		for(String foodItemId : foodItemsList) {
			try {
				if(restaurant.getAllFoodItemsId().contains(Integer.parseInt(foodItemId))) {
					orderedItems.add(getFoodItemByFoodItemId(Integer.parseInt(foodItemId)));
				} 
				else {
					System.out.println("Food Item with foodItemId : "+foodItemId+"is not available in our menu");
				}
			}catch (NumberFormatException e) {
				System.out.println("Food Item with foodItemId : "+foodItemId+"is not available in our menu");
			}	
		}
		
		Set<Chef> chefs = new HashSet<>();
		//temp Map to hold Chef / Food Items assignment
		Map<Chef, String> chefFoodItemMap = new HashMap<>();
		
		//Finding Chefs for the food items placed
		for(int i =0; i<orderedItems.size(); i++) {
			FoodItem foodItem = orderedItems.get(i);
			String foodItemType = foodItem.getFoodType().getFoodItemTypeName();
			
			Chef availableChefs = getAvailableChefs(foodItemType);
			//checking if chef are available for processing the type of food ordered
			if(availableChefs != null) {
				chefs.add(availableChefs);
				chefFoodItemMap.merge(availableChefs, foodItem.getFoodItemId().toString(), (v1,v2) -> v1+","+v2);
			}
		}

		final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
		int newOrderId = restaurant.getOrders().size()+1;
		
		for(Entry<Chef, String> entry : chefFoodItemMap.entrySet()) {
			Chef chef = entry.getKey();
			List<FoodItem> foodItems = Arrays.asList(entry.getValue().split(",")).stream().map(t->getFoodItemByFoodItemId(Integer.parseInt(t.trim()))).collect(Collectors.toList());
			
			Queue<FoodItemInOrder> chefQueue = new ArrayDeque<>(); 
			int timeTakenForChef = 0;
			if(!chef.getOrdersInQueue().isEmpty())
				chefQueue.addAll(chef.getOrdersInQueue());
			
			for(FoodItem foodItem : foodItems) {
				chefQueue.add(new FoodItemInOrder(newOrderId, foodItem));
				timeTakenForChef += foodItem.getPreparationTime();
			}
			//updating Chef's queue
			chef.setOrdersInQueue(chefQueue);
			//updating Chef's available time by adding the preparation time.
			chef.setAvailableFrom(new Date(chef.getAvailableFrom().getTime() + (timeTakenForChef * ONE_MINUTE_IN_MILLIS )));
			
		}
		Order order = new Order(customerName, orderedItems, chefs, new Date(System.currentTimeMillis()));
		restaurant.getOrders().add(order);
				
		return order;
	}

	/**** Methods to add new Food Item ****/
	@Override
	public boolean addFoodItems(FoodItem foodItem) {
		return restaurant.getFoodItems().add(foodItem);
	}

	/**** Methods to add new Chef ****/
	@Override
	public boolean addChef(Chef chef) {
		return restaurant.getChefs().add(chef);
	}

	/**** Methods to display the Menu-Card ****/
	@Override
	public void getMenuCard() {
		for (FoodItemTypeEnum e : FoodItemTypeEnum.values()) {

			System.out.println(e.getFoodItemTypeName());
			System.out.println("************");

			List<FoodItem> foodItems = getFoodItemsByType(e);
			Collections.sort(foodItems, new Comparator<FoodItem>() {
				@Override
				public int compare(FoodItem o1, FoodItem o2) {
					return o1.getFoodItemName().compareTo(o2.getFoodItemName());
				}
			});

			foodItems.stream().forEach(t -> {
				System.out.println("Food Item : " + String.format("%1$15s", t.getFoodItemName()) + "("
						+ t.getFoodItemId() + ") " + "; Preparation Time (in mins) : " + t.getPreparationTime());
			});

		}
	}

	/**** Methods to display Food Item by Food Id ****/
	@Override
	public FoodItem getFoodItemByFoodItemId(int foodItemId) {

		FoodItem foodItem = null;
		Optional<FoodItem> optional = restaurant.getFoodItems()
												.stream()
												.filter(new Predicate<FoodItem>() {
													@Override
													public boolean test(FoodItem t) {return t.getFoodItemId() == foodItemId;}
												}).findFirst();
		
		if (optional.isPresent()) {
			foodItem = optional.get();
		} else {
			System.out.println("No such Food-Items Id Found..");
		}
		return foodItem;
		
	}

	/**** Methods to display the order placed in the restaurant ****/
	@Override
	public void getPlacedOrders() {

		if (restaurant.getOrders().size() > 0) {
			restaurant.getOrders().stream().forEach(t -> {
				System.out.println( "Order #" + t.getOrderId() + " ++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println();
				System.out.println("Customer Name : " 	+ t.getCustomerName());
				System.out.println("Ordered Items :" 	+ t.getOrderedItemsAsString());
				System.out.println("Start Time :" 		+ formatTimestamp(t.getOrderStartTime()));
				System.out.println("Delivery Time :" 	+ formatTimestamp(t.getEstimatedDeliveryTime()));
				System.out.println();
			});
		} else {
			System.out.println("No orders available..");
		}

	}

	@Override
	public String formatTimestamp(Date date) {
		return df.format(date);
	}

	@Override
	public Chef getAvailableChefs(String foodItemType) {
		
		Chef availableChef = null;
		Optional<Chef>availableChefsOptional =  restaurant.getChefs()
												.stream()
												.filter(t-> t.getCookingSkills().getFoodItemTypeName().equalsIgnoreCase(foodItemType))
												.sorted(new Comparator<Chef>() {
													@Override
													public int compare(Chef chef1, Chef chef2) {
														return chef1.getAvailableFrom().compareTo(chef2.getAvailableFrom());
													}
													
												})
												.findFirst();
		if(availableChefsOptional.isPresent()) {
			availableChef =  availableChefsOptional.get();
		}

		return availableChef;
	}
	
}
