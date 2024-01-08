<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.AnagraficaDTO" %>

<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="css/style.css" rel="stylesheet">
    <title>Edit User</title>
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

    <%AnagraficaDTO a = (AnagraficaDTO) request.getAttribute("anagToUpdate");%>

    <form class="create-form" action="AnagraficaServlet" method="post">
        <input type="hidden" name="mode" value="update">
        <input type="hidden" name="username" value="<%=a.getUsername()%>">
        <h1 id="title">Modifica utente</h1>
        <div class="form-group">
            <label for="nome">Nome </label>
            <input type="text" id="nome" name="nome" value=<%=a.getNome()%> required>
        </div>
        <div class="form-group">
            <label for="cognome">Cognome </label>
            <input type="text" id="cognome" name="cognome" value=<%=a.getCognome()%> required>
        </div>
        <div class="form-group">
            <label for="indirizzo">Indirizzo </label>
            <input type="text" id="indirizzo" name="indirizzo" value=<%=a.getIndirizzo()%> required>
        </div>
        <div class="form-group">
            <label for="data">Data di nascita </label>
            <input type="date" id="data" name="data" value=<%=a.getDataNascita()%> required>
        </div>
        <div class="form-group">
            <label for="luogo">Luogo di nascita </label>
            <input type="text" id="luogo" name="luogo" value=<%=a.getLuogoNascita()%> required>
        </div>

        <div class="button-wrapper">

            <button type="submit" >Modifica Dati di <%=a.getUsername()%></button>
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