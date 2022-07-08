package demoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnexionJdbc {
	
	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PW;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("database");
		DB_URL = bundle.getString("database.url");
		DB_USER = bundle.getString("database.username");
		DB_PW = bundle.getString("database.password");
	}

	public static void main(String[] args) {
		
		//String driverName = monFichierConf.getString("database.driver");
		//String dbUrl = monFichierConf.getString("database.url");
		//String dbUser = monFichierConf.getString("database.username");
		//String dbPw = monFichierConf.getString("database.password");
		try {
			Connection connection1 = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			System.out.println("Connection ok !\n");
			System.out.println("connection : " + connection1 + "\n");
			connection1.close();
		} 
		catch (SQLException e) {
			System.out.println("Connection ko !");
		}
	}

}
