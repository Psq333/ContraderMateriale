<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO" import="it.contrader.dto.AttrezzaturaDTO"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Attrezzatura Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read Attrezzatura</title>
</head>
<body>

    <div class="navbar">
        <a href="/home<%= ((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase() %>.jsp" class="nav-link">Home</a>
        <a href="/user/logout" id="logout">Logout</a>
    </div>
    <br>

    <div class="main">
        <%
            AttrezzaturaDTO u = (AttrezzaturaDTO) request.getSession().getAttribute("attrezzaturaDTO");
        %>


        <table>
            <tr>
                <th>ID Attrezzatura</th>
                <th>Nome</th>
                <th>Descrizione</th>
                <th>Prezzo</th>
                <th>ID Impianto</th>
            </tr>

            <tr>
                <td><%=u.getIdAttrezzatura()%></td>
                <td><%=u.getNome()%></td>
                <td><%=u.getDescrizione()%></td>
                <td><%=u.getPrezzo()%></td>
               <td><a href="/impianto/read?idimpianto=<%=u.getIdImpianto().getIdImpianto()%>">
               <%=u.getIdImpianto().getIdImpianto()%>  </a>
                                                                                             </td>
            </tr>
        </table>

        <br> <br> <br> <br> <br> <br> <br>
        <br> <br> <br> <br> <br> <br> <br>


    </div>

    <%@ include file="../css/footer.jsp"%>
</body>
</html>