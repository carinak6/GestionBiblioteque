package tp.biblioteque.model;

public class Catalogue {
	private int codCatalogue;
	private String libelleCatalogue;
	
	public int getCodCatalogue() {
		return codCatalogue;
	}
	public void setCodCatalogue(int codCatalogue) {
		this.codCatalogue = codCatalogue;
	}
	public String getLibelleCatalogue() {
		return libelleCatalogue;
	}
	public void setLibelleCatalogue(String libelleCatalogue) {
		this.libelleCatalogue = libelleCatalogue;
	}
	
	@Override
	public String toString() {
		return "Catalogue [codCatalogue=" + codCatalogue + ", libelleCatalogue=" + libelleCatalogue + "]";
	}
	
	

}
