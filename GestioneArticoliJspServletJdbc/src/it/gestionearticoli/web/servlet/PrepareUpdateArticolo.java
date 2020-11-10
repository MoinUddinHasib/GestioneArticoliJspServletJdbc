package it.gestionearticoli.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateArticolo
 */
@WebServlet("/PrepareUpdateArticolo")
public class PrepareUpdateArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateArticolo() {
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
		if(session.getAttribute("ruolo").equals("GUEST")) {
			response.sendRedirect(request.getContextPath()+"/ListArticoliServlet");
			return;
		}
		try {
			Articolo a = MyServiceFactory.getArticoloServiceInstance().findById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("id", a.getId());
			request.setAttribute("codice", a.getCodice());
			request.setAttribute("descrizione", a.getDescrizione());
			request.setAttribute("prezzo", a.getPrezzo());
			request.setAttribute("categoria_fk", a.getCategoria() == null ? "" : a.getCategoria().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Categoria> a=null;
		try {
			a = MyServiceFactory.getCategoriaServiceInstance().listAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categorie", a);
		
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
