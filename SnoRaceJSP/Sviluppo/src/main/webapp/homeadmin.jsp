<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"
    import="java.lang.String"%>

<html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Home Admin</title>
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

    <div class="main container">

        <h1>Welcome ${user.getUsername()}</h1>

         <form class="create-form">

         <h1> Menu Admin</h1>
         <div class="button-wrapper">
            <button type="button" onclick="window.location.href='ImpiantoServlet?mode=get_all'">Gestione Impianti</button>
         </div>
         <div class="button-wrapper">
            <button type="button" onclick="window.location.href='PisteServlet?mode=getallimpianto'">Gestione Piste</button>
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
