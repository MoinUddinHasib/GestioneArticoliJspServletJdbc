<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Cerca</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

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
				<h5>Cerca categoria</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="SearchCategoriaServlet">
					
					<div class="form-row">

						<div class="form-group col-md-6">
							<label>Descrizione </label> <input
								type="text" name="descrizione" id="descrizione"
								class="form-control" placeholder="Inserire la descrizione">
								
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