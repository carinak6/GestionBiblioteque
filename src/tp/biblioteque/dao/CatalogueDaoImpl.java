package tp.biblioteque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tp.biblioteque.model.Abonne;
import tp.biblioteque.model.Catalogue;

public class CatalogueDaoImpl implements CatalogueDAO {
	
	private DAOContext daoContext;	
	//variables utlises dans tous les methodes alors je les ai sortie
	private Connection connexion = null;
	private PreparedStatement preparedStatement = null;
	
			public CatalogueDaoImpl(DAOContext daoContext) {
				this.daoContext = daoContext;
				try {
					this.connexion = this.daoContext.getConnection();
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans getConnection , catch Exception e ");
					e.printStackTrace();
				}
			}

			@Override
			public void ajouter(Catalogue catalogue) {
				// TODO Auto-generated method stub
				
			}
		
			@Override
			public List<Catalogue> lister() {
				
				List<Catalogue> ListeCatalogue  = new ArrayList<Catalogue>();
				
				//preparation de la requete SQL pour lister tous les items catalogues
				String query ="SELECT * FROM catalogues";
				
				//preparation de l instruction mysql pour executer la requete
				Statement smt = null; 
				try {
						smt = this.connexion.createStatement();//permet de executer requetes simples
						
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans createStatement , catch Exception e ");
					e.printStackTrace();
				}
				
				//preparation pou recevoir les resultats
				ResultSet resultats = null; //Class qui reçois les resultats de la requete
				
			 	try {
			 		
			 		resultats = smt.executeQuery(query) ;
			 		
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans executeQuery , catch Exception e ");
					e.printStackTrace();
				} 
			 	
			 	try {
	                
					while (resultats.next()) {
						
						Catalogue obj_catalogue = new Catalogue();
						obj_catalogue.setCodCatalogue(resultats.getInt("codCatalogue"));
						obj_catalogue.setLibelleCatalogue(resultats.getString("libelleCatalogue"));
						//System.out.println(obj_abon);
						ListeCatalogue.add(obj_catalogue);
						
					}
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans le resultat , catch Exception e ");
					e.printStackTrace();
				}	
				
				return ListeCatalogue;
			}
		
			@Override
			public void modifier(Catalogue catalogue) {
				// TODO Auto-generated method stub
				
			}
		
			@Override
			public void suprimer(Catalogue catalogue) {
				// TODO Auto-generated method stub
				
			}
		
			@Override
			public Catalogue chercherID(int idCatalogue) {
				
				Catalogue varCatalogue = new Catalogue();
				
				String query ="SELECT * FROM catalogues WHERE codCatalogue= idCatalogue";
				
				//preparation de l instruction mysql pour executer la requete
				Statement smt = null; 
				try {
						smt = this.connexion.createStatement();//permet de executer requetes simples
						
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans createStatement , catch Exception e ");
					e.printStackTrace();
				}
				
				//preparation pou recevoir les resultats
				ResultSet resultats = null; //Class qui reçois les resultats de la requete
				
			 	try {
			 		
			 		resultats = smt.executeQuery(query) ;
			 		
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans executeQuery , catch Exception e ");
					e.printStackTrace();
				} 
			 	
			 	try {
			 		System.out.println(resultats.getFetchSize());
	                
			 		if(resultats.getFetchSize() != 0) {			 			
			 			varCatalogue.setCodCatalogue(resultats.getInt("codCatalogue"));
			 			varCatalogue.setLibelleCatalogue(resultats.getString("libelleCatalogue"));
			 		}else {
			 			varCatalogue=null;
			 		}
			 		
					
				} catch (SQLException e) {
					System.out.println("Un erreur se ha produit dans le resultat , catch Exception e ");
					e.printStackTrace();
				}	
				return varCatalogue;
			}

}
