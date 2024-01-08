<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.PisteDTO"%>

<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="css/style.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <title>Info Pista</title>
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
    <%PisteDTO u = (PisteDTO) request.getAttribute("Piste");%>

    <div class="card">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="images/user.jpg" class="img-fluid rounded-start" alt="user avatar">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">Pista Info</h5>
                    <p class="card-text">ID Pista: <%=u.getIdPista()%></p>
                    <p class="card-text">ID Impianto: <%=u.getIdImpianto()%></p>
                    <p class="card-text">Difficolt�: <%=u.getDifficolta()%></p>
                    <p class="card-text">Prezzo: <%=u.getPrezzo()%></p>
                    <p class="card-text">Prenotazioni massime: <%=u.getPrenMax()%></p>
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