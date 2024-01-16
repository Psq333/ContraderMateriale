<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.List"
import="java.io.*"
import="it.contrader.dto.ImpiantoDTO"
import="it.contrader.dto.UserDTO"
import="it.contrader.dto.PistaDTO"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <meta charset="ISO-8859-1">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <title>Amministratore Manager</title>

</head>
<body>
<%@ include file="../css/navbar.jsp" %>
<%@ include file="../prenotazione/getall_prenotazione.jsp" %>
<form class="create-form" id="floatright" action="/prenotazione/insert" method="post">
    <h1 id="title">Aggiungi Prenotazione</h1>
    <div class="form-group">
        <label for="impianto">Scegli la struttura</label>
        <select id="impianto" name="idPista">
            <% List<ImpiantoDTO> listImp = (List<ImpiantoDTO>) request.getAttribute("impianti"); %>
            <%
            for (ImpiantoDTO imp : listImp) {
            List<PistaDTO> piste = imp.getPiste();
                for(PistaDTO pista : piste){
            %>
            <option value=<%=pista.getIdPista()%>> Impianto: <%=imp.getNome()%> - Pista: <%=pista.getNome()%></option>
            <% }} %>
        </select>
    </div>

    <div class="form-group">
        <label for="user">Scegli l'utente </label>
        <select name="user" id="user">
            <%List<UserDTO> listUsers = (List<UserDTO>) request.getAttribute("user"); %>
                <%
                for (UserDTO user : listUsers) {
                %>
                <option value=<%=user.getId()%>> <%=user.getUsername()%></option>
                <% } %>
        </select>
    </div>

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
