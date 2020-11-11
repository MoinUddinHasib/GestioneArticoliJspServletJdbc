<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <script type="text/javascript" language="javascript">
    function validaForm() {
    	if(isNaN(document.campi.id_user.value)){
    		alert("Campi non validi");
    		return false;
    	}
    	return true;
    }
    </script>
    
    <title>Sign-in</title>
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
		
		<% if (session.getAttribute("ruolo")!=null){  
			response.sendRedirect(request.getContextPath()+"/ServletLogOut");
		 } %>
		

		    <div class='card-header'>
		        <h5>Log-in</h5> 
		    </div>
		    <div class='card-body'>
		    
		    <form method="post" action="ServletLoggin" name="campi" onSubmit="return validaForm();">
		    	
		    	<div class="form-row">
							
							<div class="form-group col-md-6">
								<label>ID User <span class="text-danger">*</span></label>
								<input type="number"  value="${requestScope.id_user}" name="id_user" id="id_user" class="form-control" placeholder="Inserire id utente" required>
							</div>

						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Password <span class="text-danger">*</span></label>
								<input type="password" value="${requestScope.password}" class="form-control" name="password" id="password" placeholder="Inserire password" required>
							</div>
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Invia</button>
						
						</form>
		    
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="./footer.jsp" />
  </body>
</html>