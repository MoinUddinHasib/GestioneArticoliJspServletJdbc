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
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//andiamo ai risultati
		request.getRequestDispatcher("results.jsp").forward(request, response);

	}

}
