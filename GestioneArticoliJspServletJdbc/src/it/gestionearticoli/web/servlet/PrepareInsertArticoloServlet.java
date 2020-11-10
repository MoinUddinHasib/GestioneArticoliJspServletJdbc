package it.gestionearticoli.web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/PrepareInsertArticoloServlet")
public class PrepareInsertArticoloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8670744408877714151L;

	public PrepareInsertArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		List<Categoria> a=null;
		try {
			a = MyServiceFactory.getCategoriaServiceInstance().listAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categorie", a);
		request.getRequestDispatcher("insert.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
