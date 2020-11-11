<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Conferma</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
	<% if (session.getAttribute("ruolo")==null){  
			response.sendRedirect(request.getContextPath());
		 } %>
	
		<div class='card'>
		    <div class='card-header'>
		        <h5>Conferma eliminazione</h5> 
		    </div>
		    <div class='card-body'>

			
					<form method="post" action="ConfermaServlet" >
						<input type="hidden" value="${requestScope.id}" name="id">
						<input type="hidden" value="${requestScope.fil}" name="fil">
						<input type="hidden" value="${requestScope.co}" name="co">
						<input type="hidden" value="${requestScope.de}" name="de">
						<input type="hidden" value="${requestScope.pr}" name="pr">
						<input type="hidden" value="${requestScope.cat}" name="cat">
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					</form>
					
				<%if(Boolean.valueOf(request.getAttribute("fil").toString())){ %>
				<form method="post" action="SearchArticoloServlet">
						<input type="hidden" value="${requestScope.fil}" name="fil">
						<input type="hidden" value="${requestScope.co}" name="codice">
						<input type="hidden" value="${requestScope.de}" name="descrizione">
						<input type="hidden" value="${requestScope.pr}" name="prezzo">
						<input type="hidden" value="${requestScope.cat}" name="categoria">
				
		       <button type="submit" name="submit" value="submit" id="submit" class='btn btn-outline-secondary' style='width:80px'>Back</button>
		          
		        </form>
		        <%} else{%>
		        <a href="${pageContext.request.contextPath}/ListArticoliServlet" class='btn btn-outline-secondary' style='width:80px'>
		          Back
		        </a>
		        <%} %>
		   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>