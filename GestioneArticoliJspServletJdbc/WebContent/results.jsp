<%@page import="it.gestionearticoli.model.Articolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Pagina dei risultati</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<% if (session.getAttribute("ruolo")==null){  
			response.sendRedirect(request.getContextPath());
		 } %>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'><c:if test="${ sessionScope.ruolo != 'GUEST'}">
		    	<a class="btn btn-primary " href="PrepareInsertArticoloServlet">Add New</a>
		    						</c:if>
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Codice</th>
		                        <th>Descrizione</th>
		                        <th>Prezzo</th>
		                        <th>Categoria</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                		
		                	<c:forEach items="${requestScope.listaArticoliAttribute}" var= 'a'>							
		                    <tr >
		                        <td>${a.getId()}</td>
		                        <td>${a.getCodice()}</td>
		                        <td>${a.getDescrizione()}</td>
		                        <td>${a.getPrezzo()}</td>
		                        <td>${a.getCategoria() != null ? a.getCategoria().getDescrizione() : "Nessuna Categoria"}</td>
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="LaservletpervisualizzareArticolo?id=${a.getId()}">Visualizza</a>
									<c:if test="${ sessionScope.ruolo != 'GUEST'}">
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateArticolo?id=${a.getId()}">Edit</a>
									<c:if test="${ sessionScope.ruolo != 'OPERATORE'}">
									<a class="btn btn-outline-danger btn-sm" href="LaservletperrimuovereArticolo?id=${a.getId()}">Delete</a>
									</c:if>
									</c:if>
								</td>
		                    </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>