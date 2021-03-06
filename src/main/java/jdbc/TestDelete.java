package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestDelete {

	private static final String CL_URL;
	private static final String CL_USER;
	private static final String CL_PW;
	
	private static final String REQ_DELETE_FOU = "DELETE FROM FOURNISSEUR WHERE NOM='L''''espace création'"; //VALUES (4, 'La Maison de la Peinture')
	
	static {

		ResourceBundle bundleCloud = ResourceBundle.getBundle("cloud");
		CL_URL = bundleCloud.getString("database.url");
		CL_USER = bundleCloud.getString("database.user");
		CL_PW = bundleCloud.getString("database.pw");
	}
	
	
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(CL_URL, CL_USER, CL_PW);
				Statement st = connection.createStatement();) {
			int result = st.executeUpdate(REQ_DELETE_FOU);
			
			if(result == 1) {
				System.out.println("Delete ok");
			}
			else {
				System.out.println("Delete ko");
			}
			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}

	}

}
