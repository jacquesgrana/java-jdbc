package test02.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test02.bo.Model;
import test02.bo.Fournisseur;
import test02.dal.IFournisseurDAO;
import test02.dal.PersistenceManager;
import test02.ihm.Vue;

public class Controller {

	private Vue vue;
	private Model model;
	
	
	public void init() {
		this.vue = new Vue();
		this.model = new Model();
		vue.init();
		model.init();
	}
	
	public void run() {
		IFournisseurDAO dao = PersistenceManager.getFournisseurDAO();
		try {
			List<Fournisseur> fournisseurs = dao.findAll();
			
			for(Fournisseur fou : fournisseurs) {
				System.out.println(fou.getId() + " / " + fou.getNom());
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PersistenceManager.closeJDBCConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vue.displayQuitMessage();
	}
	/**
	 * @return the vue
	 */
	public Vue getVue() {
		return vue;
	}
	/**
	 * @param vue the vue to set
	 */
	public void setVue(Vue vue) {
		this.vue = vue;
	}
	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	
	
}
