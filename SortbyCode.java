import java.util.Comparator;
/************************************************************
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the class of Coomparator to be used by the Inventory class
 * Student Name: Nicholas Lorey
 * Student Number: 041101536 
 * Section #: 310_311
 * Course: CST8130 - Data Structures
 * Professor: Narges Tabar 
 * 
 ***********************************************************/

/****************************************************
 * SortbyCode() - compare the 2 object to be sorted
 ****************************************************/
public class SortbyCode implements Comparator<FoodItem>{
	public int compare(FoodItem a, FoodItem b) {
		return a.getItemCode()-b.getItemCode();
	}
}
