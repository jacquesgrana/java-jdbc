package demoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class TestConnexionJdbc {

	
	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PW;
	
	private static final String CL_URL;
	private static final String CL_USER;
	private static final String CL_PW;
	
	static {
		
		ResourceBundle bundle = ResourceBundle.getBundle("database");
		DB_URL = bundle.getString("database.url");
		DB_USER = bundle.getString("database.username");
		DB_PW = bundle.getString("database.password");
		
		ResourceBundle bundleCloud = ResourceBundle.getBundle("cloud");
		CL_URL = bundleCloud.getString("cloud.url");
		CL_USER = bundleCloud.getString("cloud.user");
		CL_PW = bundleCloud.getString("cloud.pw");
		//System.out.println("url : " + CL_URL + " / user : " + CL_USER + " / pw : " + CL_PW);
	}

	public static void main(String[] args) throws ClassNotFoundException {
		
		try {
			Connection connection1 = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			System.out.println("Connection locale ok !\n");
			System.out.println("connection : " + connection1 + "\n");
			connection1.close();
		} 
		catch (SQLException e) {
			System.out.println("Connection locale ko !");
		}
		
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection2 = DriverManager.getConnection(CL_URL, CL_USER, CL_PW);
			System.out.println("Connection cloud ok !\n");
			System.out.println("connection : " + connection2 + "\n");
			connection2.close();
		} 
		catch (SQLException e) {
			System.out.println("Connection cloud ko ! : " + e.toString());
		}
	}

}
