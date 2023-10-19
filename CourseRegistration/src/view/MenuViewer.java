/* The [View] package presents the data in an application in a readable format
    - It contains the connections to a graphical user interface or web page
    - It does not handle any of the data
    - It is usually designed to be used again for other applications
 */
package view;

import java.util.Scanner;

public class MenuViewer {

	public  static Scanner scan = new Scanner(System.in);
	
	public static void viewMainMenu() {
	System.out.println("---------------- Course Registration Program ----------------");
	System.out.printf(" [1] Major information: \tView / Add / Edit / Delete%n" +
			" [2] Student information:\tView / Add / Edit%n" +
			" [3] Course information:\tView / Add / Edit / Delete%n" +
			" [4] Course registration:\tView / Register / Cancel%n" + 
			" [5] Close program%n");
	System.out.println("-------------------------------------------------------------");
	System.out.println("Select an option above :");
	}
	
	public static void majorMenu() {
		System.out.println("------ Major Information ------");
		System.out.printf(" [1] View major information%n" +
				" [2] Add major information%n" +
				" [3] Edit major information%n" +
				" [4] Delete major information%n" + 
				" [5] Return to main menu%n");
		System.out.println("-------------------------------");
		System.out.println("Select an option above :");
	}
	
	public static void studentMenu() {
		System.out.println("------ Student Information ------");
		System.out.printf(" [1] View student information%n" +
				" [2] Add student information%n" +
				" [3] Edit student information%n" + 
				" [4] Return to main menu%n");
		System.out.println("---------------------------------");
		System.out.println("Select an option above :");
	}
	
	public static void courseMenu() {
		System.out.println("------ Course Information ------");
		System.out.printf(" [1] View courses%n" +
				" [2] Add to courses%n" +
				" [3] Edit courses%n" +
				" [4] Delete courses%n" + 
				" [5] Return to main menu%n");
		System.out.println("--------------------------------");
		System.out.println("Select an option above :");
	}
	
	public static void courseRegistrationMenu() {
		System.out.println("------ Course Registration ------");
		System.out.printf(" [1] View course registration%n" +
				" [2] Register for a course%n" +
				" [3] Cancel course registration%n" +
				" [4] Return to main menu%n");
		System.out.println("---------------------------------");
		System.out.println("Select an option above :");
	}
}
