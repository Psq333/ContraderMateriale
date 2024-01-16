<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.List"
import="it.contrader.dto.PistaDTO"
import="it.contrader.dto.ImpiantoDTO"
import="it.contrader.dto.UserDTO"%>

<!DOCTYPE html>
<html>
<head>
    <title>Inserimento prenotazione</title>
       </head>
<body>
<%@ include file="../css/navbar.jsp"%>

<form class="create-form" action="/prenotazione/insert" method="post">
    <h1 id="title">Aggiungi Prenotazione</h1>
    <div class="form-group">
        <label for="impianto">Nome Impianto </label>
        <%ImpiantoDTO imp = (ImpiantoDTO)request.getAttribute("impiantoDTO");%>
        <input type="hidden" name="idImpianto" value="<%=imp.getIdImpianto()%>" readonly> <!--l`id serve alla label quindi qui posso ometterlo-->
        <input type="text" id="impianto" name="nomeImpianto" value="<%=imp.getNome()%>" readonly>
    </div>
    <div class="form-group">
        <label for="pista">Nome Pista </label>
        <%PistaDTO p = (PistaDTO)request.getAttribute("pistaDTO");%>
        <input type="hidden" name="idPista" value="<%=p.getIdPista()%>" readonly>
        <input type="text" id="pista" name="nomePista" value="<%=p.getNome()%>" readonly>
    </div>
    <% UserDTO us = (UserDTO)request.getSession().getAttribute("user"); %>
    <input type="hidden" name="user" value="<%=us.getId()%>">
    <div class="form-group">
        <label for="data_i">Data inizio </label>
        <input type="date" id="data_i" name="dataInizio" placeholder="Inserisci Data di inizio..." required>
    </div>
    <div class="form-group">
        <label for="data_f">Data fine </label>
        <input type="date" id="data_f" name="dataFine" placeholder="Inserisci Data di fine..." required>
    </div>

    </div>
    <div class="button-wrapper">

        <button type="submit" >Inserisci prenotazione </button>
    </div>
</form>

</body>
</html>