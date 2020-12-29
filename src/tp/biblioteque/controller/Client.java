package tp.biblioteque.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tp.biblioteque.dao.AbonneDAO;
import tp.biblioteque.dao.DAOContext;
import tp.biblioteque.model.Abonne;

/**
 * Servlet implementation class Client
 */
@WebServlet(name = "abonne", urlPatterns = { "/abonne" })
public class Client extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AbonneDAO abonneDao;   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Client() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /*La méthode init () est lancée par le navigateur lorsque le programme Java est chargé et exécuté par le navigateur.
     * La méthode init() est appelée par le serveur juste après l'instanciation de la servlet.	
     * */
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		System.out.println("Entra à init de Client / Abonne");
		DAOContext daoContext = new DAOContext();/*on instance objet de la classe qui gere la connexion à la BDD*/
		    
	    daoContext.init(this.getServletContext());
	    
	    this.abonneDao = daoContext.getAbonneDao();/* on cree un objet PaysDaoImpl qui gere 
	    les methodes pour interchanger avec la BDD et la classe Pays*/
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action") != null) {			
			
			String actionRec = request.getParameter("action");
			Abonne obj_abonne = new Abonne();
			
			switch(actionRec) {
				case "update":	//on affichera tous les donnes de l'abonne mais peut etre il faudra faire un methode recherche, à voir			
					    request.setAttribute("modNom", request.getParameter("nom"));
					    request.setAttribute("modCodM", request.getParameter("id"));// pour la matricule	
					    request.setAttribute("modPrenom", request.getParameter("prenom"));
					    request.setAttribute("modAdresse", request.getParameter("adresse"));
					    request.setAttribute("modDateNaissance", request.getParameter("datenaissance"));
					    request.setAttribute("modDateAdhesion", request.getParameter("dateadhesion"));
					    request.setAttribute("modCodCategorieProf", request.getParameter("categorieprof"));
					   // System.out.println(request.getParameter("categorieprof"));
						break;
					
				case "delete":					
						obj_abonne.setCodMatricule(Integer.parseInt(request.getParameter("id")));
						this.abonneDao.supprimer(obj_abonne);
						break;		
					
			}
		}
		
		//on ajoute à la variable request
		request.setAttribute("listeAbonne", this.abonneDao.lister());
		
		//Rediriger vers listeabonnes.jsp
		this.getServletContext().getRequestDispatcher("/listeAbonnes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entra à POST");
		
		Abonne abonne = new Abonne();
		abonne.setNom(request.getParameter("nom"));  
		abonne.setPrenom(request.getParameter("prenom"));
		abonne.setAdresse(request.getParameter("adresse"));
		
		
		
		abonne.setDateNaissance(LocalDate.parse(request.getParameter("datenaissance")));
		abonne.setDateAdhesion(LocalDate.parse(request.getParameter("dateadhesion")));
		abonne.setCodCategorieProf(Integer.parseInt(request.getParameter("codCategorieProf")));
		
		
		if(request.getParameter("id") != null && request.getParameter("id") != "" ) {
			
			abonne.setCodMatricule(Integer.parseInt(request.getParameter("id")));	
			System.out.println(abonne);
			this.abonneDao.modifier(abonne);
			
		}else {
			System.out.println(abonne);
			this.abonneDao.ajouter(abonne);
			
		}
		
		
		//on ajoute à la variable request
		request.setAttribute("listeAbonne", this.abonneDao.lister());
		
		//Rediriger vers listeabonnes.jsp
		this.getServletContext().getRequestDispatcher("/listeAbonnes.jsp").forward(request, response);
	}

}
