<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home SuperAdmin</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>

<nav class="navbar">

    <a class="flex-logo text-decoration-none" href="home${user.getUsertype().name().toLowerCase()}.jsp">

        <img src="images/mini-logo.png" alt="mini logo">
        <span>
					Contrader Formazione
			</span>
    </a>
    <ul>
        <li>
          
            <a href="home${user.getUsertype().name().toLowerCase()}.jsp" class="nav-link">Home</a>

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
<%--

Aggiungere i giusti riferimenti alla pagina da raggiungere
--%>
<div class="main container">
    <h1>Welcome ${user.getUsername()}</h1>

    <form class="create-form">
        <h1> Menu SuperAdmin</h1>
        <div class="button-wrapper">
            <button type="button" onclick="window.location.href='UserServlet?mode=getall'">Gestione Credenziali</button>
        </div>
        <div class="button-wrapper">
            <button type="button" onclick="window.location.href='AnagraficaServlet?mode=getall'">Gestione Anagrafiche</button>
        </div>
        <div class="button-wrapper">
            <button type="button" onclick="window.location.href='PrenotazioniServlet?mode=getall'">Gestione Prenotazioni</button>
        </div>
        <div class="button-wrapper">
            <button type="button" onclick="window.location.href='ImpiantoServlet?mode=get_all'">Gestione Impianti</button>
        </div>
        <div class="button-wrapper">
            <button type="button" onclick="window.location.href='AttrezzatureServlet?mode=get_all'">Gestione Attrezzature</button>
        </div>
        <div class="button-wrapper">
            <button type="button" onclick="window.location.href='PisteServlet?mode=getall'">Gestione Piste</button>
        </div>
        <div class="button-wrapper">
            <button type="button" onclick="window.location.href='NoleggioServlet?mode=GET_ALL'">Gestione Noleggi</button>
        </div>
        <div class="button-wrapper">
            <button type="button" onclick="window.location.href='PrenotazioniServlet?mode=search'">Ricerca Prenotazione</button>
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
