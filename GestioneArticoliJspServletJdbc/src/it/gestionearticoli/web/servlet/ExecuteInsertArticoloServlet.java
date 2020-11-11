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

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7892183592958897494L;

	public ExecuteInsertArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("ruolo")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		// validiamo input
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String categoriaInputStringParam = request.getParameter("categoria");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		Long categoria = !categoriaInputStringParam.isEmpty() ? Long.parseLong(categoriaInputStringParam) : null;
		
		// se la validazione fallisce torno in pagina
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}

		//occupiamoci delle operazioni di business
		try {
			Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo,categoria!=null? 
					MyServiceFactory.getCategoriaServiceInstance().findById(categoria) : null);
			
			MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloInstance);
			settaLista(request, response);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//andiamo ai risultati
		request.getRequestDispatcher("results.jsp").forward(request, response);

	}
	
	private void settaLista(HttpServletRequest request, HttpServletResponse response) {

		if (Boolean.valueOf(request.getParameter("fil"))) {
			try {
				request.setAttribute("fil", request.getParameter("fil"));
				request.setAttribute("co", request.getParameter("co"));
				request.setAttribute("de", request.getParameter("de"));
				request.setAttribute("pr", request.getParameter("pr"));
				request.setAttribute("cat", request.getParameter("cat"));
				request.setAttribute("listaArticoliAttribute",
						MyServiceFactory.getArticoloServiceInstance()
								.findByExample(new Articolo(request.getParameter("co").toString(),
										request.getParameter("de").toString(),
										request.getParameter("pr").toString().isEmpty() ? 0
												: Integer.parseInt(request.getParameter("pr").toString()),
										MyServiceFactory.getCategoriaServiceInstance()
												.findById(Long.parseLong(request.getParameter("cat").toString())))));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
