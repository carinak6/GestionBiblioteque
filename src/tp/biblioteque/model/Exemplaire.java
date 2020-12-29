package tp.biblioteque.model;

import java.time.LocalDate;

public class Exemplaire {
	private int idExemplaire;
	private int isbn;
	private int codRayon;
	private int codUsure;
	private String libelleExemplaire;
	private LocalDate dateAchat;
	private int idEditeur;
	
	public Exemplaire() {
		this.idExemplaire=0;
		this.isbn=0;
		this.codRayon=0;
		this.codUsure=0;
		this.libelleExemplaire="";
		this.dateAchat=LocalDate.now();
		this.idEditeur=0;
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getCodRayon() {
		return codRayon;
	}

	public void setCodRayon(int codRayon) {
		this.codRayon = codRayon;
	}

	public int getCodUsure() {
		return codUsure;
	}

	public void setCodUsure(int codUsure) {
		this.codUsure = codUsure;
	}

	public String getLibelleExemplaire() {
		return libelleExemplaire;
	}

	public void setLibelleExemplaire(String libelleExemplaire) {
		this.libelleExemplaire = libelleExemplaire;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public int getIdEditeur() {
		return idEditeur;
	}

	public void setIdEditeur(int idEditeur) {
		this.idEditeur = idEditeur;
	}

	@Override
	public String toString() {
		return "Exemplaires [idExemplaire=" + idExemplaire + ", isbn=" + isbn + ", codRayon=" + codRayon + ", codUsure="
				+ codUsure + ", libelleExemplaire=" + libelleExemplaire + ", dateAchat=" + dateAchat + ", idEditeur="
				+ idEditeur + "]";
	}
	
}
