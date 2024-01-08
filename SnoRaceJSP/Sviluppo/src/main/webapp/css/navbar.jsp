<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"  import="it.contrader.dto.UserDTO"%>
<html>
<head>

<meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link href="css/vittoriostyle.css" rel="stylesheet">

    <link href="css/style.css" rel="stylesheet">
</head>
<body>

<nav class="navbar">
    <%UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user"); %>
    <a class="flex-logo text-decoration-none" href="home${user.getUsertype().getName(user.getUsertype())}.jsp">
        <img src="images/mini-logo.png" alt="mini logo">
        <span>
                    Contrader Formazione
            </span>
    </a>
    <ul>
        <li>
            <a class="flex-logo text-decoration-none" href=home<%=userDTO.getUsertype().name().toLowerCase()%>.jsp class="nav-link"> Home</a>
        </li>
        <li>
            <a href="UserServlet?mode=read&amp;id=${user.getId()}" class="nav-link">Account</a>
        </li>
        <li>
            <a href="LogoutServlet" class="nav-link">Logout</a>
        </li>
    </ul>
</nav>



</body>
</html>