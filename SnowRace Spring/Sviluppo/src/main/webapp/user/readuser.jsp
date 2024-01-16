<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"
							import="it.contrader.dto.AnagraficaDTO"%>
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
	<%@ include file="../css/header.jsp"%>
	<div class="navbar">

		<a class="flex-logo text-decoration-none" href="/home<%= ((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase() %>.jsp">
		<img src="images/mini-logo.png" alt="mini logo">
		<span>
					Contrader Formazione
			</span>
		</a>
		<ul>
			<li>

				<a href="/home<%= ((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase() %>.jsp" class="nav-link">Home</a>
			</li>
			<li>
				<a href="/user/read?id=<%=((UserDTO)request.getSession().getAttribute("user")).getId()%>" class="nav-link">
				Account
				</a>
			</li>
			<li>
				<a href="/user/logout" id="logout">Logout</a>
			</li>
		</ul>

	</div>
	<br>

	<div class="main">
		<%
			UserDTO u = (UserDTO) request.getAttribute("dto");
			AnagraficaDTO a = (AnagraficaDTO) request.getAttribute("anag");
		%>


		<table>
			<tr>
				<th>ID</th>
				<th>Username</th>
				<th>Password</th>
				<th>Usertype</th>
				<%if (a != null){%>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Indirizzo</th>
				<th>Luogo di nascita</th>
				<th>Data di nascita</th>
				<%}%>
			</tr>
			<tr>
				<td><%=u.getId()%></td>
				<td><%=u.getUsername()%></td>
				<td><%=u.getPassword()%></td>
				<td><%=u.getUsertype()%></td>
				<%if (a != null){%>
				<td><%=a.getNome()%></td>
				<td><%=a.getCognome()%></td>
				<td><%=a.getIndirizzo()%></td>
				<td><%=a.getLuogoNascita()%></td>
				<td><%=a.getDataNascita()%></td>
				<%}%>
			</tr>
		</table>
		<div>
			<%if (((UserDTO)request.getSession().getAttribute("user")).equals(u)) {%>
			<a href="/user/preupdate?id=<%=u.getId()%>&source=readuser">Modifica credenziali</a><br>
			<%if (a != null){%>
			<a href="/anagrafica/preupdate?id=<%=a.getId()%>&source=readuser">Modifica dati personali</a><br>
			<%} else {%>
			<a href="/anagrafica/preinsert">Aggiungi i tuoi dati personali</a><br>
			<%}}%>
		</div>

	</div>

	<%@ include file="../css/footer.jsp"%>
</body>
</html>