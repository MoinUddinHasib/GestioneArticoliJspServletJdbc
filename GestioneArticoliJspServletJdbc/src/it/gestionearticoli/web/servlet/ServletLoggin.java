package it.gestionearticoli.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.gestionearticoli.model.User;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ServletLoggin
 */
@WebServlet("/ServletLoggin")
public class ServletLoggin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoggin() {
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
		request.getRequestDispatcher("EntryPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idTemp=request.getParameter("id_user");		
		long idInputParam = !idTemp.isEmpty() ? Long.parseLong(idTemp) : -1;
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		try {
			User ris=MyServiceFactory.getUserServiceInstance().findById(idInputParam);
			if(ris==null || !ris.getPassword().equals(password)) {
				request.setAttribute("errorMessage", "Credenziali sbagliate");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}

			session.setAttribute("nome", ris.getNome());
			session.setAttribute("cognome", ris.getCognome());
			session.setAttribute("codice_fiscale", ris.getCodice_fiscale());
			session.setAttribute("ruolo", ris.getRuolo());
			request.getRequestDispatcher("EntryPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
