<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.List"
import="it.contrader.dto.AnagraficaDTO"%>

<html lang="it">
<head>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <title>Anagrafica Manager</title>

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
<div class="main container d-flex">
    <%
    List<AnagraficaDTO> list = (List<AnagraficaDTO>) request.getAttribute("anags");
    %>

    <table class="table w-25">
        <thead>
        <tr>
            <th class="text-primary-emphasis" scope="col">ID</th>
            <th class="text-primary-emphasis" scope="col">Nome</th>
            <th class="text-primary-emphasis" scope="col">Cognome</th>
            <th class="text-primary-emphasis" scope="col">Indirizzo</th>
            <th class="text-primary-emphasis" scope="col">Data di nascita</th>
            <th class="text-primary-emphasis" scope="col">Luogo di nascita</th>
            <th class="text-primary-emphasis" scope="col">Username</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
        for (AnagraficaDTO a : list) {
        %>
        <tr>
            <th scope="row"><a href=AnagraficaServlet?mode=read&id=<%=a.getId()%>>
                <%=a.getId()%>
            </a></th>
            <td><%=a.getNome()%></td>
            <td><%=a.getCognome()%></td>
            <td><%=a.getIndirizzo()%></td>
            <td><%=a.getDataNascita()%></td>
            <td><%=a.getLuogoNascita()%></td>
            <td><%=a.getUsername()%></td>
            <td><a href=AnagraficaServlet?mode=prepare_update&id=<%=a.getId()%>>Edit</a></td>
            <td><a href=AnagraficaServlet?mode=delete&id=<%=a.getId()%>>Delete</a></td>
        </tr>
        </tbody>
        <%
        }
        %>
    </table>

    <form class="create-form" action="AnagraficaServlet?mode=insert" method="post">
        <h1 id="title">Aggiungi i dati dell'utente</h1>
        <div class="form-group">
            <label for="nome">Nome </label>
            <input type="text" id="nome" name="nome" placeholder="Inserisci nome..." required>
        </div>
        <div class="form-group">
            <label for="cognome">Cognome </label>
            <input type="text" id="cognome" name="cognome" placeholder="Inserisci cognome..." required>
        </div>
        <div class="form-group">
            <label for="indirizzo">Indirizzo </label>
            <input type="text" id="indirizzo" name="indirizzo" placeholder="Inserisci indirizzo..." required>
        </div>
        <div class="form-group">
            <label for="data">Data di nascita </label>
            <input type="date" id="data" name="data" placeholder="Inserisci la data di nascita..." required>
        </div>
        <div class="form-group">
            <label for="luogo">Luogo di nascita </label>
            <input type="text" id="luogo" name="luogo" placeholder="Inserisci il luogo di nascita..." required>
        </div>
        <div class="form-group">
            <label for="user">Username </label>
            <input type="text" id="user" name="username" placeholder="Inserisci l'username dell'utente..." required>
        </div>
        <div class="button-wrapper">
            <button type="submit" >Inserisci Account</button>
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