package tp.biblioteque.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tp.biblioteque.dao.DAOContext;
import tp.biblioteque.model.Abonne;

/** Cette Classe sers a implementer l interface AbonneDAO et à interchanger avec la BDD
 * */
public class AbonneDaoImpl implements AbonneDAO {
	
	private DAOContext daoContext;	
	//variables utlises dans tous les methodes alors je les ai sortie
	private Connection connexion = null;
	private PreparedStatement preparedStatement = null;
	
	
			public AbonneDaoImpl(DAOContext daoContext) {
				this.daoContext = daoContext;
				try {
					this.connexion = this.daoContext.getConnection();
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans getConnection , catch Exception e ");
					e.printStackTrace();
				}
			}

			@Override
			public List<Abonne> lister() {
				System.out.println("Entra à lister");
				List<Abonne> LAbonne = new ArrayList<Abonne>();				
				
								
				String query ="SELECT codMatricule,nom,prenom,adresse,dateNaissance,dateAdhesion,c.libelleCategorie as 'libelleCategorie' FROM abonnes a INNER JOIN categoriespro c ON a.codCategorieProf = c.codCategorieProf";
				//System.out.println("query " + query);
				
				Statement smt = null; 
				try {
						smt = this.connexion.createStatement();//permet de executer requetes simples
						
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans createStatement , catch Exception e ");
					e.printStackTrace();
				}
				
				ResultSet resultats = null; //Class qui reçois les resultats de la requete
				
			 	try {
			 		
			 		resultats = smt.executeQuery(query) ;
			 		
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans executeQuery , catch Exception e ");
					e.printStackTrace();
				} 
			 	
			 	
			 	try {
	                
					while (resultats.next()) {
					
						/* pour lever l'erreur du pointeur nullException j'ai du ajouter cette declaration des varaibles LocalDate et les passer au constructeur d Abonne */
						LocalDate dateNaiss = (resultats.getDate("dateNaissance") != null )?resultats.getDate("dateNaissance").toLocalDate() : LocalDate.now();
						LocalDate dateAdhes = (resultats.getDate("dateAdhesion") != null )?resultats.getDate("dateAdhesion").toLocalDate() : LocalDate.now();
						
						Abonne obj_abon = new Abonne(resultats.getInt("codMatricule"),resultats.getString("nom"),resultats.getString("prenom"), resultats.getString("adresse"),dateNaiss, dateAdhes, resultats.getString("libelleCategorie"));
						//System.out.println(obj_abon);
						LAbonne.add(obj_abon);
						
					}
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans le resultat , catch Exception e ");
					e.printStackTrace();
				}	
			 	//System.out.println(LAbonne);
				 return LAbonne;
			}

			
			@Override
			public void ajouter(Abonne abonne) {
				System.out.println("Entra à ajouter");	
				
				try {
					//this.connexion =  obj_DaoContext.getConnection();
					this.connexion = this.daoContext.getConnection();
					this.preparedStatement = this.connexion.prepareStatement("INSERT INTO abonnes Values (NULL,?,?,?,?,?,?)");
					this.preparedStatement.setString(1, abonne.getNom());
					this.preparedStatement.setString(2, abonne.getPrenom());
					this.preparedStatement.setString(3, abonne.getAdresse());
					
					//tranformer un LocalDate dans DATE et DATETIME		
					this.preparedStatement.setDate(4, Date.valueOf(abonne.getDateNaissance()));
					this.preparedStatement.setDate(5, Date.valueOf(abonne.getDateAdhesion()));/**/
					
					this.preparedStatement.setInt(6, abonne.getCodCategorieProf());
					
					this.preparedStatement.executeUpdate();
					System.out.println("Creation Reussi!!!!!!!");
					//return true; pour une evolution dans l application
					
				} catch (SQLException e) {				
					e.printStackTrace();
				}
				
			}

			@Override
			public void modifier(Abonne abonne) {
				System.out.println("Entra à modifier");
				
				try {
					this.connexion = this.daoContext.getConnection();
					
					this.preparedStatement = connexion.prepareStatement("UPDATE abonnes SET nom = ?, prenom = ?, adresse = ?, dateNaissance = ?, dateAdhesion = ?, codCategorieProf = ? WHERE codMatricule = ?;");
					
					
					this.preparedStatement.setString(1, abonne.getNom());
					this.preparedStatement.setString(2, abonne.getPrenom());
					this.preparedStatement.setString(3, abonne.getAdresse());
					
					this.preparedStatement.setDate(4, Date.valueOf(abonne.getDateNaissance()));
					this.preparedStatement.setDate(5, Date.valueOf(abonne.getDateAdhesion()));
					
					this.preparedStatement.setInt(6, abonne.getCodCategorieProf());
					this.preparedStatement.setInt(7, abonne.getCodMatricule());
					this.preparedStatement.executeUpdate();
					System.out.println("Modification Reussi!!!!!!!");
					
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans la methode modifier , catch Exception e ");
					e.printStackTrace();
				}
				
			}

			@Override
			public void supprimer(Abonne abonne) {
				
				try {
					
					this.preparedStatement = this.connexion.prepareStatement("DELETE FROM abonnes WHERE codMatricule = ?;");
					this.preparedStatement.setInt(1, abonne.getCodMatricule());
					this.preparedStatement.executeUpdate();
					System.out.println("Supression Reussi!!!!!");
					
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans la methode modifier , catch Exception e ");
					e.printStackTrace();
				}
			}

}
