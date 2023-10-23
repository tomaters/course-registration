package controller.RegisterManagers;

import java.util.Scanner;

import controller.DAO.CourseDAO;
import model.CourseVO;

public class CourseRegisterManager {

	public static Scanner scan = new Scanner(System.in);
	
	public static void viewCourse() throws Exception {
		CourseDAO courseDAO = new CourseDAO();
		System.out.println("View courses");
		courseDAO.viewCourses();
	}
	
	public static void addCourse() throws Exception {
		CourseDAO courseDAO = new CourseDAO();
		CourseVO courseVO = new CourseVO();
		String course_abv;
		String course_name;
		System.out.println("View courses");
		courseDAO.viewCourses();
		System.out.println("Enter course information");
		System.out.print("Course abbreviation : ");
		course_abv = scan.nextLine();
		System.out.print("Course name : ");
		course_name = scan.nextLine();
		courseVO.setCourse_abv(course_abv);
		courseVO.setCourse_name(course_name);
		courseDAO.addCourse(courseVO);

		System.out.println("View courses");
		courseDAO.viewCourses();
		System.out.println();
	}
	
	public static void editCourse() throws Exception {
		CourseDAO courseDAO = new CourseDAO();
		CourseVO courseVO = new CourseVO();
		int serial_num = 0;
		String course_abv, course_name = null;
		
		System.out.println("View course list (Cannot edit course that is in use)");
		courseDAO.viewCourses();
		System.out.println("Enter serial number of course to edit");
		System.out.print("Serial number : ");
		serial_num = scan.nextInt();
		scan.nextLine(); // clear buffer
		System.out.println("Enter all new information");
		System.out.print("Course abbreviation:");
		course_abv = scan.nextLine();
		System.out.print("Course name : ");
		course_name = scan.nextLine();
		courseVO.setSerial_num(serial_num);;
		courseVO.setCourse_abv(course_abv);;
		courseVO.setCourse_name(course_name);;
		courseDAO.editCourse(courseVO);
		System.out.println("View courses");
		courseDAO.viewCourses();
	}
	
	public static void deleteCourse() throws Exception {
		CourseDAO courseDAO = new CourseDAO();
		int serial_num = 0;
		System.out.println("View course list(Cannot edit course that is in use)");
		courseDAO.viewCourses();
		System.out.println("Enterial serial number of course to delete");
		System.out.print("Serial number : ");
		serial_num = scan.nextInt();
		scan.nextLine(); // clear buffer
		courseDAO.deleteCourse(serial_num);
		System.out.println("View courses");
		courseDAO.viewCourses();
	}
}
