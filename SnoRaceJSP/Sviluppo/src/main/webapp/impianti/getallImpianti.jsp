<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.ImpiantoDTO" import="java.util.List"
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/vittoriostyle.css" rel="stylesheet">
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
                <th class="text-primary-emphasis" scope="col">Amministratore</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
            for (ImpiantoDTO i : list) {
            %>
            <tr>
                <th scope="row"><a href=ImpiantoServlet?mode=read&id=<%=i.getIdImpianto()%>>
                    <%=i.getIdImpianto()%>
                </a></th>
                <td><%=i.getNome()%></td>
                <td><%=i.getDescrizione()%></td>
                <td><%=i.getLuogo()%></td>
                <td><%=i.getAmministratore()%></td>
                <%if(userDTO.getUsertype().name().equals("AMMINISTRATORE") || userDTO.getUsertype().name().equals("ADMIN")) {%>
                    <td><a href=ImpiantoServlet?mode=prepare_update&idImpianto=<%=i.getIdImpianto()%>>Edit</a></td>
                    <td><a href=ImpiantoServlet?mode=delete&idImpianto=<%=i.getIdImpianto()%>>Delete</a></td>
                <%} else if(userDTO.getUsertype().name().equals("USER")){%>
                    <td><a href=PisteServlet?mode=get_piste_impianto&idImpianto=<%=i.getIdImpianto()%>>Scegli</a></td>
                <%} } %>
            </tr>
            </tbody>

        </table>
    </div>

    </body>
</html>