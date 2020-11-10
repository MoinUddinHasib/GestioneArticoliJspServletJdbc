package it.gestionearticoli.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class Laservletpervisualizzare
 */
@WebServlet("/LaservletpervisualizzareArticolo")
public class LaservletpervisualizzareArticolo extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -5868866209395218850L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LaservletpervisualizzareArticolo() {
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
		Articolo a = null;
		try {
			a = MyServiceFactory.getArticoloServiceInstance().findById(Long.parseLong(request.getParameter("id")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("id", a.getId());
		request.setAttribute("codice", a.getCodice());
		request.setAttribute("descrizione", a.getDescrizione());
		request.setAttribute("prezzo", a.getPrezzo());
		request.setAttribute("categoria", a.getCategoria());
		request.getRequestDispatcher("show.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
