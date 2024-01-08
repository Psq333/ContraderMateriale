<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.ImpiantoDTO" import="java.util.List"
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/vittoriostyle.css" rel="stylesheet">
        <title>Inserimento Impianto</title>
    </head>
    <body>
    <%@ include file="/css/navbar.jsp" %>
    <div>
    <div class="button-wrapper">
        <a href="home${user.getUsertype().getName(user.getUsertype())}.jsp" class="nav-link">Indietro</a>
    </div>

        <div>
            <%if(userDTO.getUsertype().name().equals("USER")) {%>
            <h1> Scegli impianto </h1>
            <% }%>
        </div>
        <div class="main container d-flex">
        <%@ include file="../impianti/getallImpianti.jsp" %>
        <%if(!userDTO.getUsertype().name().equals("USER")) {%>
            <%@ include file="../impianti/InserimentoImpianto.jsp" %>
        <%} %>


    </div>
    </div>
       <%@ include file="../css/footer.jsp" %>
    </body>
</html>