package tp.biblioteque.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;


public class DAOContext {
	private static String db_url="";
	private static String db_username="";
	private static String db_password="";
	
	    public DAOContext() {
	        
	    }
	    
	     /* il charge les parametres de connection du xml*/
	    public  void init(ServletContext context) {
	    	try {
	    			System.out.println("Entra à init de DAOContext");
	    			Class.forName(context.getInitParameter( "JDBC_DRIVER" ) );
	    			
	    			 db_url = context.getInitParameter( "JDBC_URL" );
	    			 db_username = context.getInitParameter( "JDBC_LOGIN" );
	    			 db_password = context.getInitParameter( "JDBC_PASSWORD" );
	    			
	    	} catch( Exception exception ) {	    		
	    			System.out.println("Erreur dans la lecture des constantes de web.xml");
	    			exception.printStackTrace();
	    	}
	    }
	    
	    public Connection getConnection() throws SQLException {
	    	
	    	Connection connexion = null;
			 
			 try {
				 	try {//bloque try pour class
					 	Class.forName("com.mysql.cj.jdbc.Driver");					 	
					 	
					} catch (ClassNotFoundException e) {
						System.out.println("Message Erreur du catch Class.forName");
						e.printStackTrace();
					}
				 
					 // BLOQUE Connexion, il passe par ici quand la connexion est reussi: 		
					connexion = DriverManager.getConnection(db_url, db_username, db_password) ;
					 
					 if (connexion != null) { 
						 System.out.println("Connection Reussi!!");
					 } else {
						 System.out.println("Pas de connexion à la BDD");
					 }		 
			 }catch(SQLException e) {
				 
				 System.out.println("Un erreur se ha produit dans la connection MySQLDatabase \n ");
				 System.out.println(e);
				 
			 }
	    
	    	return connexion;
	    }
	    
	 // Récupération du Dao, on recupere les objets beans qu on a besoin d utiliser
	  // ans les differents classes  
	  public AbonneDAO getAbonneDao() {
	    	return new AbonneDaoImpl(this); /* ici on cree un objet AbonneDaoImpl */
	    }
	  
	  public LivreDAO getLivreDao() {
		  return new LivreDaoImpl(this);
	  }
	  
	  public CatalogueDAO getCatalogueDao() {
		  return new CatalogueDaoImpl(this);
	  }
	  
	  public AuteurDAO getAuteurDao() {
		  return new AuteurDaoImpl(this);
	  }
	  
	  public MotsClesDAO getMotsClesDao() {
		  return new MotsClesDaoImpl(this);
	  }
	  
	  public LivreParMotsClesDAO getLivreParMotsClesDao() {
		  return new LivreParMotsClesDaoImpl(this);
	  }
	  
}
