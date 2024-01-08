<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.ImpiantoDTO" import="java.util.List"
import="it.contrader.dto.UserDTO" import="it.contrader.dto.PrenotazioniDTO"
                                  import="java.util.Map"  import="it.contrader.dto.PisteDTO"
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
            <label for="user">Username</label>
            <select id="user" name="username">
                <% List<UserDTO> userss = (List<UserDTO>)request.getAttribute("users");
                for (UserDTO uk : userss){%>
                    <option> <%=uk.getUsername()%></option>
                <%}%>
            </select>
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