package tp.biblioteque.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livre {
	private long isbn;
	private int codCatalogue;
	private String titre;
	private LocalDate dateParution;
	private int IdAuteur;
	private List<Integer> listeMotsCles;
	
	public Livre() {
		this.isbn =0;
		this.codCatalogue=0;
		this.titre="";
		this.dateParution=LocalDate.now();
		this.IdAuteur =0;
		this.listeMotsCles = new ArrayList<>();
	}
	
	
	public List<Integer> getListeMotsCles() {
		return listeMotsCles;
	}


	public void setListeMotsCles(List<Integer> list) {
		this.listeMotsCles = list;
	}


	public int getIdAuteur() {
		return IdAuteur;
	}


	public void setIdAuteur(int idAuteur) {
		IdAuteur = idAuteur;
	}


	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long l) {
		this.isbn = l;
	}
	public int getCodCatalogue() {
		return codCatalogue;
	}
	public void setCodCatalogue(int codCatalogue) {
		this.codCatalogue = codCatalogue;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public LocalDate getDateParution() {
		return dateParution;
	}
	public void setDateParution(LocalDate dateParution) {
		this.dateParution = dateParution;
	}


	@Override
	public String toString() {
		return "Livre [isbn=" + isbn + ", codCatalogue=" + codCatalogue + ", titre=" + titre + ", dateParution="
				+ dateParution + ", IdAuteur=" + IdAuteur + ", listeMotsCles=" + listeMotsCles + "]";
	}


	
	
}
