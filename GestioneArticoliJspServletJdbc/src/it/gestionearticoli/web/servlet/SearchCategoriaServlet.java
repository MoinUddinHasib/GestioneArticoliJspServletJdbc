package it.gestionearticoli.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class SearchCategoriaServlet
 */
@WebServlet("/SearchCategoriaServlet")
public class SearchCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("ruolo")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		request.getRequestDispatcher("SearchCat.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ruolo")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		try {
			request.setAttribute("listaCategorie", MyServiceFactory.getCategoriaServiceInstance().findByExample(new Categoria(request.getParameter("descrizione"))));
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("canali.jsp").forward(request, response);
	}

}
