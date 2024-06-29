import java.util.Formatter;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the child from the parent class FoodItem
 * Student Name: Nicholas Lorey
 * Student Number: 041101536 
 * Section #: 310_311
 * Course: CST8130 - Data Structures
 * Professor: Narges Tabar 
 * 
 */

public class Preserve extends FoodItem{
	
	private int jarSize;
	
	public Preserve() {
		
	}
	
	/*****************************************************************************************
	 * toString() - will return their own string format because each child is different 
	 * Call the parent toString() to gather the data
	 ****************************************************************************************/
	public String toString() {
		String ss = super.toString();
		String s = String.format("%s size: %s mL",ss, this.jarSize);
		return s;
	}
	
	/*****************************************************************************************
	 * addItem() - to add their own child variable that is needed to gather the data
	 * because each child have their own varible, so they have their own addItem
	 * @param fromFile - if true then it read from a file
	 * @param scanner - take the content of the file to read from
	 * @return true if the adding is success
	 * @return false if the adding is failed
	 ****************************************************************************************/
	public boolean addItem(Scanner scanner, boolean fromFile) {

		if (fromFile) {
			super.addItem(scanner, true);
			String input = scanner.nextLine();			
			this.jarSize = Integer.parseInt(input);
		}else {
			super.addItem(scanner,false);
			System.out.println("Enter the size of the jar in milliliters:");
			this.jarSize = scanner.nextInt();
			return true;
			
		}
		
		return false;
	}
	
	/****************************************************************************************
	 * outputItem() - to format the content to what we want
	 * @param writer - take a Formatter object to be able to use the format method
	 ***************************************************************************************/
	public void outputItem(Formatter writer) {
		writer.format("p\n");
		super.outputItem(writer);
		writer.format("%d\n", this.jarSize);
		
	}

}
