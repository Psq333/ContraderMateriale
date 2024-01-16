<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"  import="it.contrader.dto.UserDTO"%>
<html>
<head>

<meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<%UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user"); %>
<div class="navbar">
    <a href="/home<%= ((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase() %>.jsp" class="nav-link">Home</a>
    <a href="/user/logout" id="logout">Logout</a>
</div>
</body>
</html>