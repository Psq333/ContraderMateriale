
<%@
page import="it.contrader.dto.ImpiantoDTO"
import="it.contrader.dto.UserDTO"
import="it.contrader.dto.NoleggioDTO"
import="it.contrader.dto.AttrezzaturaDTO"%>

<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="ISO-8859-1">
        <link href="css/style.css" rel="stylesheet">
        <link href="/css/vittoriostyle.css" rel="stylesheet">
            <style>
                .hidden-column {
                    display: none;
                }
            </style>


    <title>Inserimento Noleggio</title>
       </head>
<body>


<form class="create-form" action="/noleggio/insert" method="post">
    <h1 id="title">Aggiungi Noleggio</h1>
    <div class="form-group">
        <label for="attrezzatura">Nome Attrezzatura </label>
        <%AttrezzaturaDTO imp = (AttrezzaturaDTO)request.getAttribute("attrezzaturaDTO");%>
        <input type="hidden" name="idattrezzatura" value="<%=imp.getIdAttrezzatura()%>" readonly> <!--l`id serve alla label quindi qui posso ometterlo-->
        <input type="text" id="attrezzatura" name="nome" value="<%=imp.getNome()%>" readonly>
    </div>
    <% UserDTO us = (UserDTO)request.getSession().getAttribute("user"); %>
    <input type="hidden" name="user" value="<%=us.getId()%>">
    <div class="form-group">
        <label for="data_i">Data inizio </label>
        <input type="date" id="data_i" name="startDate" placeholder="Inserisci Data di inizio..." required>
    </div>
    <div class="form-group">
        <label for="data_f">Data fine </label>
        <input type="date" id="data_f" name="endDate" placeholder="Inserisci Data di fine..." required>
    </div>

    </div>
    <div class="button-wrapper">

        <button type="submit" >Inserisca Noleggio </button>
    </div>
</form>

</body>
</html>