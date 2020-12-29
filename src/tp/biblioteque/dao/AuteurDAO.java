package tp.biblioteque.dao;

import java.util.List;

import tp.biblioteque.model.Auteur;

public interface AuteurDAO {
	void ajouter(Auteur auteur);
	List<Auteur> lister();
	void modifier(Auteur auteur);
	void suprimer(Auteur auteur);
	Auteur chercherID(int isbn);
}
