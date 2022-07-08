package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestUpdate {

	private static final String CL_URL;
	private static final String CL_USER;
	private static final String CL_PW;
	
	private static final String REQ_UPDATE_FOU = "UPDATE FOURNISSEUR SET NOM='La Maison des Peintures' WHERE NOM='La Maison de la Peinture'"; //VALUES (4, 'La Maison de la Peinture')
	
	static {

		ResourceBundle bundleCloud = ResourceBundle.getBundle("cloud");
		CL_URL = bundleCloud.getString("cloud.url");
		CL_USER = bundleCloud.getString("cloud.user");
		CL_PW = bundleCloud.getString("cloud.pw");
	}
	
	
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(CL_URL, CL_USER, CL_PW);
				Statement st = connection.createStatement();) {
			int result = st.executeUpdate(REQ_UPDATE_FOU);
			
			if(result == 1) {
				System.out.println("Update ok");
			}
			else {
				System.out.println("Update ko");
			}
			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}

	}

}
