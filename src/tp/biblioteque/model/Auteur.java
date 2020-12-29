package tp.biblioteque.model;

public class Auteur {
	private int idAuteur;
	private String nomAuteur;
	private String prenomAuteur;
			
			public Auteur() {
				this.idAuteur=0;
				this.nomAuteur="";
				this.prenomAuteur="";
			}

			public int getIdAuteur() {
				return idAuteur;
			}

			public void setIdAuteur(int idAuteur) {
				this.idAuteur = idAuteur;
			}

			public String getNomAuteur() {
				return nomAuteur;
			}

			public void setNomAuteur(String nomAuteur) {
				this.nomAuteur = nomAuteur;
			}

			
			public String getPrenomAuteur() {
				return prenomAuteur;
			}

			public void setPrenomAuteur(String prenomAuteur) {
				this.prenomAuteur = prenomAuteur;
			}

			@Override
			public String toString() {
				return "Auteur [idAuteur=" + idAuteur + ", nomAuteur=" + nomAuteur + ", prenomAuteur=" + prenomAuteur
						+ "]";
			}
			
}
