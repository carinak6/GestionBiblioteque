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
import tp.biblioteque.model.Livre;

public class LivreDaoImpl implements LivreDAO {
	
	private DAOContext daoContext;	
	//variables utilises dans tous les methodes alors je les ai sortie
	private Connection connexion = null;
	private PreparedStatement preparedStatement = null;
	
	
			public LivreDaoImpl( DAOContext daoContext) {
				this.daoContext = daoContext;
				
				try {
					this.connexion = this.daoContext.getConnection();
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans getConnection , catch Exception e ");
					e.printStackTrace();
				}
			}

			@Override
			public void ajouter(Livre livre) {
				//System.out.println("Entra à ajouter");	
				
				try {
					//this.connexion =  obj_DaoContext.getConnection();
					this.connexion = this.daoContext.getConnection();
					this.preparedStatement = this.connexion.prepareStatement("INSERT INTO livres Values (?,?,?,?)");
					this.preparedStatement.setLong(1, livre.getIsbn());
					this.preparedStatement.setInt(2, livre.getCodCatalogue());
					this.preparedStatement.setString(3, livre.getTitre());
					
					//tranformer un LocalDate dans DATE et DATETIME		
					this.preparedStatement.setDate(4, Date.valueOf(livre.getDateParution()));
					
					
					this.preparedStatement.executeUpdate();
					System.out.println("Creation Reussi du livre!!!!!!!");
					
					/* inserté dans le tableau auteur par livre   ça marche ??
					 * il faut penser pour plusiers auteurs
					 * */
					if(livre.getIdAuteur() != 0) {
							this.connexion = this.daoContext.getConnection();
							this.preparedStatement = this.connexion.prepareStatement("INSERT INTO auteurparlivre Values (?,?)");
							this.preparedStatement.setInt(1, livre.getIdAuteur());
							this.preparedStatement.setLong(2, livre.getIsbn());
							
							this.preparedStatement.executeUpdate();
							System.out.println("Creation Reussi auteurparlivre !!!!!!!");	
					
					}
					
					/* inserté dans le tableau livre par mot cles   ça marche ??*/
					if(livre.getListeMotsCles() != null) {
							this.connexion = this.daoContext.getConnection();
							
							for( Integer value : livre.getListeMotsCles() ) {
					            System.out.println( value );
					            this.preparedStatement = this.connexion.prepareStatement("INSERT INTO livreparmotscles Values (?,?)");
					            this.preparedStatement.setInt(1, value);
					            this.preparedStatement.setLong(2, livre.getIsbn());
					            this.preparedStatement.executeUpdate();
					            System.out.println("Creation Reussi livre par mots cles  !!!!!!!");	
					        }					
					}
					//return true; pour une evolution dans l application
					 System.out.println("Creation Reussi du livre complet  !!!!!!!");	
					
				} catch (SQLException e) {				
					e.printStackTrace();
				}
				
			}
		
			@Override
			public List<Livre> lister() {
				// TODO Auto-generated method stub
				List<Livre> listeLivre = new ArrayList<Livre>();
				
				//requete
				/*String query="SELECT l.isbn, l.titre, l.dateParution, c.libelleCatalogue, CONCAT(a.nomAuteur,' ',a.prenomAuteur) as auteur \r\n"
						+ "FROM livres l\r\n"
						+ "JOIN catalogues c ON l.codCatalogue = c.codCatalogue\r\n"
						+ "JOIN auteurparlivre al ON l.isbn = al.isbn\r\n"
						+ "JOIN auteurs a ON al.idAuteur = a.idAuteur";*/
				
				
				/*il faut que je demande comme ça fera avec une table relationnel many to many
				 * String query="SELECT isbn, titre, dateParution, codCatalogue FROM livres ";*/
				
				String query ="SELECT l.isbn, l.titre, l.dateParution, l.codCatalogue, al.idAuteur FROM livres l \r\n"
						+ "LEFT JOIN auteurparlivre al ON l.isbn = al.isbn";
				
				Statement smt = null; 
					
				try {
					smt = this.connexion.createStatement();
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans createStatement , catch Exception e ");
					e.printStackTrace();
				}
				
					
				ResultSet resultats = null; //Class qui reçois les resultats de la requete
				
				try {
					resultats = smt.executeQuery(query);
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans executeQuery , catch Exception e ");
					e.printStackTrace();
				}
				
				
				try {
	                //on prepare la variable que retournera le resultat de la liste
					//premier partie de recuperation des donnes des libre
					while (resultats.next()) {
					
						/* pour lever l'erreur du pointeur nullException j'ai du ajouter cette declaration des varaibles LocalDate et les passer au constructeur d Abonne */
						LocalDate dateParution = (resultats.getDate("dateParution") != null )?resultats.getDate("dateParution").toLocalDate() : LocalDate.now();
						
						//Livre obj_livre = new Livre(resultats.getInt("isbn"),resultats.getString("titre"),dateParution, resultats.getString("libelleCatalogue"), resultats.getString("auteur"));
						//j'utilise le methode beans
						Livre obj_livre = new Livre();
						obj_livre.setIsbn(resultats.getLong("isbn"));  
						obj_livre.setDateParution(dateParution); 
						obj_livre.setTitre(resultats.getString("titre"));
						obj_livre.setCodCatalogue(resultats.getInt("codCatalogue"));
						obj_livre.setIdAuteur(resultats.getInt("idAuteur"));/* j'ai du l'ajouter pour faire la liasion un livre un auteur et un auteur plusiers livres */
						//System.out.println(listeLivre);
						obj_livre.setListeMotsCles(recuperMotsCles(resultats.getLong("isbn")));
						
						listeLivre.add(obj_livre);
						
					}
					
					
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans le resultat , catch Exception e ");
					e.printStackTrace();
				}	
				
				
				
				return listeLivre;
				
				
			}
		
			@Override
			public void modifier(Livre livre) {
				System.out.println("Entra à modifier Livre");
				
				try {
					this.connexion = this.daoContext.getConnection();
					
					this.preparedStatement = connexion.prepareStatement("UPDATE livres SET codCatalogue = ?, titre = ?, dateParution = ? WHERE isbn = ?;");
										
					this.preparedStatement.setInt(1, livre.getCodCatalogue());
					this.preparedStatement.setString(2, livre.getTitre());	
					this.preparedStatement.setDate(3, Date.valueOf(livre.getDateParution()));
					this.preparedStatement.setLong(4, livre.getIsbn());
					
					this.preparedStatement.executeUpdate();
					System.out.println("Modification lu livre Reussi!!!!!!!");
					
					/* inserté dans le tableau auteur par livre   ça marche pas*/
					this.connexion = this.daoContext.getConnection();
					this.preparedStatement = this.connexion.prepareStatement("UPDATE auteurparlivre SET idAuteur = ? WHERE isbn = ?;");
					this.preparedStatement.setInt(1, livre.getIdAuteur());
					this.preparedStatement.setLong(2, livre.getIsbn());
					
					this.preparedStatement.executeUpdate();
					System.out.println("Modification Reussi auteurparlivre !!!!!!!");	
					
					
					/* inserté dans le tableau livre par mot cles   ça marche ??*/
					if(livre.getListeMotsCles() != null) {
							this.connexion = this.daoContext.getConnection();
							
							for( Integer value : livre.getListeMotsCles() ) {
					            System.out.println( value );
					            /* si e=il existe deja il se reproduit ou il y a une erreur ??
					             * java.sql.SQLIntegrityConstraintViolationException: Duplicata du champ '1-2733871713' pour la clef 'PRIMARY'
					             * */
					            
					            this.preparedStatement = this.connexion.prepareStatement("INSERT INTO livreparmotscles Values (?,?)");
					            this.preparedStatement.setInt(1, value);
					            this.preparedStatement.setLong(2, livre.getIsbn());
					            this.preparedStatement.executeUpdate();
					            System.out.println("Creation Reussi livre par mots cles  !!!!!!!");	
					        }	
					}
									
					
					
					
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans la methode modifier de un Livre , catch Exception e ");
					e.printStackTrace();
				}
				
				
			}
		
			@Override
			public void suprimer(Livre livre) {
				/*  il faut suprimer les tables filles*/
				try {
					
					this.preparedStatement = this.connexion.prepareStatement("DELETE FROM livres WHERE isbn = ?;");
					this.preparedStatement.setLong(1, livre.getIsbn());
					this.preparedStatement.executeUpdate();
					System.out.println("Supression Reussi d'un livre !!!!!");
					
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans la methode modifier , catch Exception e ");
					e.printStackTrace();
				}
				
			}
			
			public List<Integer> recuperMotsCles(Long id){
				System.out.println("Entre recuperMotsCles ");
				
				List<Integer> listeMotsCles = new ArrayList<>();
				
				Connection connexionMots = null;
							
				DAOContext obj_DaoContext = new DAOContext();
				
				Statement smt = null;
				ResultSet resultats = null;
				
				String query ="SELECT idMotCles FROM livreparmotscles WHERE isbn = "+id;
				
				try {
					connexionMots = obj_DaoContext.getConnection();
					smt = connexionMots.createStatement();				
					resultats = smt.executeQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
	                //on prepare la variable que retournera le resultat de la liste
					//premier partie de recuperation des donnes des libre
					while (resultats.next()) {	
						listeMotsCles.add(resultats.getInt("idMotCles"));
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("listeMotsCles " + listeMotsCles);
				 return listeMotsCles;
				
			}
			
}
