package tp.biblioteque.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tp.biblioteque.dao.AuteurDAO;
import tp.biblioteque.dao.CatalogueDAO;
import tp.biblioteque.dao.DAOContext;
import tp.biblioteque.dao.LivreDAO;
import tp.biblioteque.dao.LivreParMotsClesDAO;
import tp.biblioteque.dao.MotsClesDAO;
import tp.biblioteque.model.LivreParMotsCles;

import java.net.*;

/**
 * Servlet implementation class Livre
 */
@WebServlet(name = "livre", urlPatterns = { "/livre" })
public class Livre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LivreDAO livreDao;
	private CatalogueDAO catalogueDao;
	private AuteurDAO auteurDao;
	private MotsClesDAO motsClesDao;
	private LivreParMotsClesDAO livreParMotCle;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Livre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		System.out.println("Entra à Livre de Client / Abonne");
		DAOContext daoContext = new DAOContext();/*on instance objet de la classe qui gere la connexion à la BDD*/
		    
	    daoContext.init(this.getServletContext());
	    
	    /* on cree un objet LivreDaoImpl qui gere 
	    les methodes pour interchanger avec la BDD et la classe Livre*/
	    this.livreDao=daoContext.getLivreDao();
	    this.catalogueDao=daoContext.getCatalogueDao();
	    this.auteurDao = daoContext.getAuteurDao();
	    this.motsClesDao = daoContext.getMotsClesDao();
	    this.livreParMotCle = daoContext.getLivreParMotsClesDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");/* ça marche pas les signe ? continuent  */
		response.setCharacterEncoding("UTF-8");
		System.out.println("entra à doGet");
		
		if(request.getParameter("action") != null) {
		
			String actionRec = request.getParameter("action");
			tp.biblioteque.model.Livre obj_livre = new tp.biblioteque.model.Livre();
			
			switch(actionRec) {
				case "update":
					//System.out.println("entra à modifier");
					request.setAttribute("modIsbn", request.getParameter("isbn"));
					request.setAttribute("modTitre", request.getParameter("titre"));					
					request.setAttribute("modDateParution", request.getParameter("date"));					
					
					request.setAttribute("modCodCatalogue", request.getParameter("codTheme"));//Litt?rature
					request.setAttribute("modIdAuteur", request.getParameter("idAuteur"));
					
					//peut etre les listes de mots cles on devra les demander ici avec un objet motscles, a voir
					// et le faire par get
					//System.out.println("entra à modifier : "+request.getParameter("listeMots"));
					
					request.setAttribute("modlisteMots", request.getParameter("listeMots"));/* liste mots cles */
					
					break;
					
				case "delete":					
						obj_livre.setIsbn(Long.parseLong(request.getParameter("id")));						
						this.livreDao.suprimer(obj_livre);
						break;
					
					
					//System.out.println(request.getAttribute("ListeCatalogue"));
				}
		}
		request.setAttribute("listeLivres", livreDao.lister());
		request.setAttribute("ListeCatalogue", catalogueDao.lister());
		request.setAttribute("listeAuteurs", auteurDao.lister());
		request.setAttribute("listeMotsCles", motsClesDao.lister());
		
		
		System.out.println(request.getAttribute("listeLivres"));
		//System.out.println(request.getAttribute("modCatalogue"));
		//System.out.println(request.getAttribute("ListeCatalogue"));
		this.getServletContext().getRequestDispatcher("/gestionLivre.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");/* ça marche pas les signe ? continuent  */
		response.setCharacterEncoding("UTF-8");
		
		//j'ai du passer le chemin complet de la classe bean de livre, sinon il y a un conflit avec
		//le nom de servlet...
		tp.biblioteque.model.Livre livre = new tp.biblioteque.model.Livre();
		//Integer.parseInt(request.getParameter("isbn")
		livre.setIsbn(Long.parseLong(request.getParameter("isbn")));
		livre.setTitre(request.getParameter("titre"));
		livre.setCodCatalogue(Integer.parseInt(request.getParameter("codCatalogue")));
		livre.setIdAuteur(Integer.parseInt(request.getParameter("idAuteur")));
		livre.setDateParution(LocalDate.parse(request.getParameter("dateParution")));
		
		
				
		//System.out.println("---------> "+livre);
		
		if(request.getParameter("id") != null && request.getParameter("id") != "" ) {
			
			//livre.setCodMatricule(Integer.parseInt(request.getParameter("id")));	
			System.out.println(livre);
			this.livreDao.modifier(livre);
			
		}else {
			System.out.println(livre);
			this.livreDao.ajouter(livre);
			
		}
		
		//ajoute des mots cles		
		for (String s: request.getParameterValues("motscles")){
			
			LivreParMotsCles livreParMot = new LivreParMotsCles();
			livreParMot.setIsbn(Long.parseLong(request.getParameter("isbn")));			
			livreParMot.setIdMotCles(Integer.parseInt(s));
			
			//verification s il existe deja
			if(this.livreParMotCle.existe(livreParMot)) {
				System.out.println("EXISTE ---------> "+livreParMot);
			}else {
				System.out.println("N EXISTE PAS ---------> "+livreParMot);
				this.livreParMotCle.ajouter(livreParMot);
			}
			
		}		
		
		//on ajoute à la variable request
		request.setAttribute("listeLivres", this.livreDao.lister());
		request.setAttribute("ListeCatalogue", this.catalogueDao.lister());
		request.setAttribute("listeAuteurs", this.auteurDao.lister());
		request.setAttribute("listeMotsCles", motsClesDao.lister());
		
		//System.out.println(request.getParameter("listeAbonne"));
		
		//Rediriger vers listeabonnes.jsp
		this.getServletContext().getRequestDispatcher("/gestionLivre.jsp").forward(request, response);
	}

}
