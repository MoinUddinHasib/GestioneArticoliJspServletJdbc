package it.gestionearticoli.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Laservletperrimuovere
 */
@WebServlet("/LaservletperrimuovereArticolo")
public class LaservletperrimuovereArticolo extends HttpServlet {      
    /**
	 * 
	 */
	private static final long serialVersionUID = -1718919331655128471L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LaservletperrimuovereArticolo() {
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
			response.sendRedirect(request.getContextPath()+"/ListArticoliServlet");
			return;
		}
		request.setAttribute("id", request.getParameter("id"));
		request.getRequestDispatcher("conferma.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
