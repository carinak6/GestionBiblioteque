package tp.biblioteque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import tp.biblioteque.model.Livre;
import tp.biblioteque.model.LivreParMotsCles;

public class LivreParMotsClesDaoImpl implements LivreParMotsClesDAO  {

	private DAOContext daoContext;	
	//variables utlises dans tous les methodes alors je les ai sortie
	private Connection connexion = null;
	private PreparedStatement preparedStatement = null;
			
			public LivreParMotsClesDaoImpl( DAOContext daoContext) {
					this.daoContext = daoContext;
					
					try {
						this.connexion = this.daoContext.getConnection();
					} catch (SQLException e) {
						System.out.println("Un erreur se ha produit dans getConnection , catch Exception e ");
						e.printStackTrace();
					}
			}
		
			@Override
			public void ajouter(LivreParMotsCles motscles) {
//				System.out.println("Entra à ajouter");	
				
				try {
					//this.connexion =  obj_DaoContext.getConnection();
					//this.connexion = this.daoContext.getConnection();
					this.preparedStatement = this.connexion.prepareStatement("INSERT INTO livreParMotsCles Values (?,?)");
					this.preparedStatement.setInt(1, motscles.getIdMotCles());
					this.preparedStatement.setLong(2, motscles.getIsbn());
					
					this.preparedStatement.executeUpdate();
					
					System.out.println("Creation Reussi des mots par livre   !!!!!!!");	
					
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
		
			@Override
			public boolean existe(LivreParMotsCles motscles) {
				
				try {
					
					this.connexion = this.daoContext.getConnection();
					// create the preparedstatement and add the criteria
					this.preparedStatement = connexion.prepareStatement("SELECT * FROM livreParMotsCles WHERE idMotCles = ? && isbn =? ");
					this.preparedStatement.setInt(1, motscles.getIdMotCles());
					this.preparedStatement.setLong(2, motscles.getIsbn());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				// process the results				
				ResultSet resultats = null; //Class qui reçois les resultats de la requete
				
				try {
					resultats = this.preparedStatement.executeQuery();
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans executeQuery , catch Exception e ");
					e.printStackTrace();
				}
				
				
				try {
	                //on prepare la variable que retournera le resultat de la liste
					//premier partie de recuperation des donnes des libre
					if (resultats.next()) {
						System.out.println("cet mots cles existe");
						return true;
												
					}
					
					
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans le resultat , catch Exception e ");
					e.printStackTrace();
				}	
				
				
				System.out.println("cet mots cles n existe pas");
				return false;
			}

}
