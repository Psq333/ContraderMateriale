<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.NoleggioDTO"
	import="it.contrader.dto.AttrezzatureDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet">
<title>Noleggio Manager</title>

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
            <a href="UserServlet?mode=read&amp;id=${user.getId()}" class="nav-link">
                Account
            </a>
        </li>
        <li>
            <a href="LogoutServlet" class="nav-link">Logout</a>
        </li>
    </ul>

</nav>
<div class="main container d-flex">
	<%
		List<NoleggioDTO> list = (List<NoleggioDTO>) request.getAttribute("noleggi");
	%>

	<table class="table w-25">
		<thead>
			<tr>
				<th class="text-primary-emphasis" scope="col">ID Noleggio</th>
				<th class="text-primary-emphasis" scope="col">Username</th>
				<th class="text-primary-emphasis" scope="col">ID Attrezzatura</th>
				<th class="text-primary-emphasis" scope="col">data inizio</th>
				<th class="text-primary-emphasis" scope="col">data fine</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<%
			for (NoleggioDTO u : list) {
		%>
		<tr>
			<th scope="row"><a href=NoleggioServlet?mode=read&id=<%=u.getIdNoleggio()%>>
				<%=u.getIdNoleggio()%>
			</a></th>
			<td><%=u.getUsername()%></td>
			<td><%=u.getIdAttrezzatura()%></td>
			<td><%=u.getData_inizio()%></td>
			<td><%=u.getData_fine()%></td>
			<td><a href=NoleggioServlet?mode=prepare_update&id=<%=u.getIdNoleggio()%>>Edit</a></td>
			<td><a href=NoleggioServlet?mode=delete&id=<%=u.getIdNoleggio()%>>Delete</a></td>
		</tr>
		</tbody>
		<%
			}
		%>
	</table>

	<form class="create-form" action="NoleggioServlet?mode=insert" method="post">
	<input type="hidden" name="mode" value="insert">

		<h1 id="title">Aggiungi noleggio</h1>
		<div class="form-group">
			<label for="username">username </label>
			<input type="text" id="username" name="username" placeholder="Inserisci username..." required>
		</div>

		<div class="form-group">
        <label for="idattrizzatura">ID Attrezzatura </label>
        <select id="type" name="idattrezzatura">
                <% List<AttrezzatureDTO> lista = (List<AttrezzatureDTO>) request.getAttribute("attrezzatures"); %>
                <%
                for (AttrezzatureDTO t : lista) {
                %>
                 <option> <%=t.getIdAttrezzature()%></option>
                 <%}%>
                  </select>
        </div>






        <div class="form-group">
        	<label for="data_inizio">data_inizio </label>
        	<input type="date" id="data_inizio" name="data_inizio" placeholder="Inserisci data inizio noleggio..." required>
        </div>
        <div class="form-group">
             <label for="data_fine">data_fine </label>
             <input type="date" id="data_fine" name="data_fine" placeholder="Inserisci data fine noleggio..." required>
        </div>

		<div class="button-wrapper">

			<button type="submit" >Inserisci noleggio</button>
		</div>
	</form>
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