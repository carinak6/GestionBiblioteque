package tp.biblioteque.dao;

import java.util.List;

import tp.biblioteque.model.Livre;

public interface LivreDAO {
	void ajouter(Livre livre);
	List<Livre> lister();
	void modifier(Livre livre);
	void suprimer(Livre livre);
}
