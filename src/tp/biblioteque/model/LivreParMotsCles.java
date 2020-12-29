package tp.biblioteque.model;

public class LivreParMotsCles {
	private Long isbn;
	private int idMotCles;
	
	public LivreParMotsCles() {
		
	}
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public int getIdMotCles() {
		return idMotCles;
	}
	public void setIdMotCles(int idMotCles) {
		this.idMotCles = idMotCles;
	}
	
	@Override
	public String toString() {
		return "LivreParMotsCles [isbn=" + isbn + ", idMotCles=" + idMotCles + "]";
	}
	//livreparmotscles
}
