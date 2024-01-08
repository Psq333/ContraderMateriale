<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"
	import="it.contrader.dto.AnagraficaDTO"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/style.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>Read User</title>
</head>
<body>
	<nav class="navbar">
		<a class="flex-logo text-decoration-none" href="home${user.getUsertype().getName(user.getUsertype())}.jsp">
			<img src="images/mini-logo.png" alt="mini logo">
			<span>
					Contrader Formazione
			</span>
		</a>
		<ul>
			<li>
				<a href="home${user.getUsertype().getName(user.getUsertype())}.jsp" class="nav-link">Home</a>
			</li>
			<li>
				<a href="LogoutServlet" class="nav-link">Logout</a>
			</li>
		</ul>
	</nav>


	<div class="main container">
		<%UserDTO u = (UserDTO) request.getAttribute("user");%>
		<%AnagraficaDTO a = (AnagraficaDTO) request.getAttribute("anag");%>
		<div class="card">
			<div class="row g-0">
				<div class="col-md-4">
					<img src="images/user.jpg" class="img-fluid rounded-start" alt="user avatar">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title">User Info</h5>
						<p class="card-text">Username: <%=u.getUsername()%></p>
						<p class="card-text">Password: <%=u.getPassword()%></p>
						<p class="card-text">Nome: <%=a.getNome()%></p>
						<p class="card-text">Cognome: <%=a.getCognome()%></p>
						<p class="card-text">Indirizzo: <%=a.getIndirizzo()%></p>
						<p class="card-text">Luogo di nascita: <%=a.getLuogoNascita()%></p>
						<p class="card-text">Data di nascita: <%=a.getDataNascita()%></p>
					</div>
				</div>
				<div>
					<a class="registration-btn" href=UserServlet?mode=prepare_update&id=<%=u.getId()%>>Modifica credenziali</a>
					<a class="registration-btn" href=AnagraficaServlet?mode=prepare_update&id=<%=a.getId()%>>Modifica dati personali</a>
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