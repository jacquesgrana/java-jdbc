package test02.dal;

import java.sql.SQLException;
import java.util.List;

import test02.bo.Fournisseur;

public interface IFournisseurDAO {
	
	public List<Fournisseur> findAll() throws SQLException;

}
