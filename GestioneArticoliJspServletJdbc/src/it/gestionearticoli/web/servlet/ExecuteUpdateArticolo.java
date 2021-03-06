package it.gestionearticoli.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class Laservletpermodificare
 */
@WebServlet("/ExecuteUpdateArticolo")
public class ExecuteUpdateArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteUpdateArticolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// validiamo input
		long idInputParam;
		try {
			idInputParam = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e1) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String categoriaInputStringParam = request.getParameter("categoria");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		Long categoria = !categoriaInputStringParam.isEmpty() ? Long.parseLong(categoriaInputStringParam) : null;

		// se la validazione fallisce torno in pagina
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1
				|| Long.toString(idInputParam).isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("codice", request.getParameter("codice"));
			request.setAttribute("descrizione", request.getParameter("descrizione"));
			request.setAttribute("prezzo", request.getParameter("prezzo"));
			request.setAttribute("categoria", request.getParameter("categoria"));
			request.getRequestDispatcher("edit.jsp").forward(request, response);
			return;
		}
		// occupiamoci delle operazioni di business

		try {
			Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo,
					categoria != null ? MyServiceFactory.getCategoriaServiceInstance().findById(categoria) : null);
			articoloInstance.setId(idInputParam);
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articoloInstance);
			settaLista(request, response);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// andiamo ai risultati
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
