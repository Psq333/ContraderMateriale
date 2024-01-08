<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.PrenotazioniDTO"
import="java.util.Map" import="java.util.List" import="it.contrader.dto.PisteDTO"
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="css/style.css" rel="stylesheet">
    <title>Edit Prenotazioni</title>
</head>
<body>
<nav class="navbar">

    <a class="flex-logo text-decoration-none" href="homeadmin.jsp">
        <img src="images/mini-logo.png" alt="mini logo">
        <span>
					Contrader Formazione
			</span>
    </a>
    <ul>
        <li>
            <a href="homeadmin.jsp" class="nav-link">Home</a>
        </li>
        <li>
            <a href="UserServlet?mode=getall" class="nav-link">
                Utenti
            </a>
        </li>
        <li>
            <a href="LogoutServlet" class="nav-link">Logout</a>
        </li>
    </ul>

</nav>

<div class="main container">

    <%PrenotazioniDTO p = (PrenotazioniDTO) request.getAttribute("prenotazioneToUpdate");%>

    <form class="create-form" action="PrenotazioniServlet" method="post">
        <input type="hidden" name="mode" value="update">
        <input type="hidden" name="id" value="<%=p.getId_prenotazione()%>">
        <h1 id="title">Modifica prenotazione</h1>
        <div class="form-group">
            <label for="pista">Id Impianto - Id Pista</label>
            <select id="pista" name="idpista">
            <% Map<Integer,List<PisteDTO>> map = (Map<Integer,List<PisteDTO>>)request.getAttribute("map");
                for (Integer key : map.keySet()){
                    List<PisteDTO> piste = (List<PisteDTO>) map.get(key);
                    for(PisteDTO pista : piste){
                        %>
                    <option value="<%=pista.getIdPista()%>"> ID Impianto: <%=key%> - ID Pista: <%=pista.getIdPista()%></option>
                    <%
                    }
                }
                %>
            </select>

            <label for="username">Username </label>
            <input type="text" id="username" name="username" value=<%=p.getUsername()%> readonly>
        </div>

        <div class="form-group">
            <label for="data_i">Data di inizio </label>
            <input type="text" id="data_i" name="data_i" value=<%=p.getData_inizio()%> required>
        </div>
        <div class="form-group">
            <label for="data_f">Data di fine </label>
            <input type="text" id="data_f" name="data_f" value=<%=p.getData_fine()%> required>
        </div>

        <div class="button-wrapper">

            <button type="submit" >Modifica Prenotazione</button>
        </div>
    </form>

</div>

<div class="footer">
    <div class="footer-container">
        <img src="images/logo-final.png" alt="Logo Contrader">
        <p> We make innovation happen </p>
    </div>
    <p class="footer-copyright"> ©2023 Contrader Srl all rights reserved </p>
</div>
</body>
</html>