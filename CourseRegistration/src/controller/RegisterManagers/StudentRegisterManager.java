package controller.RegisterManagers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controller.DAO.MajorDAO;
import controller.DAO.StudentDAO;
import model.StudentVO;

public class StudentRegisterManager {

	public static Scanner scan = new Scanner(System.in);
	
	public static void viewStudentInfo() throws Exception {
		
	}
	
	public static void addStudentInfo() throws Exception {
		MajorDAO majorDAO = new MajorDAO();
		StudentDAO studentDAO = new StudentDAO();
		StudentVO studentVO = new StudentVO();
		
		String student_num, student_name, student_id, student_pass, major_code, student_bday = null;
		String student_phone, student_address, student_email, year = null;
		boolean usernameOverlaps = false;
		// input student information to add new account
		System.out.println("----- Enter student information -----");
		System.out.println("Name:");
		student_name = scan.nextLine();
		// do-while loop to check if username already exists
		do {
			System.out.println("Enter username (8-12 characters):");
			student_id = scan.nextLine();
			usernameOverlaps = studentDAO.doesUsernameOverlap(student_id);
		} while(usernameOverlaps);
		System.out.println("That username is available");
		// enter new password
		System.out.println("Enter password (maximum 12 characters):");
		// check that password is not over 12 characters
		while(true) {
			student_pass = scan.nextLine();
			if(student_pass.length() < 13) {
				break;
			}
		}
		// student number is automatically constructed: year + major code + serial number
		// shows list of majors and their codes; select major by code
		majorDAO.displayListOfMajors();
		System.out.println("Enter major code:");
		major_code = scan.nextLine();
		// format date into last two digits of year to make first part of student id
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy");
		// Date from java.util, not SQL
		year = simpleDateFormat.format(new Date());
		// create student number
		student_num = year + major_code + studentDAO.studentCount(major_code);
		// input birthday
		System.out.println("Enter birthday (YY/MM/DD):");
		student_bday = scan.nextLine();
		System.out.println("Enter phone number:");
		student_phone = scan.nextLine();
		System.out.println("Enter address:");
		student_address = scan.nextLine();
		System.out.println("Enter email address:");
		student_email = scan.nextLine();
		
		// save information into Student Value Object
		studentVO.setStudent_num(student_num);
		studentVO.setStudent_name(student_name);
		studentVO.setStudent_id(student_id);
		studentVO.setStudent_pass(student_pass);
		studentVO.setMajor_code(major_code);
		studentVO.setStudent_bday(student_bday);
		studentVO.setStudent_phone(student_phone);
		studentVO.setStudent_address(student_address);
		studentVO.setStudent_email(student_email);		
		// save information to Student Data Access Object
		studentDAO.addStudentInfo(studentVO);
		System.out.println("Student information successfully added");
		// direct to login to see info
		studentDAO.viewStudentInfo(studentVO.getStudent_id(), studentVO.getStudent_pass());
		}
	
	public static void editStudentInfo() throws Exception {
		StudentDAO studentDAO = new StudentDAO();
		StudentVO studentVO = new StudentVO();
		
		String username, password = null;
		String student_num, student_pass, student_phone, student_address, student_email = null;
		
		boolean checkLogin = false;
		
		System.out.println("Edit student information");
		// do-while statement to allow login attempts
		do {
			System.out.println("Username:");
			username = scan.nextLine();
			System.out.println("Password:");
			password = scan.nextLine();
			// call method to check login credentials; if true, exit loop
			checkLogin = studentDAO.studentLogin(username, password);
			if(!checkLogin) {
				System.out.println("The username and password you entered is incorrect");
			}
		} while(!checkLogin);
		// stores student number into student_num if id/pass is correct
		student_num = studentDAO.getStudentNumber(username, password);
		System.out.println("Select student information to edit");
		System.out.println("Student number: " + student_num);
		System.out.println("Password (max 12 characters):");
		student_pass = scan.nextLine();
		System.out.println("Phone number: ");
		student_phone = scan.nextLine();
		System.out.println("Address:");
		student_address = scan.nextLine();
		System.out.println("Email");
		student_email = scan.nextLine();
		// store inputs into Value Object
		studentVO.setStudent_num(student_num);
		studentVO.setStudent_pass(student_pass);
		studentVO.setStudent_phone(student_phone);
		studentVO.setStudent_address(student_address);
		studentVO.setStudent_email(student_email);
		// call update SQL function
		studentDAO.editStudentInfo(studentVO);
		// display info after change
		System.out.println("Student information changed. Results:");
		studentDAO.viewStudentInfo(username, studentVO.getStudent_pass());
		
	}
	
	public void viewAllStudents() throws Exception {
		// admin function to view information of all students
		StudentDAO studentDAO = new StudentDAO();
		String password = null;
		System.out.println("Enter admin password");
		password = scan.nextLine();
		
		if(password.equals("admin1234")) {
			studentDAO.viewAllStudents();
		} else {
			System.out.println("The password you entered is incorrect");
		}
	}	
}
