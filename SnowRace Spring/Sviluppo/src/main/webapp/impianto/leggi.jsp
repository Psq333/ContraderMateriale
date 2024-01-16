<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
                              import="it.contrader.dto.ImpiantoDTO"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>

</head>
<body>
	<%@ include file="../css/navbar.jsp"%>

	    <div class="button-wrapper">
            <a href="/home<%=userDTO.getUsertype().name().toLowerCase() %>.jsp" class="nav-link">Indietro</a>
        </div>

	<div class="main">
		<%
			ImpiantoDTO imp = (ImpiantoDTO) request.getAttribute("impiantoDTO");
		%>


		<table>
			<tr>
				<th>ID Impianto</th>
				<th>Nome</th>
				<th>Descrizione</th>
				<th>Luogo</th>
				<th>Amministratore</th>
			</tr>
			<tr>
			<%if(userDTO.getUsertype().name().equals("AMMINISTRATORE") || userDTO.getUsertype().name().equals("ADMIN")) {%>
                <td><%=imp.getIdImpianto()%></td>
            <%}%>
				<td><%=imp.getNome()%></td>
				<td><%=imp.getDescrizione()%></td>
				<td><%=imp.getLuogo()%></td>
				<td><%=imp.getAmministratore().getUsername()%></td>
			</tr>
		</table>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>


	</div>

	<%@ include file="../css/footer.jsp"%>
</body>
</html>