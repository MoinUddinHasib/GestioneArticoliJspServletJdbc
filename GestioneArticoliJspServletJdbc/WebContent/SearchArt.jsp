<!doctype html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Cerca</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

<script type="text/javascript" language="javascript">
    function validaForm() {
    	if(isNaN(document.campi.prezzo.value)){
    		alert("Campi non validi");
    		return false;
    	}
    	return true;
    }
    </script>

</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<%
			if (session.getAttribute("ruolo") == null) {
			response.sendRedirect(request.getContextPath());
		}
		%>

		<div class='card'>
			<div class='card-header'>
				<h5>Cerca Articoli</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="SearchArticoloServlet" name="campi" onSubmit="return validaForm();">
					
					<div class="form-row">
					
					<div class="form-group col-md-6">
							<label>Codice </label> <input
								type="text" name="codice" id="codice"
								class="form-control" placeholder="Inserire il codice">
								
						</div>

						<div class="form-group col-md-6">
							<label>Descrizione </label> <input
								type="text" name="descrizione" id="descrizione"
								class="form-control" placeholder="Inserire la descrizione">
								
						</div>
						
						<div class="form-group col-md-6">
							<label>Prezzo </label> <input
								type="number" name="prezzo" id="prezzo"
								class="form-control" placeholder="Inserire il prezzo">
								
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


					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Cerca</button>


				</form>



				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />

</body>
</html>