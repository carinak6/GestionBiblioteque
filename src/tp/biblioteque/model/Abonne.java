package tp.biblioteque.model;

import java.time.LocalDate;

public class Abonne {
	private int codMatricule;
	private String nom;
	private String prenom;
	private String adresse;
	private LocalDate dateNaissance;
	private LocalDate dateAdhesion;
	private int codCategorieProf;
	private String libelleCategProf;	

	public Abonne() {
		this.codMatricule = 0;
		this.nom = "";
		this.prenom = "";
		this.adresse = "";
		this.dateNaissance = null;
		this.dateAdhesion = null;
		this.libelleCategProf = "";
	}
	
	
	public Abonne(int codMatricule, String nom, String prenom, String adresse, LocalDate dateNaissance,
			LocalDate dateAdhesion, String libelleCategProf) {
		
		this.codMatricule = codMatricule;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
		this.dateAdhesion = dateAdhesion;
		this.libelleCategProf = libelleCategProf;
	}
	
	public int getCodMatricule() {
		return codMatricule;
	}
	public void setCodMatricule(int codMatricule) {
		this.codMatricule = codMatricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public LocalDate getDateAdhesion() {
		return dateAdhesion;
	}
	public void setDateAdhesion(LocalDate dateAdhesion) {
		this.dateAdhesion = dateAdhesion;
	}
	
	public String getLibelleCategProf() {
		return libelleCategProf;
	}

	public void setLibelleCategProf(String libelleCategProf) {
		this.libelleCategProf = libelleCategProf;
	}

	public int getCodCategorieProf() {
		return codCategorieProf;
	}


	public void setCodCategorieProf(int codCategorieProf) {
		this.codCategorieProf = codCategorieProf;
	}


	@Override
	public String toString() {
		return "Abonne [codMatricule=" + codMatricule + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse
				+ ", dateNaissance=" + dateNaissance + ", dateAdhesion=" + dateAdhesion + ", codCategorieProf="
				+ codCategorieProf + ", libelleCategProf=" + libelleCategProf + "]";
	}
	
	
	

	
}
