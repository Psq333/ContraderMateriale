<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.NoleggioDTO"%>

<html>

    <head>
        <meta charset="ISO-8859-1">
        <title>noleggi relativi all attrezzatura </title>  <!-- Metti nome attrezzatura --!>
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
     <div class="main container">
     <h1> noleggio dell attrezzatura NOMEATTREZZATURA </h1>
     <div class="container d-flex">
     	<%
     		List<NoleggioDTO> list = (List<NoleggioDTO>) request.getAttribute("noleggi");
     	%>
     	<table class="table">
     		<thead>
     			<tr>
     				<th class="text-primary-emphasis" scope="col">ID noleggio</th>
     				<th class="text-primary-emphasis" scope="col">username</th>
     				<th class="text-primary-emphasis" scope="col">ID attrezzatura</th>
     				<th class="text-primary-emphasis" scope="col">data inizio</th>
     				<th class="text-primary-emphasis" scope="col">data fine </th>
     				<th></th>
     				<th></th>
     			</tr>
     		</thead>
     		<tbody>
     		<%
     			for (NoleggioDTO u : list) {
     		%>
     		<tr>
     			<th scope="row">
     			<a v  href=NoleggioServlet?mode=read&id=<%=u.getIdNoleggio()%>> <%=u.getIdNoleggio()%> </a></th>
     			<td><%=u.getUsername()%></td>
     			<td><%=u.getIdAttrezzatura()%></td>
     			<td><%=u.getData_Inizio()%> $</td>
     			<td><%=u.getData_Fine()%></td>
     			<td><a href=NoleggioServlet?mode=prepare_update&id=<%=u.getIdNoleggio()%>>Edit</a></td>
     			<td><a href=NoleggioServlet?mode=delete&id=<%=u.getIdNoleggio()%>>Delete</a></td>
     		</tr>
     		</tbody>
     		<%
     			}
     		%>
     	</table>
     </div>
    </div>
<form class="create-form" action="PisteServlet?mode=insert" method="post">
	<h1 id="title">Aggiungi Noleggio</h1>
	<div class="form-group">
                	<label for="idnoleggio">ID noleggio </label>
                	<input type="text" id="noleggio" name="ID noleggio" placeholder="Inserisci l'id del noleggio..." required>
                </div>
                <div class="form-group">
                	<label for="username">username </label>
                    <input type="text" id="username" name="username" placeholder="Inserisci username..." required>
                </div>
                <div class="form-group">
                     <label for="idattrezzatura">ID attrezzatura </label>
                     <input type="text" id="attrezzatura" name="ID attrezzatura" placeholder="Inserisci l'id dell'attrezzatura..." required>
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
		<button type="submit" >Aggiungi Noleggio</button>
	</div>
</form>
    <div class="footer">
        <div class="footer-container">
            <img src="images/logo-final.png" alt="Logo Contrader">
            <p> We make innovation happen </p>
        </div>
        <p class="footer-copyright"> �2023 Contrader Srl all rights reserved </p>
    </div>
</body>
</html>