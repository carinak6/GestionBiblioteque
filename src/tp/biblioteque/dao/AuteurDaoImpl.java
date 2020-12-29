package tp.biblioteque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tp.biblioteque.model.Auteur;

public class AuteurDaoImpl implements AuteurDAO{
	
	private DAOContext daoContext;
	//variables utlises dans tous les methodes alors je les ai sortie
	private Connection connexion=null;
	private PreparedStatement preparedStatement = null;
	
			public AuteurDaoImpl(DAOContext daoContext) {
				this.daoContext = daoContext;
				
				try {
					this.connexion = this.daoContext.getConnection();
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans getConnection , catch Exception e ");
					e.printStackTrace();
				}
				
			}

			@Override
			public void ajouter(Auteur auteur) {
				// TODO Auto-generated method stub
				
			}
		
			@Override
			public List<Auteur> lister() {
				List<Auteur> listeAuteur = new ArrayList<Auteur>();
				
				String query = "SELECT * FROM auteurs";
				
				Statement smt =null;
				
				try {
					smt = this.connexion.createStatement();
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans createStatement , catch Exception e ");
					e.printStackTrace();
				}
				
				ResultSet resultats = null;
				
				 try {
					resultats = smt.executeQuery(query);
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans executeQuery , catch Exception e ");
					e.printStackTrace();
				}
				 
				 //preparation du return
				 try {
					while(resultats.next()) {
						 Auteur objAuteur = new Auteur();
						 objAuteur.setIdAuteur(resultats.getInt("idAuteur"));
						 objAuteur.setNomAuteur(resultats.getString("nomAuteur"));
						 objAuteur.setPrenomAuteur(resultats.getString("prenomAuteur"));
						 
						 listeAuteur.add(objAuteur);
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
				return listeAuteur;
			}
		
			@Override
			public void modifier(Auteur auteur) {
				// TODO Auto-generated method stub
				
			}
		
			@Override
			public void suprimer(Auteur auteur) {
				// TODO Auto-generated method stub
				
			}
		
			@Override
			public Auteur chercherID(int isbn) {
				// TODO Auto-generated method stub
				return null;
			}

}
