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
                                    <button type="button" onclick="window.location.href='anagraficaCRUD.jsp'">Gestione Anagrafiche</button>
                                </div>
                                <div class="button-wrapper">
                                    <button type="button" onclick="window.location.href='prenotazioniCRUD.jsp'">Gestione Prenotazioni</button>
                                </div>
                                <div class="button-wrapper">
                                    <button type="button" onclick="window.location.href='impiantiCRUD.jsp'">Gestione Impianti</button>
                                </div>
                                <div class="button-wrapper">
                                    <button type="button" onclick="window.location.href='attrezzatureCRUD.jsp'">Gestione Attrezzature</button>
                                </div>
                                <div class="button-wrapper">
                                    <button type="button" onclick="window.location.href='PisteServlet?mode=getall'">Gestione Piste</button>
                                </div>
                                <div class="button-wrapper">
                                    <button type="button" onclick="window.location.href='noleggioCRUD.jsp'">Gestione Noleggi</button>
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
