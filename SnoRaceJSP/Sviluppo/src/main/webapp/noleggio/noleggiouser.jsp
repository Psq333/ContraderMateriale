<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.NoleggioDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet">
<title>Noleggio Manager</title>

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
            <a href="UserServlet?mode=read&amp;id=${user.getId()}" class="nav-link">
                Account
            </a>
        </li>
        <li>
            <a href="LogoutServlet" class="nav-link">Logout</a>
        </li>
    </ul>

</nav>
<div class="main container d-flex">
	<%
		List<NoleggioDTO> list = (List<NoleggioDTO>) request.getAttribute("noleggoatt");

	%>


	<form class="create-form" action="NoleggioServlet" method="post">
	<input type="hidden" name="mode" value="insert">

		<h1 id="title">Aggiungi noleggio</h1>

	    <input type="hidden" id="username" name="username" value=<%=request.getSession().getAttribute("username")%>>
		<div class="form-group">
        <label for="IdAttrezzatura">ID Attrezzatura </label>
        <input type="number" id="IdAttrezzatura" name="idattrezzatura" value=<%=request.getAttribute("IdAttrezzatura")%> readonly>
        </div>

        <div class="form-group">
        	<label for="data_inizio">data_inizio </label>
        	<input type="date" id="data_inizio" name="data_inizio" placeholder="Inserisci data inizio noleggio..." required>
        </div>
        <div class="form-group">
             <label for="data_fine">data_fine </label>
             <input type="date" id="data_fine" name="data_fine" placeholder="Inserisci data fine noleggio..." required>
        </div>

		<div class="button-wrapper">

			<button type="submit" >Inserisci noleggio</button>
		</div>
	</form>
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