<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.List"
import="it.contrader.dto.PrenotazioniDTO"
import="it.contrader.dto.UserDTO"
import="java.lang.String"%>
<!DOCTYPE html>
<html>
<--! prenotazionimanager.jsp= (onclick) gestione prenotazioni post log AMMINISTRATORE-->
<head>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <title>PRENOTAZIONI Manager</title>

</head>
<body>
<nav class="navbar">
    <%UserDTO user = (UserDTO) request.getSession().getAttribute("user"); %>
    <a class="flex-logo text-decoration-none" href=home<%=user.getUsertype().name().toLowerCase()%>.jsp>

        <img src="images/mini-logo.png" alt="mini logo">
        <span>
                    Contrader Formazione
            </span>
    </a>
    <ul>
        <li>
            <a href="home<%=user.getUsertype().name().toLowerCase()%>.jsp" class="nav-link">Home</a>
        </li>
        <li>
            <a href="LogoutServlet" class="nav-link">Logout</a>
        </li>
    </ul>

</nav>
<div class="main container d-flex">
    <%     List<PrenotazioniDTO> list = (List<PrenotazioniDTO>) request.getAttribute("prenotazioni");    %>

    <table class="table w-25">
        <thead>
        <tr>
            <th class="text-primary-emphasis" scope="col">ID</th>
            <th class="text-primary-emphasis" scope="col">Id_pista</th>
            <th class="text-primary-emphasis" scope="col">Username</th>
            <th class="text-primary-emphasis" scope="col">Data_inizio</th>
            <th class="text-primary-emphasis" scope="col">Data_fine</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
        for (PrenotazioniDTO u : list) {
        %>
        <tr>
            <th scope="row"><a href=PrenotazioniServlet?mode=read&id=<%=u.getId_prenotazione()%>>
                <%=u.getId_prenotazione()%>
            </a></th>
            <td><%=u.getId_pista()%></td>
            <td><%=u.getUsername()%></td>
            <td><%=u.getData_inizio()%></td>
            <td><%=u.getData_fine()%></td>
            <td><a href=PrenotazioniServlet?mode=prepare_update&id=<%=u.getId_prenotazione()%>>Edit</a></td>
            <td><a href=PrenotazioniServlet?mode=delete&id=<%=u.getId_prenotazione()%>>Delete</a></td>
        </tr>
        </tbody>
        <%
        }
        %>
    </table>
 <!-- non un form ma un tasto con "inserisci" e poi si reindirizza ad una pagina di inserimento prenotazione (getAllimpianti, getAllPiste...)-->
    <!-- <form class="create-form" action="PrenotazioniServlet?mode=insert" method="post">
        <h1 id="title">Aggiungi prenotazione</h1>
        <div class="form-group">
            <label for="id_prenotazione">Username </label>
            <input type="text" id="pista" name="username" placeholder="Inserisci username..." required>
        </div>
        <div class="form-group">
            <label for="pass">Password </label>
            <input type="password" id="pass" name="password" placeholder="Inserisci password..." required>
        </div>
        <div class="form-group">

            <label for="type">Usertype</label>
            <select id="type" name="usertype">
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
                <option value="AMMINISTRATORE">AMMINISTRATORE</option>
            </select>

        </div>

            -->
    <div class="button-wrapper">
        <a href=PrenotazioniServlet?mode=aggiunta>Aggiungi prenotazione</a>
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