<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List"
    import="it.contrader.dto.PistaDTO"
    import="it.contrader.dto.UserDTO"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Pista Read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read Pista</title>

</head>
<body>
	<%@ include file="../css/navbar.jsp"%>
	<br>

	<div class="main">
		<%
			PistaDTO pista = (PistaDTO) request.getAttribute("pistaDTO");
		%>


		<table>
			<tr>
				<th>ID Pista</th>
				<th>Nome</th>
				<th>Difficoltà</th>
				<th>prezzo</th>
				<th>Prnotazioni Massime</th>
			</tr>
			<tr>
			<%if(userDTO.getUsertype().name().equals("AMMINISTRATORE") || userDTO.getUsertype().name().equals("ADMIN")) {%>
                <td><%=pista.getIdPista()%></td>
            <%}%>
				<td><%=pista.getNome()%></td>
				<td><%=pista.getDifficolta()%></td>
				<td><%=pista.getPrezzo()%></td>
				<td><%=pista.getPrenotazioniMax()%></td>
			</tr>
		</table>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>


	</div>

	<%@ include file="../css/footer.jsp"%>
</body>
</html>