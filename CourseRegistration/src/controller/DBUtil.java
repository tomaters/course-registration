/* The [Controller] package provides a link between the Model and the View packages
    - It takes input and translates it into commands
    - It handles user interactions, processes requests, and updates the Model accordingly
    - It communicates with changes in the Model to the View
  */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// The database utility class provides methods to establish and manage connections, execute queries, and more
public class DBUtil {
	// connect to Oracle database using account javauser
	public static Connection makeConnection() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
		String username = "javauser";
		String password = "java1234";
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, username, password);
//			System.out.println("connected (test)");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.printf("Error: %s", e);
		}
		return connection;
	}
}
