package tp.biblioteque.dao;

import java.util.ArrayList;
import tp.biblioteque.model.LivreParMotsCles;

public interface LivreParMotsClesDAO {
	public void ajouter(LivreParMotsCles mots);
	//public ArrayList<LivreParMotsCles> chercherISBN(Long isbn);
	//public ArrayList<LivreParMotsCles> chercherMotsCles(int idMotsCles);
	public boolean existe(LivreParMotsCles mots);
	//public void modifier(LivreParMotsCles mots);
	//public void suprimer(LivreParMotsCles mots);
}
