<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.NoleggioDTO" import="java.util.List" import="it.contrader.dto.UserDTO"
%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <link href="/css/vittoriostyle.css" rel="stylesheet">
    </head>
    <body>

    <div class="main container">
            <div class="button-wrapper">

        <%NoleggioDTO n = (NoleggioDTO) request.getSession().getAttribute("noleggioDTO");%>
        <a href="/noleggio/getall">Indietro</a>

        <form class="create-form" action="/noleggio/update" method="post">
            <input type="hidden" name="mode" value="update">
            <input type="hidden" name="idnoleggio" value="<%=n.getIdnoleggio()%>">
            <h1 id="title">Modifica Noleggio</h1>
            <div class="form-group">
                <label for="nome">Username </label>

                 <select id="user" name="user">
                            <%for (UserDTO user: (List<UserDTO>)request.getAttribute("users")) {%>
                                <option value="<%= user.getId() %>"><%= user.getUsername() %></option>
                            <%}%>
                        </select>
            </div>
           <div class="form-group">
               <label for="idattrezzatura">Attrezzatura </label>
               <input type="text" id="idattrezzatura" name="idattrezzatura" value="<%= (n != null && n.getIdattrezzatura() != null) ? n.getIdattrezzatura().getIdAttrezzatura() : "" %>" readonly required>
           </div>
            <div class="form-group">
                <label for="startDate">Data inizio </label>
                <input type="date" id="startDate" name="startDate" value=<%=n.getStartDate()%> required>
            </div>
            <div class="form-group">
                 <label for="endDate">Data fine </label>
                 <input type="date" id="endDate" name="endDate" value=<%=n.getEndDate()%> required>
            </div>
            <div class="button-wrapper">

                <button type="submit" >Modifica Noleggio</button>
            </div>
        </form>
    </div>
    </body>
</html>
