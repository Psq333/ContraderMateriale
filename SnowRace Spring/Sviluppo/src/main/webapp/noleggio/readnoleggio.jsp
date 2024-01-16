<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO" import="it.contrader.dto.NoleggioDTO"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Noleggio Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read Noleggio</title>
</head>
<body>

    <div class="navbar">
        <a href="/home<%= ((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase() %>.jsp" class="nav-link">Home</a>
        <a href="/user/logout" id="logout">Logout</a>
    </div>
    <br>

    <div class="main">
        <%
            NoleggioDTO u = (NoleggioDTO) request.getSession().getAttribute("noleggioDTO");
        %>


        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Attrezzatura</th>
                <th>Data Inizio</th>
                <th>Data Fine</th>
            </tr>

            <tr>
                <td><%=u.getIdnoleggio()%></td>
                <td><%=u.getUser().getUsername()%></td>
                <td><a href="/noleggio/read?idattrezzatura=<%=u.getIdattrezzatura().getIdAttrezzatura()%>">
                     <%=u.getIdattrezzatura().getIdAttrezzatura()%>
                 </a>
                 </td>
                <td><%=u.getStartDate()%></td>
                <td><%=u.getEndDate()%></td>
            </tr>
        </table>

        <br> <br> <br> <br> <br> <br> <br>
        <br> <br> <br> <br> <br> <br> <br>


    </div>

    <%@ include file="../css/footer.jsp"%>
</body>
</html>