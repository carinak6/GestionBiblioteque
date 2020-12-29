package tp.biblioteque.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Principal
 */
@WebServlet("/accueil")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Principal() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entre à Get de Principal");
		//Rediriger vers l accueil.jsp
		this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Entra à POST de Principal");
		
		//Rediriger vers listeabonnes.jsp
		this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
		//doGet(request, response);
	}

}
