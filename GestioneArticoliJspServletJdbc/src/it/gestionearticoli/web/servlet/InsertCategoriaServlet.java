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
 * Servlet implementation class InserCategoriaServlet
 */
@WebServlet("/InsertCategoriaServlet")
public class InsertCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCategoriaServlet() {
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
			response.sendRedirect(request.getContextPath()+"/ListCategorieServlet");
			return;
		}
		request.setAttribute("filtro", request.getParameter("lista"));
		request.setAttribute("criterio", request.getParameter("desc"));
		request.getRequestDispatcher("insertCat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				String descrizioneInputParam = request.getParameter("descrizione");

				if (descrizioneInputParam.isEmpty()) {
					request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
					request.getRequestDispatcher("insertCat.jsp").forward(request, response);
					return;
				}

				Categoria cat = new Categoria(descrizioneInputParam);
				try {
					MyServiceFactory.getCategoriaServiceInstance().inserisciNuovo(cat);
					settaLista(request,response);
					request.setAttribute("successMessage", "Operazione effettuata con successo");
				} catch (Exception e) {
					e.printStackTrace();
				}

				request.getRequestDispatcher("canali.jsp").forward(request, response);

	}
	
	private void settaLista(HttpServletRequest request, HttpServletResponse response) {
		if(Boolean.valueOf(request.getParameter("filtro").toString())) {
			try {
				request.setAttribute("filtro", true);
				request.setAttribute("criterio", request.getParameter("criterio"));
				request.setAttribute("listaCategorie", MyServiceFactory.getCategoriaServiceInstance().findByExample(new Categoria(request.getParameter("criterio"))));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else {
			try {
				request.setAttribute("listaCategorie", MyServiceFactory.getCategoriaServiceInstance().listAll());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
