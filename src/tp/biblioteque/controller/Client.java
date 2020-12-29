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
    
    /*La m�thode init () est lanc�e par le navigateur lorsque le programme Java est charg� et ex�cut� par le navigateur.
     * La m�thode init() est appel�e par le serveur juste apr�s l'instanciation de la servlet.	
     * */
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		System.out.println("Entra � init de Client / Abonne");
		DAOContext daoContext = new DAOContext();/*on instance objet de la classe qui gere la connexion � la BDD*/
		    
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
				case "update":	//on affichera tous les donnes de l'abonne mais peut etre il faudra faire un methode recherche, � voir			
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
		
		//on ajoute � la variable request
		request.setAttribute("listeAbonne", this.abonneDao.lister());
		
		//Rediriger vers listeabonnes.jsp
		this.getServletContext().getRequestDispatcher("/listeAbonnes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entra � POST");
		
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
		
		
		//on ajoute � la variable request
		request.setAttribute("listeAbonne", this.abonneDao.lister());
		
		//Rediriger vers listeabonnes.jsp
		this.getServletContext().getRequestDispatcher("/listeAbonnes.jsp").forward(request, response);
	}

}
