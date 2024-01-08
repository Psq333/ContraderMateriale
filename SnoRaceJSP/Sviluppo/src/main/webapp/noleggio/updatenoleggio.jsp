<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="it.contrader.dto.NoleggioDTO" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="css/style.css" rel="stylesheet">
    <title>Edit Noleggio</title>
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

        <%NoleggioDTO u = (NoleggioDTO) request.getAttribute("noleggioDaModificare");%>

        <form class="create-form" action="NoleggioServlet" method="post">
            <input type="hidden" name="mode" value="update">
            <input type="hidden" name="idnoleggio" value="<%=u.getIdNoleggio()%>">
            <h1 id="title">Modifica pista</h1>

            <div class="form-group">
            	<label for="username">username </label>
                <input type="text" id="username" name="username" value="<%=u.getUsername()%>" required>
            </div>
            <div class="form-group">
                 <label for="idattrezzatura">ID attrezzatura </label>
                 <input type="text" id="idattrezzatura" name="idattrezzatura" value="<%=u.getIdAttrezzatura()%>" required>
            </div>
            <div class="form-group">
                  <label for="data_inizio">data_inizio </label>
                  <input type="date" id="data_inizio" name="data_inizio" value="<%=u.getData_inizio()%>" required>
            </div>
            <div class="form-group">
                  <label for="data_fine">data_fine </label>
                  <input type="date" id="data_fine" name="data_fine" value="<%=u.getData_fine()%>" required>
            </div>

            <div class="button-wrapper">

                <button type="submit" >Modifica Noleggio</button>
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