package controller.RegisterManagers;

import java.util.Scanner;

import controller.DAO.MajorDAO;
import model.MajorVO;

public class MajorRegisterManager {

	public static Scanner scan = new Scanner(System.in);
	public static void viewMajorInfo() throws Exception {
		MajorDAO majorDAO = new MajorDAO();
		System.out.println("Displaying list of majors");
		System.out.println("-------------------------------------------------------------");
		majorDAO.viewMajorInfo();
		System.out.println("-------------------------------------------------------------");
	}
	
	public static void addMajorInfo() throws Exception {
		System.out.println("Add major information");
		MajorDAO majorDAO = new MajorDAO();
		MajorVO majorVO = new MajorVO();
		String major_code = null;
		String major_name = null;
		// display list of majors
		System.out.println("Displaying list of majors");
		System.out.println("-------------------------------------------------------------");
		majorDAO.viewMajorInfo();
		System.out.println("-------------------------------------------------------------");
		// input new major information
		System.out.println("Enter major information to add");
		System.out.println("Major code:");
		major_code = scan.nextLine();
		System.out.println("Name of major:");
		major_name = scan.nextLine();
		// save inputs into Value Object variables
		majorVO.setMajor_code(major_code);
		majorVO.setMajor_name(major_name);
		// call add major function in database
		majorDAO.addMajorInfo(majorVO);
		//display changed list of majors
		System.out.println("Displaying list of majors");
		System.out.println("-------------------------------------------------------------");
		majorDAO.viewMajorInfo();
		System.out.println("-------------------------------------------------------------");
	}
	
	public static void editMajorInfo() throws Exception {
		System.out.println("Edit major information");
		MajorDAO majorDAO = new MajorDAO();
		MajorVO majorVO = new MajorVO();
		int serial_num = 0;
		String major_code = null;
		String major_name = null;
		// display list of majors
		System.out.println("Displaying list of majors");
		System.out.println("-------------------------------------------------------------");
		majorDAO.viewMajorInfo();
		System.out.println("-------------------------------------------------------------");
		// input serial number of major to edit
		System.out.println("Enter serial number of the major to edit:");
		serial_num = scan.nextInt();
		scan.nextLine(); // clear buffer
		// input new data to replace that of tuple
		System.out.println("Enter changes");
		System.out.println("Major code:");
		major_code = scan.nextLine();
		System.out.println("Major name:");
		major_name = scan.nextLine();
		// save inputs
		majorVO.setSerial_num(serial_num);
		majorVO.setMajor_code(major_code);
		majorVO.setMajor_name(major_name);
		// call major function in database
		majorDAO.editMajorInfo(majorVO);
		//display changed list of majors
		System.out.println("Displaying list of majors");
		System.out.println("-------------------------------------------------------------");
		majorDAO.viewMajorInfo();
		System.out.println("-------------------------------------------------------------");
		
	}
	
	public static void deleteMajorInfo() throws Exception{
		System.out.println("Delete major information");
		MajorDAO majorDAO = new MajorDAO();
		int serial_num = 0;
		//display changed list of majors
		System.out.println("Displaying list of majors");
		System.out.println("-------------------------------------------------------------");
		majorDAO.viewMajorInfo();
		System.out.println("-------------------------------------------------------------");
		// input serial number of major to delete
		System.out.println("Enter serial number of the major to delete:");
		serial_num = scan.nextInt();
		scan.nextLine(); // clear buffer
		// call major function in database
		majorDAO.deleteMajorInfo(serial_num);
		//display changed list of majors
		System.out.println("Displaying list of majors");
		System.out.println("-------------------------------------------------------------");
		majorDAO.viewMajorInfo();
		System.out.println("-------------------------------------------------------------");
	}
}
