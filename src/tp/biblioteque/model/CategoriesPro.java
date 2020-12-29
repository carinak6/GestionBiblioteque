package tp.biblioteque.model;

public class CategoriesPro {
	private int codCategorieProf;
	private String libelleCategorie;
	
	public CategoriesPro() {
		
	}
	
	public int getCodCategorieProf() {
		return codCategorieProf;
	}
	public void setCodCategorieProf(int codCategorieProf) {
		this.codCategorieProf = codCategorieProf;
	}
	public String getLibelleCategorie() {
		return libelleCategorie;
	}
	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	@Override
	public String toString() {
		return "CategoriesPro [codCategorieProf=" + codCategorieProf + ", libelleCategorie=" + libelleCategorie + "]";
	}
	

}
