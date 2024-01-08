<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.AttrezzatureDTO"%>

<html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Attrezzature relative all impianto </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet">
    </head>

<body>

<nav class="navbar">

        <a class="flex-logo text-decoration-none" href="home${user.getUsertype().getName(user.getUsertype())}.jsp">
            <img src="images/mini-logo.png" alt="mini logo">
            <span>
                    Contrader Formazione
            </span>
        </a>
        <ul>
            <li>
                <a href="home${user.getUsertype().getName(user.getUsertype())}.jsp" class="nav-link">Home</a>
            </li>
            <li>
                <a href="LogoutServlet" class="nav-link">Logout</a>
            </li>
        </ul>

    </nav>

    </nav>
         <div class="main container">
         <h1> Lista Attrezzature </h1>
         <div class="container d-flex">
         	<%
         		List<AttrezzatureDTO> list = (List<AttrezzatureDTO>) request.getAttribute("userattrezzature");
         	%>

         	<table class="table">
         		<thead>
         			<tr>
         				<th class="text-primary-emphasis" scope="col">ID Attrezzature</th>
                        <th class="text-primary-emphasis" scope="col">Nome</th>
                        <th class="text-primary-emphasis" scope="col">Descrizione</th>
                        <th class="text-primary-emphasis" scope="col">Prezzo</th>
                        <th class="text-primary-emphasis" scope="col">ID Impianto</th>
         				<th></th>
         				<th></th>
         			</tr>
         		</thead>
         		<tbody>
         		<%
         			for (AttrezzatureDTO u : list) {
         		%>
         		<tr>
         			<th scope="row">
         			<a v  href=AttrezzatureServlet?mode=read&id=<%=u.getIdAttrezzature()%>> <%=u.getIdAttrezzature()%> </a></th>
         			<td><%=u.getNome()%></td>
                    <td><%=u.getDescrizione()%></td>
                    <td><%=u.getPrezzo()%> $</td>
                    <td><%=u.getIdImpianto()%></td>
                    <td><a onclick="window.location.href='AttrezzatureServlet?mode=SCELTAUSER&IdAttrezzatura=<%=u.getIdAttrezzature()%>'" class="nav-link">Scegli</a></td>
         		</tr>
         		</tbody>
         		<%
         			}
         		%>
         	</table>
         </div>
        </div>
        <div class="footer">
            <div class="footer-container">
                <img src="images/logo-final.png" alt="Logo Contrader">
                <p> We make innovation happen </p>
            </div>
            <p class="footer-copyright"> �2023 Contrader Srl all rights reserved </p>
        </div>


 </body>
</html>