import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;
/************************************************************
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the dynamically allocated array to store the object that will be created by the userInput
 * Student Name: Nicholas Lorey
 * Student Number: 041101536 
 * Section #: 310_311
 * Course: CST8130 - Data Structures
 * Professor: Narges Tabar 
 * 
 ***********************************************************/
public class Inventory {
	
	private ArrayList<FoodItem> inventory; 
	
	public Inventory() {
		/*declare the arrayList and the capacity*/
		inventory = new ArrayList<>(20);
	}
	
	/************************************************************
	 * toString() - to print each Object in the arrayList inventory
	 ***********************************************************/
	public String toString() {

		StringBuilder sb = new StringBuilder();
		Collections.sort(inventory,new SortbyCode());
		for(FoodItem s: inventory) 
			sb.append(s.toString()+"\n");
		
		return sb.toString();
		
	}
	
	/**********************************************************************************************
	 * alreadyExists() - check if the code match any object in the arrayList
	 * @param item - take an object and use the isEqual method if the current object already exist on the Inventory
	 * @return object index if the object match the code in the Inventory
	 * @return -1 if the object user want to add didn't match the code in the Inventory
	 *********************************************************************************************/
	public int alreadyExists(FoodItem item) {
		for(FoodItem s: inventory) {
			/*use isEqual() method to check every object itemCode*/
			boolean result=s.isEqual(item);
			if(result) {
				return inventory.indexOf(s);
			}
		}
		return -1;
		
	}
	
	/*******************************************************************
	 * addItem() - to add an item to the ArrayList Object 
	 * @param scanner - take input user to check which item user want to add
	 * @param fromFile, it decide whether the add is from file or from user
	 * @return false if addItem unsuccessfull
	 * Use alreadyExists() method in here to check if the object user want to add already exist or not
	 ******************************************************************/

	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean toContinue = true;
		String input = null;
		if(fromFile) {
			input = scanner.nextLine();
			switch(input) {
			case "f":
				Fruit fruit = new Fruit();
				
				fruit.addItem(scanner, true);
				int result = alreadyExists(fruit);
				/*if object didn't match any code in Inventory, add to Inventory*/
				if(result == -1) {
					inventory.add(fruit);
					toContinue = false;
				}else {
					System.out.println("Item code already exists");
					toContinue = false;
				}
				break;
			case "v":
				Vegetable vegetable = new Vegetable();
				
				vegetable.addItem(scanner, true);
				result = alreadyExists(vegetable);
				/*if object didn't match any code in Inventory, add to Inventory*/
				if(result == -1) {
					inventory.add(vegetable);
					toContinue = false;
				}else {
					System.out.println("Item code already exists");
					toContinue = false;
				}
				break;
			case "p":
				Preserve preserve = new Preserve();
				
				preserve.addItem(scanner, true);
				result = alreadyExists(preserve);
				/*if object didn't match any code in Inventory, add to Inventory*/
				if(result == -1) {
					inventory.add(preserve);
					toContinue = false;
				}else {
					System.out.println("Item code already exists");
					toContinue = false;
				}
				break;
			}
		}else {
			do {
				System.out.println("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");	
				input = scanner.next();
				switch(input) {
					case "f":
						Fruit fruit = new Fruit();
						/*call the addItem() method to gather the data input from the user*/
						fruit.addItem(scanner,false);
						int result = alreadyExists(fruit);
						/*if object didn't match any code in Inventory, add to Inventory*/
						if(result == -1) {
							inventory.add(fruit);
							toContinue = false;
						}else {
							System.out.println("Item code already exists");
							toContinue = false;
						}
						break;
					case "v":
						Vegetable vegetable = new Vegetable();
						vegetable.addItem(scanner,false);
						result = alreadyExists(vegetable);
						if(result == -1) {
							inventory.add(vegetable);
							toContinue = false;
						}else {
							System.out.println("Item code already exists");
							toContinue = false;
						}
						break;
					case "p":
						Preserve preserve = new Preserve();
						preserve.addItem(scanner,false);
						result = alreadyExists(preserve);
						if(result == -1) {
							inventory.add(preserve);
							toContinue = false;
						}else {
							System.out.println("Item code already exists");
							toContinue = false;
						}
						break;
					default:
						System.out.println("Invalid entry");
						break;
			}
			
			}while(toContinue);
		}
		
		
		return false;
		
	}
	
	/***********************************************************************
	 * updateQuantity() - to update the inventory if user want to sell/buy
	 * @param scanner - take user input 
	 * @param buyOrSell - decide if user choose buy/sell
	 * @return false if updateQuantity failed
	 * use inputCode() to check if the code exists or not
	 ***********************************************************************/
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		
		System.out.println("Enter the item code:");
	    String itemCode = scanner.next();
	    boolean existCode = false;
	    /*buy - loop through arrayList, if code exists then ask user to input quantity to add to Inventory */
		if(buyOrSell) {
			
			for(FoodItem s : inventory) {
				Scanner input = new Scanner(itemCode);
				/*check if itemCode already exists*/
				boolean result = s.inputCode(input);
				if(result) {
					System.out.println("Enter valid quantity to buy:");
					int quantity = scanner.nextInt();
					if(quantity >= 0) {
						/*use updateItem() method to add the amount to the itemStock object*/
						s.updateItem(quantity+s.itemQuantityInStock);
						existCode = true;
						break;
					}else {
						System.out.println("please enter a positive number");
						existCode = true;
					}
				}
				else{
					existCode = false;
				}
				
			}
			if(existCode == false) {
				System.out.println("Code not found in inventory...");
				System.out.println("Error...could not buy item");
			}
			
		}
		/*sell - loop through arrayList, if code exists then ask user to input quantity to decrease*/
		else if(buyOrSell == false) {
			for(FoodItem s : inventory) {
				Scanner input = new Scanner(itemCode);
				/*check if itemCode already exists*/
				boolean result = s.inputCode(input);
				if(result) {
					System.out.println("Enter valid quantity to sell:");
					int quantity = scanner.nextInt();
					if(quantity >= 0) {
						/*use updateItem() method to add the amount to the itemStock object*/
						s.updateItem(s.itemQuantityInStock-quantity);
						existCode = true;
						break;
					}else {
						System.out.println("please enter a positive number");
						existCode = true;
					}
				}
				else{
					existCode = false;
				}
				
			}
			if(existCode == false) {
				System.out.println("Code not found in inventory...");
				System.out.println("Error...could not sell item");
			}
			
		}
		return existCode;
		
	}
	
	/************************************************************************
	 * searchForItem() - to search item base on user input itemCode
	 * @param scanner - take itemCode from user and use binary search to loop through the object
	 ***********************************************************************/
	public void searchForItem(Scanner scanner) {
		Collections.sort(inventory,new SortbyCode());
		System.out.println("Enter the code for the item:");
		int toSearch = scanner.nextInt();
		int start = 0;
		int end = inventory.size() - 1;
		boolean toContinue = true;

		while (start <= end && toContinue) {
			//find the middle number of the array
			int mid = (start + end)/2;

			//if the search number equal to the middle number, it goes here
			int result = inventory.get(mid).getItemCode();
			if(toSearch == result) {
				System.out.println(inventory.get(mid));
				toContinue = false;
			}else if(toSearch > result) {
				//the start will take the mid number + 1 if the number to search is bigger than the middle number
				start = mid + 1;
			}else if(toSearch < result){
				end = mid -1;
			}
			
		}
		if(toContinue){
			System.out.println("Code not found in inventory");
		}
		
	}
	
	/***********************************************************************
	 * saveToFile() - save the content of the arrayList object to a file that user give
	 * @param scanner - take the name of the file
	 ***********************************************************************/
	public void saveToFile(Scanner scanner) {
		System.out.println("enter file name");
		String fileName = scanner.next();
		try {
			
			FileWriter myWriter = new FileWriter(fileName);
			Formatter writer = new Formatter(myWriter);
			//loop through each object to save to a file
			for(FoodItem s: inventory) {
				//using outputItem to format the list 
				s.outputItem(writer);
				
			}
			
			myWriter.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	/************************************************************************
	 * readFromFile() - to read the content of the file and add it to the arrayList
	 * @param scanner - take the file name from the user to read from
	 ***********************************************************************/
	public void readFromFile(Scanner scanner) {
		System.out.println("Enter file name:");
		StringBuilder sb = new StringBuilder();
		String fileName = scanner.next();
		BufferedReader reader;
		String test;
		try {
			int i = 0;
			reader = new BufferedReader(new FileReader(fileName));
			//read each line and add to the stringBuilder to make it 1 object 1 line
			while((test  = reader.readLine()) != null) {
				sb.append(test+"\n");
				i++;
				if(i == 7) {
					scanner = new Scanner(sb.toString());
					addItem(scanner, true);
					//clear the stringBuilder everytime an object have been added to arrayList
					sb.setLength(0);
					i=0;
				}
					
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	

}
