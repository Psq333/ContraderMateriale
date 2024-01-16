<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.ImpiantoDTO" import="java.util.List" import="it.contrader.dto.UserDTO"
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
        <%ImpiantoDTO imp = (ImpiantoDTO) request.getAttribute("impiantoDTO");%>

        <form class="create-form" action="/impianto/update" method="post">
            <input type="hidden" name="mode" value="update">
            <input type="hidden" name="idImpianto" value="<%=imp.getIdImpianto()%>">
            <h1 id="title">Modifica Impianto</h1>
            <div class="form-group">
                <label for="nome">Nome Impianto </label>
                <input type="text" id="nome" name="nomeImpianto" value=<%=imp.getNome()%> required>
            </div>
            <div class="form-group">
                <label for="descrizione">Descrizione </label>
                <input type="text" id="descrizione" name="descrizioneImpianto" value=<%=imp.getDescrizione()%> required>
            </div>
            <div class="form-group">
                <label for="luogo">Luogo </label>
                <input type="text" id="luogo" name="luogoImpianto" value=<%=imp.getLuogo()%> required>
            </div>
            <div class="form-group">
                <%UserDTO user = (UserDTO) request.getSession().getAttribute("user");%>
                <%if(user.getUsertype().name().equals("AMMINISTRATORE")) {%>

                <label for="type">Amministratore</label>
                <select id="type" name="amministratoreImpianto">
                    <% List<UserDTO> listAmm = (List<UserDTO>) request.getAttribute("amministratori"); %>
                    <%
                    for (UserDTO amm : listAmm) {
                    %>
                    <option value=<%=amm.getId()%> <%if(amm.getId()==imp.getAmministratore().getId()){%> selected <%}%>> <%=amm.getUsername()%> </option>
                    <%}%>
                </select>

                <%} else if(user.getUsertype().name().equals("ADMIN")) {%>
                <label for="amministratore">Amministratore</label>
                    <input type="text" id="amministratore" name="amministratore" value=<%=userDTO.getUsername()%> readonly>
                    <input type="hidden" id="amministratore" name="amministratoreImpianto" value=<%=userDTO.getId()%>>
                <%}%>

            </div>
            <div class="button-wrapper">

                <button type="submit" >Modifica Account</button>
            </div>
        </form>
    </div>
    </body>
</html>