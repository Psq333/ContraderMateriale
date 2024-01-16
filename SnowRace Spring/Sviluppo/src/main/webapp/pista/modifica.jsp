<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import="it.contrader.dto.PistaDTO"
import="java.util.List"
import="it.contrader.dto.UserDTO"
import="it.contrader.dto.ImpiantoDTO"
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
        <%PistaDTO p = (PistaDTO) request.getSession().getAttribute("pistaDTO");%>

        <form class="create-form " action="/pista/update" method="post">
            <input type="hidden" name="mode" value="update">
            <input type="hidden" name="idPista" value="<%=p.getIdPista()%>">
            <input type="hidden" name="idImpianto" value="<%=p.getImpianto()%>">
            <h1 id="title">Modifica Pista</h1>
            <div class="form-group">
                <label for="nome">Nome Pista </label>
                <input type="text" id="nome" name="nomePista" value=<%=p.getNome()%> required>
            </div>
            <div class="form-group">
                <label for="difficolta">Difficoltà </label>
                <input type="text" id="difficolta" name="difficoltaPista" value="<%=p.getDifficolta()%>" required>
            </div>
            <div class="form-group">
                <label for="prezzo">Prezzo </label>
                <input type="double" id="prezzo" name="prezzoPista" value="<%=p.getPrezzo()%>" required>
            </div>
            <div class="form-group">
                <label for="prenMax">Prenotazioni Massime </label>
                <input type="number" id="prenMax" name="prenMax" value="<%=p.getPrenotazioniMax()%>" required>
            </div>
                <div class="form-group">
                    <%UserDTO user = (UserDTO) request.getSession().getAttribute("user");%>
                    <%if(user.getUsertype().name().equals("AMMINISTRATORE")) {%>

                    <label for="type">Impianto</label>
                    <select id="type" name="impianto">
                        <% List<ImpiantoDTO> listImp = (List<ImpiantoDTO>) request.getAttribute("impianti"); %>
                        <%
                        for (ImpiantoDTO imp : listImp) {
                        %>
                        <option> <%=imp.getNome()%></option>
                        <%}%>
                    </select>


                    <%}%>

                </div>
            <div class="button-wrapper">

                <button type="submit" >Modifica Account</button>
            </div>
        </form>
    </div>
    </body>
</html>