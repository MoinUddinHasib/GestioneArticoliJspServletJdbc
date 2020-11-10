<!-- navbar -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/ServletLoggin">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletLoggin">Home</a>
          <a class="dropdown-item" href="ListArticoliServlet">Risultati</a>
          <c:if test="${ sessionScope.ruolo != 'GUEST'}">
          <a class="dropdown-item" href="PrepareInsertArticoloServlet">Inserisci nuovo elemento</a>
          </c:if>
        </div>
      </li>
      <li class="nav-item active">
      <c:if test="${ sessionScope.ruolo != null}">
        <a class="nav-link" href="ServletLogOut">Log-Out <span class="sr-only">(current)</span></a>
        </c:if>
      </li>
       <li>Benvenuto  ${sessionScope.nome }  ${sessionScope.cognome } ${sessionScope.codice_fiscale }  ${sessionScope.ruolo }</li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<!-- <input class="form-control mr-sm-2" type="text" placeholder="Search Categoria" aria-label="Search" name="cerca_categoria">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search Categoria</button>
     </form>
      <form method="post" action="SearchArticoloServlet">
      <input class="form-control mr-sm-2" type="text" placeholder="Search Articolo" aria-label="Search" name="cerca_articolo">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search Articolo</button>
      </form> -->