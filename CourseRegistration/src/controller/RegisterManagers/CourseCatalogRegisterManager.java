package controller.RegisterManagers;

import java.util.Scanner;

import controller.DAO.CourseCatalogDAO;
import controller.DAO.StudentDAO;
import model.CourseCatalogVO;

public class CourseCatalogRegisterManager {

	public static Scanner scan = new Scanner(System.in);
	
	public static void viewRegistration() throws Exception {
		String student_num, username, password = null;
		String mainMenu = null;
		boolean loginFlag = false;
		CourseCatalogDAO courseCatalogDAO = new CourseCatalogDAO();
		StudentDAO studentDAO = new StudentDAO();
		System.out.println("Login to view list of registered courses");
		do {
			System.out.print("Username: ");
			username = scan.nextLine();
			System.out.print("Password: ");
			password = scan.nextLine();
			loginFlag = studentDAO.studentLogin(username, password);
			if (!loginFlag) {
				System.out.println("The username and passord are incorrect");

				System.out.print("Enter 'X' to return to main menu");
				mainMenu = scan.nextLine();
				if (mainMenu.toLowerCase().equals("y")) return;				
			}
		} while (!loginFlag);
		
		student_num = studentDAO.getStudentNumber(username, password);
		System.out.println("Displaying list of registered courses");
		courseCatalogDAO.viewCourseCatalog(student_num);
	}
	
	public static void addRegistration() throws Exception {
		CourseCatalogDAO courseCatalogDAO = new CourseCatalogDAO();
		CourseCatalogVO courseCatalogVO = new CourseCatalogVO();
		StudentDAO studentDAO = new StudentDAO();
		String student_num, course_abv, course_type = null;
		String username, password, mainMenu = null;
		boolean loginFlag = false;
	
		System.out.println("Login to register for a course");
		do {
			System.out.print("Username: ");
			username = scan.nextLine();
			System.out.print("Password: ");
			password = scan.nextLine();
			loginFlag = studentDAO.studentLogin(username, password);
			if (!loginFlag) {
				System.out.println("The username and passord are incorrect");

				System.out.print("Enter 'X' to return to main menu");
				mainMenu = scan.nextLine();
				if (mainMenu.toLowerCase().equals("y")) return;		
			}
		} while (!loginFlag);
		
		System.out.printf("Displaying list of registered courses for SID: ");
		student_num = studentDAO.getStudentNumber(username, password);
		courseCatalogDAO.viewCourseCatalog(student_num);
		System.out.printf("[%s]%n" + student_num);
		
		System.out.print("Enter course abbreviation :");
		course_abv = scan.nextLine();
		System.out.print("Enter course type (Major, Elective, or ???): ");
		course_type = scan.nextLine();
		courseCatalogVO.setStudent_num(student_num);;;
		courseCatalogVO.setCourse_abv(course_abv);;
		courseCatalogVO.setCourse_type(course_type);;

		courseCatalogDAO.addRegistration(courseCatalogVO);
		System.out.println("Displaying list of courses");
		courseCatalogDAO.viewCourseCatalog(student_num);
	}
	
	public static void cancelRegistration() throws Exception {
		CourseCatalogDAO courseCatalogDAO = new CourseCatalogDAO();
		StudentDAO studentDAO = new StudentDAO();
		String student_num = null;
		int serial_num = 0;
		String username, password, mainMenu = null;
		boolean loginFlag = false;
		System.out.println("Login to cancel course");
		do {
			System.out.print("Username: ");
			username = scan.nextLine();
			System.out.print("Password: ");
			password = scan.nextLine();
			loginFlag = studentDAO.studentLogin(username, password);
			if (!loginFlag) {
				System.out.println("The username and passord are incorrect");

				System.out.print("Enter 'X' to return to main menu");
				mainMenu = scan.nextLine();
				if (mainMenu.toLowerCase().equals("y")) return;				
			}
		} while (!loginFlag);

		student_num = studentDAO.getStudentNumber(username, password);
		System.out.println("Displaying list of courses");
		courseCatalogDAO.viewCourseCatalog(student_num);
		System.out.println("Enter serial number of course to cancel: ");
		serial_num = scan.nextInt();
		scan.nextLine(); // clear buffer
		courseCatalogDAO.deleteCourse(serial_num);
		System.out.println("Displaying updated list of courses");
		courseCatalogDAO.viewCourseCatalog(student_num);
	}
}

