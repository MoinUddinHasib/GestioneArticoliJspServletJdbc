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
 * Servlet implementation class Conferma
 */
@WebServlet("/ConfermaServlet")
public class ConfermaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("ruolo").equals("GUEST")) {
			response.sendRedirect(request.getContextPath()+"/ListArticoliServlet");
			return;
		}
		
//		settaLista(request,response);
//		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session.getAttribute("ruolo").equals("GUEST")) {
			response.sendRedirect(request.getContextPath()+"/ListArticoliServlet");
			return;
		}
		
		try {
			Articolo a = MyServiceFactory.getArticoloServiceInstance().findById(Long.parseLong(request.getParameter("id")));
			MyServiceFactory.getArticoloServiceInstance().rimuovi(a);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		settaLista(request,response);
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

	private void settaLista(HttpServletRequest request, HttpServletResponse response) {
		
		if(Boolean.valueOf(request.getParameter("fil"))) {
			try {
				request.setAttribute("fil", request.getParameter("fil"));
				request.setAttribute("co", request.getParameter("co"));
				request.setAttribute("de", request.getParameter("de"));
				request.setAttribute("pr", request.getParameter("pr"));
				request.setAttribute("cat", request.getParameter("cat"));
				request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().findByExample(
						new Articolo(request.getParameter("co").toString(),
						request.getParameter("de").toString(),
						request.getParameter("pr").toString().isEmpty()?0:Integer.parseInt(request.getParameter("pr").toString()),
								MyServiceFactory.getCategoriaServiceInstance().findById(
										Long.parseLong(request.getParameter("cat").toString())))));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else {
			try {
				request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

}
