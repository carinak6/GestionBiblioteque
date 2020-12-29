package tp.biblioteque.dao;

import java.util.List;

import tp.biblioteque.model.Catalogue;

public interface CatalogueDAO {
	
	void ajouter(Catalogue catalogue);
	List<Catalogue> lister();
	void modifier(Catalogue catalogue);
	void suprimer(Catalogue catalogue);
	Catalogue chercherID(int idCatalogue);

}
