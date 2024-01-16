<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.PrenotazioneDTO" import="java.util.List" import="it.contrader.dto.UserDTO"
import="it.contrader.dto.PistaDTO" import="it.contrader.dto.ImpiantoDTO"
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<%@ include file="../css/navbar.jsp" %>
<div class="main container">
    <div class="button-wrapper">
        <a href=ImpiantoServlet?mode=get_all>Indietro</a>
    </div>
    <%PrenotazioneDTO p = (PrenotazioneDTO) request.getAttribute("prenotazioni");%> <!--request restituisce un oggeto di tipo Object quindi serve il cast-->

    <form class="create-form" action="/prenotazione/update" method="post">
        <input type="hidden" name="mode" value="update">
        <input type="hidden" name="idPrenotazione" value="<%=p.getIdPrenotazione()%>">
        <h1 id="title">Modifica prenotazione</h1>
        <div class="form-group">
        <%if (request.getAttribute("source")!=null){%>
            <input type="hidden" name="source" value="ricerca">>
        <%}%>
        <div class="form-group">
            <%UserDTO u = (UserDTO) p.getUser();%>
            <input type="hidden" id="user" name="user" value=<%=u.getId()%> required>
        </div>

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
            <label for="dataInizio">Data Inizio </label>
            <input type="date" id="dataInizio" name="dataInizio" value=<%=p.getDataInizio()%> required>
        </div>

            <div class="form-group">
                <label for="dataFine">Data Fine </label>
                <input type="date" id="dataFine" name="dataFine" value=<%=p.getDataFine()%> required>
            </div>

        <div class="button-wrapper">

            <button type="submit" >Modifica prenotazione </button>
        </div>
    </form>
</div>
</body>
</html>