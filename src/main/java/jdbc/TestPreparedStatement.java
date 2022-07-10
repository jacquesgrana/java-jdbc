package jdbc;

import java.util.ArrayList;
import java.util.List;

import entites.Fournisseur;
import jdbc.dao.FournisseurDaoPrepStat;

public class TestPreparedStatement {

	public static void main(String[] args) {
		List<Fournisseur> fournisseurs = new ArrayList<>();
		FournisseurDaoPrepStat dao = new FournisseurDaoPrepStat();
		fournisseurs = dao.extraire();
		displayList(fournisseurs);
		
		
		int id = 4;
		String nom = "L'espace création";
		Fournisseur fou = new Fournisseur(id, nom);
		dao.insert(fou);
		fournisseurs = dao.extraire();
		displayList(fournisseurs);
		
		String nouveauNom = "Monoprix";
		dao.update(nom, nouveauNom);
		fournisseurs = dao.extraire();
		displayList(fournisseurs);
		
		fou.setNom(nouveauNom);
		dao.delete(fou);
		fournisseurs = dao.extraire();
		displayList(fournisseurs);
		
	}

	private static void displayList(List<Fournisseur> fournisseurs) {
		if (fournisseurs.size() > 0) {
			System.out.println("\nListe des fournisseurs : \n");
			for(Fournisseur fou : fournisseurs) {
				System.out.println(fou.toString());
			}
			System.out.println();
		}
		
	}

}
