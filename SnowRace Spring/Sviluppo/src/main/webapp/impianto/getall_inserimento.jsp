<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.ImpiantoDTO" import="java.util.List"
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <link href="/css/vittoriostyle.css" rel="stylesheet">
        <title>Inserimento Impianto</title>
    </head>
    <body>
    <%@ include file="../css/navbar.jsp" %>
    <div>
    <div class="button-wrapper">
        <a href="/home<%=userDTO.getUsertype().name().toLowerCase() %>.jsp" class="nav-link">Indietro</a>
    </div>

        <div>
            <%if(userDTO.getUsertype().name().equals("USER")) {%>
            <h1> Scegli impianto </h1>
            <% }%>
        </div>
        <div class="main container d-flex">
        <%@ include file="../impianto/getall.jsp" %>
        <%if(!userDTO.getUsertype().name().equals("USER")) {%>
            <%@ include file="../impianto/inserisci.jsp" %>
        <%} %>


    </div>
       <%@ include file="../css/footer.jsp" %>
    </body>
</html>