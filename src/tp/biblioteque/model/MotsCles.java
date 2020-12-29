package tp.biblioteque.model;

public class MotsCles {
	private int idMotCle;
	private String libelleMotCle;
	private Long isbn;
	
	
	public int getIdMotCle() {
		return idMotCle;
	}
	public void setIdMotCle(int idMotCle) {
		this.idMotCle = idMotCle;
	}
	public String getLibelleMotCle() {
		return libelleMotCle;
	}
	public void setLibelleMotCle(String libelleMotCle) {
		this.libelleMotCle = libelleMotCle;
	}
	@Override
	public String toString() {
		return "MotsCles [idMotCle=" + idMotCle + ", libelleMotCle=" + libelleMotCle + "]";
	}
	
	

}
