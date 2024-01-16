<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.ImpiantoDTO" import="java.util.List"
%>

<!DOCTYPE html>
<html class="main">
    <head>
        <meta charset="ISO-8859-1">
        <link href="css/style.css" rel="stylesheet">
        <link href="/css/vittoriostyle.css" rel="stylesheet">
            <style>
                .hidden-column {
                    display: none;
                }
            </style>
    </head>

    <body>
    <div>
        <%
        List<ImpiantoDTO> list = (List<ImpiantoDTO>) request.getAttribute("impianti");
        %>

        <table class="table w-25">
            <thead>
                <tr>
                    <th class="text-primary-emphasis" scope="col">ID</th>
                    <th class="text-primary-emphasis" scope="col">Nome</th>
                    <th class="text-primary-emphasis" scope="col">Descrizione</th>
                    <th class="text-primary-emphasis" scope="col">Luogo</th>
                    <th <%if(!userDTO.getUsertype().name().equals("AMMINISTRATORE")){ %> class="hidden-column"<%}%> scope="col">Amministratore</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
        <tbody>
            <%
            for (ImpiantoDTO i : list) {
            %>
            <tr>
                <th scope="row"><a href="/impianto/read?idImpianto=<%=i.getIdImpianto()%>">
                    <%=i.getIdImpianto()%>
                </a></th>
                <td><%=i.getNome()%></td>
                <td><%=i.getDescrizione()%></td>
                <td><%=i.getLuogo()%></td>
                <td <%if(!userDTO.getUsertype().name().equals("AMMINISTRATORE")){ %> class="hidden-column"<%}%> value=<%=i.getAmministratore().getId()%>><%=i.getAmministratore().getUsername()%></td>
                <%if(userDTO.getUsertype().name().equals("AMMINISTRATORE") || userDTO.getUsertype().name().equals("ADMIN")) {%>
                    <td><a href="/impianto/preupdate?idImpianto=<%=i.getIdImpianto()%>">Edit</a></td>
                    <td><a href="/impianto/delete?idImpianto=<%=i.getIdImpianto()%>">Delete</a></td>
                <%} else if(userDTO.getUsertype().name().equals("USER")){%>
                    <td><a href="/pista/getalluser?idImpianto=<%=i.getIdImpianto()%>">Scegli</a></td>
                <%} } %>
            </tr>
            </tbody>

        </table>
    </div>
    </body>
</html>