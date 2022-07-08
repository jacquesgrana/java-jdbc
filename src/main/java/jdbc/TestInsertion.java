package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestInsertion {

	private static final String CL_URL;
	private static final String CL_USER;
	private static final String CL_PW;
	
	private static final String REQ_INSERT_FOU = "INSERT INTO FOURNISSEUR VALUES (4, 'La Maison de la Peinture')";
	
	static {

		ResourceBundle bundleCloud = ResourceBundle.getBundle("cloud");
		CL_URL = bundleCloud.getString("cloud.url");
		CL_USER = bundleCloud.getString("cloud.user");
		CL_PW = bundleCloud.getString("cloud.pw");
	}
	
	public static void main(String[] args) {
		
		try (Connection connection = DriverManager.getConnection(CL_URL, CL_USER, CL_PW);
				Statement st = connection.createStatement();) {
			int result = st.executeUpdate(REQ_INSERT_FOU);
			
			if(result == 1) {
				System.out.println("Insertion ok");
			}
			else {
				System.out.println("Insertion ko");
			}
			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
	}

}
