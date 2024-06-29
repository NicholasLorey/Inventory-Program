import java.util.InputMismatchException;
import java.util.Scanner;

/*****************************
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the main
 * Student Name: Nicholas Lorey
 * Student Number: 041101536 
 * Section #: 310_311
 * Course: CST8130 - Data Structures
 * Professor: Narges Tabar 
 * 
 *******************************/
public class Assign2 {

	public static void main(String[] args) {

		boolean toContinue = true;
		Inventory inventory = new Inventory();
		do {
			try {
				displayMenu();
				Scanner keyboard = new Scanner(System.in);
				String user = keyboard.next();
				
				switch(user) {
					case "1":
						inventory.addItem(keyboard,false);
						break;
					case "2":
						System.out.println("Inventory");
						System.out.println(inventory.toString());
						break;
					case "3":
						inventory.updateQuantity(keyboard, true);
						break;
					case "4":
						inventory.updateQuantity(keyboard, false);
						break;
					case "5":
						inventory.searchForItem(keyboard);
						break;
					case "6":
						inventory.saveToFile(keyboard);
						break;
					case "7":
						inventory.readFromFile(keyboard);
						break;
					case "8":
						toContinue = false;
						System.out.println("Exiting....");
						keyboard.close();
						break;
					default:
						System.out.println("...Invalid input, please try again...");
						break;
				}
			}catch(InputMismatchException e) {
				System.out.println("invalid entry");
			}
			
		}while(toContinue);
		
		

	}
	
	static void displayMenu() {
		System.out.println("Please select one of the following:");
		System.out.println("1: Add Item to Inventory");
		System.out.println("2: Display Current Inventory");
		System.out.println("3: Buy Item(s)");
		System.out.println("4: Sell Item(s)");
		System.out.println("5: Search for Item");
		System.out.println("6: Save Inventory to File");
		System.out.println("7: Read Inventory from File");
		System.out.println("8: To Exit");
		System.out.print(">");
		
	}

}
