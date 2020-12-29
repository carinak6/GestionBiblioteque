package tp.biblioteque.dao;

import java.util.List;

import tp.biblioteque.model.Abonne;

public interface AbonneDAO {
	 void ajouter(Abonne abonne);
	 List<Abonne> lister();
	 void modifier(Abonne abonne);
	 void supprimer(Abonne abonne);
}
