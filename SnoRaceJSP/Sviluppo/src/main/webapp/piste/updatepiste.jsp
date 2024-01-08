<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.PisteDTO"
         import="it.contrader.dto.ImpiantoDTO"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="css/style.css" rel="stylesheet">
    <title>Edit Pista</title>
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

        <%PisteDTO u = (PisteDTO) request.getAttribute("PisteToUpdate");%>

        <form class="create-form" action="PisteServlet" method="post">
            <input type="hidden" name="mode" value="update">
            <input type="hidden" name="id" value="<%=u.getIdPista()%>">
            <h1 id="title">Modifica pista</h1>

            <div class="form-group">
                <label for="idpista">ID Pista </label>
                <input type="text" id="idpista" name="idpista" value=<%=u.getIdPista()%> required>
            </div>

            <div class="form-group">
                <label for="idimpianto">ID Impianto </label>
                <select id="type" name="idimpianto">
                        <% List<ImpiantoDTO> lista = (List<ImpiantoDTO>) request.getAttribute("impiantis"); %>
                        <%
                        for (ImpiantoDTO impianto : lista) {
                        %>
                         <option> <%=impianto.getIdImpianto()%></option>
                         <%}%>
                          </select>
            </div>

            <div class="form-group">
                <label for="difficolta">Difficolt� </label>
                <input type="text" id="difficolta" name="difficolta" value=<%=u.getDifficolta()%> required>
            </div>

            <div class="form-group">
                <label for="prezzo">Prezzo </label>
                <input type="double" id="prezzo" name="prezzo" value=<%=u.getPrezzo()%> required>
            </div>

            <div class="form-group">
                <label for="prenmax">Prenotazioni Massime </label>
                <input type="number" id="prenmax" name="prenmax" value=<%=u.getPrenMax()%> required>
            </div>

            <div class="button-wrapper">

                <button type="submit" >Modifica Pista</button>
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