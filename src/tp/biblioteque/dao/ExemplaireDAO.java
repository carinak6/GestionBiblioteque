package tp.biblioteque.dao;

import java.util.List;

import tp.biblioteque.model.Exemplaire;

public interface ExemplaireDAO {
	void ajouter(Exemplaire exemplaire);
	List<Exemplaire> lister();
	void modifier(Exemplaire exemplaire);
	void suprimer(Exemplaire exemplaire);
	
}
