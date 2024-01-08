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
        <title>Prenotazione</title>
    </head>
    <body>
    <%@ include file="/css/navbar.jsp" %>
    <div>
    <div class="button-wrapper">
        <a href="home${user.getUsertype().getName(user.getUsertype())}.jsp" class="nav-link">Indietro</a>
    </div>
    <div class="main container">

        <form class="create-form" action="PrenotazioniServlet" method="post">
            <input type="hidden" name="mode" value="insert">
            <h1 id="title">Crea prenotazione</h1>
            <div class="form-group">
            <label for="impianto">Id Impianto</label>
            <input type="text" id="impianto" name="idImpianto" value=<%=request.getAttribute("idImpianto")%> readonly>
            </div>
            <div class="form-group">
                <label for="idpista">Id Pista </label>
                <input type="text" id="idpista" name="idpista" value=<%=request.getAttribute("idPista")%> readonly>
            </div>
            <input type="hidden" name="username" value=<%=userDTO.getUsername()%> readonly>
            <div class="form-group">
                <label for="data_i">Data di inizio </label>
                <input type="date" id="data_i" name="data_i" placeholder="Inserisci Data Inizio..." required>
            </div>
            <div class="form-group">
                <label for="data_f">Data di fine </label>
                <input type="date" id="data_f" name="data_f" placeholder="Inserisci Data Inizio..." required>
            </div>

            <div class="button-wrapper">

                <button type="submit" >Aggiungi Prenotazione</button>
            </div>
        </form>

    </div>
    </div>
       <%@ include file="../css/footer.jsp" %>
    </body>
</html>