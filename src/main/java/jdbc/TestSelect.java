package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entites.Fournisseur;

public class TestSelect {

	private static final String CL_URL;
	private static final String CL_USER;
	private static final String CL_PW;
	
	private static final String REQ_INSERT_FOU = "SELECT * FROM FOURNISSEUR";
	
	private static List<Fournisseur> fournisseurs = new ArrayList<>();
	
	static {

		ResourceBundle bundleCloud = ResourceBundle.getBundle("cloud");
		CL_URL = bundleCloud.getString("cloud.url");
		CL_USER = bundleCloud.getString("cloud.user");
		CL_PW = bundleCloud.getString("cloud.pw");
	}
	
	public static void main(String[] args) {
		
		try (Connection connection = DriverManager.getConnection(CL_URL, CL_USER, CL_PW);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(REQ_INSERT_FOU)) {
			while (rs.next()) {
				Integer id = rs.getInt("ID");
				String nom = rs.getString("NOM");
				Fournisseur fou = new Fournisseur(id, nom);
				fournisseurs.add(fou);
			}
			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(fournisseurs.size() > 0) {
			System.out.println("Liste des fournisseurs chargée : \n");
			for(Fournisseur fou : fournisseurs) {
				//System.out.println(fou.getId() + " / " + fou.getNom());
				System.out.println(fou.toString());
			}
		}

	}

}
