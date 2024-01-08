<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Home Admin</title>
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

    <div class="main container">

        <h1>Welcome ${user.getUsername()}</h1>

        <div>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi, architecto delectus eius eum ex harum
            magni minima modi molestias nemo nesciunt nobis qui sint, sunt totam. Doloremque illum perferendis
            reprehenderit.
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
