<!doctype html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Modifica articolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
	
	<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			 	Operazione fallita!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<% if (session.getAttribute("ruolo")==null){  
			response.sendRedirect(request.getContextPath());
		 } %>
		 
		 <% if(session.getAttribute("ruolo")!=null && session.getAttribute("ruolo").equals("GUEST")){
			 	response.sendRedirect(request.getContextPath());
				}%>
		

		    <div class='card-header'>
		        <h5>Modifica articolo</h5> 
		    </div>
		    <div class='card-body'>
		    
		    <h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		    
		    <form method="post" action="ExecuteUpdateArticolo" novalidate="novalidate">
		    	
		    	<div class="form-row">
		    	

							<input type="hidden" value="${requestScope.id}" name="id">
							
							<div class="form-group col-md-6">
								<label>Codice <span class="text-danger">*</span></label>
								<input type="text" value="${requestScope.codice}" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Descrizione <span class="text-danger">*</span></label>
								<input type="text" value="${requestScope.descrizione}" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" required>
							</div>
							<div class="form-group col-md-6">
								<label>Prezzo <span class="text-danger">*</span></label>
								<input type="number" value="${requestScope.prezzo}" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo" required>
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Categoria </label>
								<select name="categoria">
								<c:forEach items = "${requestScope.categorie}" var="cat">
									<option value="${cat.id }">${cat.descrizione }</option>
								</c:forEach>
								<option value="-1" selected>Nessuna Categoria</option>
								</select>
							</div>
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
						
						</form>
		    
		    <div class='card-footer'>
		  
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>