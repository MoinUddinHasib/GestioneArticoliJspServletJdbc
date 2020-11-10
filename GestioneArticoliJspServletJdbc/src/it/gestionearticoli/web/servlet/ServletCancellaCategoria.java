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
 * Servlet implementation class ServletCancellaCategoria
 */
@WebServlet("/ServletCancellaCategoria")
public class ServletCancellaCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCancellaCategoria() {
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
		session = request.getSession();
		if(session.getAttribute("ruolo").equals("GUEST") || session.getAttribute("ruolo").equals("OPERATORE")) {
			response.sendRedirect(request.getContextPath()+"/ListCategorieServlet");
			return;
		}
		try {
			Categoria c = MyServiceFactory.getCategoriaServiceInstance().findById(Long.parseLong(request.getParameter("id")));
			MyServiceFactory.getCategoriaServiceInstance().rimuovi(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/ListCategorieServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
