package main;

import controller.RegisterManagers.CourseCatalogRegisterManager;
import controller.RegisterManagers.CourseRegisterManager;
import controller.RegisterManagers.MajorRegisterManager;
import controller.RegisterManagers.StudentRegisterManager;
import view.MenuViewer;
import view.Selection.CourseCatalogSelection;
import view.Selection.CourseSelection;
import view.Selection.MajorSelection;
import view.Selection.StudentSelection;
import view.Selection.MenuSelection;

public class CourseRegistrationMain {

	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu() {
		int input = 0;
		
		while(true) {
			try {
				MenuViewer.viewMainMenu();
				input = MenuViewer.scan.nextInt();
				MenuViewer.scan.nextLine(); // clear buffer in MenuViewer
				
				switch(input) {
					case MenuSelection.major : majorMenu(); break;
					case MenuSelection.student : studentMenu(); break;
					case MenuSelection.course : courseMenu(); break;
					case MenuSelection.courseCatalog : courseCatalogMenu(); break;
					case MenuSelection.exit : System.out.println("Closing program");
					return;
				}				
			} catch(Exception e) {
				System.out.println("Error: please try again");
				return;
			}
		}
	}

	private static void majorMenu() throws Exception {
		int input = 0;
		
		MajorRegisterManager majorRegisterManager = new MajorRegisterManager();
		// call method to retrieve number
		MenuViewer.majorMenu();
		input = MenuViewer.scan.nextInt();
		MenuViewer.scan.nextLine(); // clear buffer
		
		switch(input) {
		case MajorSelection.viewMajorInfo : 
			majorRegisterManager.viewMajorInfo();
			break;
		case MajorSelection.addMajorInfo :
			majorRegisterManager.addMajorInfo();
			break;
		case MajorSelection.editMajorInfo :
			majorRegisterManager.editMajorInfo();
			break;
		case MajorSelection.deleteMajorInfo :
			majorRegisterManager.deleteMajorInfo();
			break;
		case MajorSelection.returnToMenu : 
			System.out.println("Returning to main menu");
			return;
		default : System.out.println("Please enter a number in the menu");
		}
	}

	private static void studentMenu() throws Exception {
		int input = 0;
		
		StudentRegisterManager studentRegisterManager = new StudentRegisterManager();
		// call method to retrieve number
		MenuViewer.studentMenu();
		input = MenuViewer.scan.nextInt();
		MenuViewer.scan.nextLine(); // clear buffer
		
		switch(input) {
		case StudentSelection.viewStudentInfo :
			studentRegisterManager.viewStudentInfo();
			break;
		case StudentSelection.addStudentInfo :
			studentRegisterManager.addStudentInfo();
			break;
		case StudentSelection.editStudentInfo :
			studentRegisterManager.editStudentInfo();
		case StudentSelection.returnToMenu : 
			System.out.println("Returning to main menu");
			return;
			default : System.out.println("Please enter a number in the menu");
		}
	}

	private static void courseMenu() throws Exception {
		int input = 0;
		
		CourseRegisterManager courseRegisterManager = new CourseRegisterManager();
		// call method to retrieve number
		MenuViewer.courseMenu();
		input = MenuViewer.scan.nextInt();
		MenuViewer.scan.nextLine(); // clear buffer

		switch(input) {
		case CourseSelection.viewCourse : 
			System.out.println();
			courseRegisterManager.viewCourse();
			break;
		case CourseSelection.addCourse :
			courseRegisterManager.addCourse();
			break;
		case CourseSelection.editCourse :
			courseRegisterManager.editCourse();
			break;
		case CourseSelection.deleteCourse : 
			courseRegisterManager.deleteCourse();
		case CourseSelection.returnToMenu :
			System.out.println("Returning to main menu");
			return;
			default: System.out.println("Please enter a number in the menu");
		}
	}

	private static void courseCatalogMenu() throws Exception {
		int input = 0;
		
		CourseCatalogRegisterManager courseCatalogRegisterManager = new CourseCatalogRegisterManager();
		// call method to retrieve number
		MenuViewer.courseRegistrationMenu();
		input = MenuViewer.scan.nextInt();
		MenuViewer.scan.nextLine(); // clear buffer
		
		switch(input) {
		case CourseCatalogSelection.viewReg : 
			System.out.println();
			courseCatalogRegisterManager.viewRegistration();
			break;
		case CourseCatalogSelection.addReg :
			courseCatalogRegisterManager.addRegistration();
			break;
		case CourseCatalogSelection.cancelReg :
			courseCatalogRegisterManager.cancelRegistration();
			break;
		case CourseCatalogSelection.returnToMenu : 
			System.out.println("Returning to main menu");
			return;
			default: System.out.println("Please enter a number in the menu");
		}
	}
}
