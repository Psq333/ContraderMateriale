<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.NoleggioDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/style.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>Read Noleggio</title>
</head>
<body>
	<nav class="navbar">

		<a class="flex-logo text-decoration-none" href="homeadmin.jsp">
			<img src="images/mini-logo.png" alt="mini logo">
			<span>
					Contrader Formazione
			</span>
		</a>
		<ul>
			<li>
				<a href="homenoleggio.jsp" class="nav-link">Home</a>
			</li>
			<li>
				<a href="NoleggioServlet?mode=getall" class="nav-link">
					Noleggi
				</a>
			</li>
			<li>
				<a href="LogoutServlet" class="nav-link">Logout</a>
			</li>
		</ul>

	</nav>

	<div class="main container">
		<%NoleggioDTO n = (NoleggioDTO) request.getAttribute("noleggio");%>

		<div class="card">
			<div class="row g-0">
				<div class="col-md-4">
					<img src="images/noleggio.jpg" class="img-fluid rounded-start" alt="noleggio avatar">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title">Noleggio Info</h5>
						<p class="card-text">Username: <%=n.getUsername()%></p>
						<p class="card-text">Data inizio: <%=n.getData_inizio()%></p>
						<p class="card-text">Data fine: <%=n.getData_fine()%></p>
						<p class="card-text">ID Attrezzatura: <%=n.getIdAttrezzatura()%></p>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div class="footer">
		<div class="footer-container">
			<img src="images/logo-final.png" alt="Logo Contrader">
			<p> We make innovation happen </p>
		</div>
		<p class="footer-copyright"> �2023 Contrader Srl all rights reserved </p>
	</div>
</body>
</html>