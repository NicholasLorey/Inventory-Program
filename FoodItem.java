import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
/************************************************************
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the base class to be used by the child class and the Inventory class
 * Student Name: Nicholas Lorey
 * Student Number: 041101536 
 * Section #: 310_311
 * Course: CST8130 - Data Structures
 * Professor: Narges Tabar 
 * 
 ***********************************************************/
public abstract class FoodItem {
	
	private int itemCode;
	private String itemName;
	private float itemPrice;
	protected int itemQuantityInStock;
	private float itemCost;
	
	public FoodItem() {
		
	}
	
	/*****************************************************************************************
	 * toString() - will return the formatted output when display the Inventory item
	 ****************************************************************************************/
	public String toString() {
		String s = String.format("Item: %d %s %d, price: $ %.2f, cost: $ %.2f,",this.itemCode,this.itemName, this.itemQuantityInStock,this.itemPrice,this.itemCost);
		return s;
	}
	
	/*****************************************************************************************
	 * updateItem() - to update the current item with the newest Item stock
	 * @param amount - take the amount from the user that has been calculated 
	 * @return false if the update is failed
	 * @return true if the update is success
	 ****************************************************************************************/
	public boolean updateItem(int amount) {
		boolean toContinue = true;
		/*if the stock is enough that it will update and return true*/
		if(toContinue && amount >= 0) {
			this.itemQuantityInStock = amount;
			return toContinue;
		/*if stock not enough then return a warning to the user*/
		}else if(amount < 0) {
			System.out.println("Insufficient stock in inventory...");
			System.out.println("Error...could not sell item");
		}
		else {
			System.out.println("Invalid quantity...");
			System.out.println("Error...could not buy item");
		}
		
		return false;
	}
	
	/*****************************************************************************************
	 * isEqual() - to check if the user Object itemCode match the inventory itemCode
	 * @param item - take the object from the user and check the new object itemCode
	 * @return true if the itemCode is match
	 * @return false if the itemCode didn't match
	 ****************************************************************************************/
	public boolean isEqual(FoodItem item) {
		if(item.itemCode == this.itemCode) {
			return true;
		}
		return false;
	}
	
	/************************************************************
	 * addItem() - to gather the data from the user input 
	 * @param scanner - take user input
	 * @param fromFile - if it's true then it directly input the value without asking user
	 * @return true if the gather data is success
	 * @return false if the gather data is failed
	 ***********************************************************/
	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean success = false;
		if(fromFile) {
			this.itemCode = Integer.parseInt(scanner.nextLine());
			this.itemName = scanner.nextLine();
			this.itemQuantityInStock = Integer.parseInt(scanner.nextLine());
			this.itemCost = Float.parseFloat(scanner.nextLine());
			this.itemPrice = Float.parseFloat(scanner.nextLine());
			
		}else {
			
			do{
				try {
					System.out.println("Enter the code for the item:");
					this.itemCode = scanner.nextInt();
					scanner.nextLine();
					
					System.out.println("Enter the name for the item:");
					this.itemName = scanner.nextLine();
					//scanner.nextLine();
					
					System.out.println("Enter the quantity for the item:");
					this.itemQuantityInStock = scanner.nextInt();
					/*check positive number*/
					if (this.itemQuantityInStock < 0) {
		                throw new InputMismatchException();
		                
		            }
					
					System.out.println("Enter the cost of the item:");
					this.itemCost = scanner.nextFloat();
					/*check positive number*/
					if (this.itemCost <= 0) {
		                throw new InputMismatchException();
		                
		            }
					
					System.out.println("Enter the sales price of the item:");
					this.itemPrice = scanner.nextFloat();
					/*check positive number*/
					if (this.itemPrice <= 0) {
		                throw new InputMismatchException();
		                
		            }
					
					
					success = true;
				}catch(InputMismatchException e) {
					System.out.println("invalid entry, REDO");
					scanner.nextLine();
				}

			}while(!success);
		}
		
		
		return success;
	}
	
	/************************************************************
	 * inputCode() - to check if the user input itemCode match the inventory itemCode
	 * @param scanner - take user input
	 * @return true if the itemCode is match
	 * @return false if the itemCode didn't match
	 ***********************************************************/
	public boolean inputCode(Scanner scanner) {
		int itemCode = scanner.nextInt();
		if(itemCode == this.itemCode) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * outputItem() - format the content to be exactly like we want it
	 * @param writer - take a Formatter object to be able to use the format method
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n%s\n%d\n%.2f\n%.2f\n", this.itemCode,this.itemName, this.itemQuantityInStock,this.itemPrice,this.itemPrice);
	}
	public int getItemCode() {
		return this.itemCode;
	}

	
	

}
