<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.List"
import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <title>User Manager</title>

</head>
<body>


    <form class="create-form" action="ImpiantoServlet?mode=insert" method="post">
        <h1 id="title">Aggiungi Impianto</h1>
        <div class="form-group">
            <label for="nome">Nome Impianto </label>
            <input type="text" id="nome" name="nomeImpianto" placeholder="Inserisci nome Impianto..." required>
        </div>
        <div class="form-group">
            <label for="descrizione">Descrizione </label>
            <input type="text" id="descrizione" name="descrizioneImpianto" placeholder="Inserisci descrizione Impianto..." required>
        </div>
        <div class="form-group">
            <label for="luogo">Luogo </label>
            <input type="text" id="luogo" name="luogoImpianto" placeholder="Inserisci luogo Impianto..." required>
        </div>
        <div class="form-group">
                <%if(userDTO.getUsertype().name().equals("AMMINISTRATORE")) {%>
                <label for="type">Amministratore</label>
                <select id="type" name="amministratoreImpianto">
                <% List<UserDTO> listAmm = (List<UserDTO>) request.getAttribute("amministratori"); %>
                <%
                for (UserDTO amm : listAmm) {
                %>
                <option> <%=amm.getUsername()%></option>
                <%}%>
                </select>

                <%} else if(userDTO.getUsertype().name().equals("ADMIN")) {%>
                <label for="amministratore">Amministratore</label>
                    <input type="text" id="amministratore" name="amministratoreImpianto" value=<%=userDTO.getUsername()%> readonly>
                <%}%>


        </div>
        <div class="button-wrapper">

            <button type="submit" >Crea Impianto</button>
        </div>
    </form>

</body>
</html>