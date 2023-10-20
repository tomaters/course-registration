/* The [Controller] package provides a link between the Model and the View packages
    - It takes input and translates it into commands
    - It handles user interactions, processes requests, and updates the Model accordingly
    - It communicates with changes in the Model to the View
  */
package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// The database utility class provides methods to establish and manage connections, execute queries, and more
public class DBUtil {
	// Properties; connect to Oracle database using account details from properties file
	public static Connection makeConnection() throws Exception {
		Properties properties = new Properties();
		Connection connection = null;
		try {
			FileInputStream fileInputStream = new FileInputStream("src/config/db.properties");
			properties.load(fileInputStream);
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (IOException | ClassNotFoundException | SQLException e){
			System.out.printf("Error: %s", e);
		}
		return connection;
	}
}
