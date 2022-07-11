package test02.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test02.bo.Fournisseur;

public class FournisseurJDBCDAO implements IFournisseurDAO {
	
	private static final String FIND_ALL_QUERY = "SELECT * FROM FOURNISSEUR";

	@Override
	public List<Fournisseur> findAll() throws SQLException{
		List<Fournisseur> fournisseurs = new ArrayList<>();
		Connection connection = PersistenceManager.getJDBCConnection();
		try(
			PreparedStatement st = connection.prepareStatement(FIND_ALL_QUERY);
			ResultSet rs = st.executeQuery();
			){
			
			while(rs.next()) {
				//System.out.println(rs.getInt("ID") + " / " + rs.getString("NOM"));
				fournisseurs.add(new Fournisseur(rs.getInt("ID"), rs.getString("NOM")));
			}
			//System.out.println("longueur liste : " + fournisseurs.size());
			
		}
		return fournisseurs;
	}

}
