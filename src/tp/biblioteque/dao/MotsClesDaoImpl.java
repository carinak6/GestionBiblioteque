package tp.biblioteque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import tp.biblioteque.model.MotsCles;

public class MotsClesDaoImpl implements MotsClesDAO {
	private DAOContext daoContext;	
	//variables utlises dans tous les methodes alors je les ai sortie
	private Connection connexion = null;
	private PreparedStatement preparedStatement = null;
	
			public MotsClesDaoImpl( DAOContext daoContext) {
				this.daoContext = daoContext;
				
				try {
					this.connexion = this.daoContext.getConnection();
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans getConnection , catch Exception e ");
					e.printStackTrace();
				}
			}

			@Override
			public List<MotsCles> lister() {
				List<MotsCles> ListeMotscles = new ArrayList<MotsCles>();
				
						
				Connection connexion = null;			
				
				DAOContext obj_DaoContext = new DAOContext();/*class pour connecte à la BDD*/
				
				try {
					connexion = obj_DaoContext.getConnection();//permet de obtenir une connexion BDD, cree dans DAOContext
				} catch (SQLException e1) {
					System.out.println("Un erreur se ha produit dans getConnection , catch Exception e ");
					e1.printStackTrace();
				}	
				
				
				String query="SELECT * FROM motscles";/*requete*/
				
				Statement smt = null; 
				try {
						smt = connexion.createStatement();//permet de executer requetes simples
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans createStatement , catch Exception e ");
					e.printStackTrace();
				}
				
				ResultSet resultats = null; //reçois les resultats de la requete
				
			 	try {
			 		resultats = smt.executeQuery(query) ;
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans executeQuery , catch Exception e ");
					e.printStackTrace();
				} 
			 	
			 	try {
		//Remplir une liste de pays à partir de la réponse de la requête précédente
					while (resultats.next()) {
					 	/* pour verifier si la requete s execute correctement
					 	 * System.out.println(rs.getString("Nom") + " "+rs.getString("Prenom") ) ;*/
						MotsCles obj_mots = new MotsCles();
						obj_mots.setIdMotCle(resultats.getInt("idMotCle"));
						obj_mots.setLibelleMotCle(resultats.getString("libelleMotCle"));
						ListeMotscles.add(obj_mots);
					}
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans le resultat , catch Exception e ");
					e.printStackTrace();
				}	
			 	
		
				 return ListeMotscles;
			}

}
