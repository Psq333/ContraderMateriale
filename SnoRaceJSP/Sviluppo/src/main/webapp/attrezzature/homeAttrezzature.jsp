<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.AttrezzatureDTO"
	import="it.contrader.dto.ImpiantoDTO"%>

<html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Attrezzature </title>
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
     <h1> Attrezzature </h1>
     <div class="container d-flex">
     	<%
     		List<AttrezzatureDTO> list = (List<AttrezzatureDTO>) request.getAttribute("attrezzature");
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
     			<td><a href=AttrezzatureServlet?mode=prepare_update&id=<%=u.getIdAttrezzature()%>>Edit</a></td>
     			<td><a href=AttrezzatureServlet?mode=delete&id=<%=u.getIdAttrezzature()%>>Delete</a></td>
     		</tr>
     		</tbody>
     		<%
     			}
     		%>
     	</table>
     </div>
    </div>


<form class="create-form" action="AttrezzatureServlet?mode=insert" method="post">
	<h1 id="title">Aggiungi Attrezzatura</h1>




	<div class="form-group">
		<label for="prezzo">Prezzo </label>
		<input type="double" id="prezzo" name="prezzo" placeholder="Inserisci il prezzo" required>
	</div>

	<div class="form-group">
		<label for="descrizione">Descrizione </label>
		<input type="text" id="descrizione" name="descrizione" placeholder="Inserisci la descrizione" required>
	</div>

	<div class="form-group">
		<label for="nome">Nome </label>
		<input type="text" id="nome" name="nome" placeholder="Inserisci il nome dell'attrezzatura" required>
	</div>
	<div class="form-group">
    		 <label for="idimpianto">ID Impianto </label>
            		<select id="type" name="idimpianto">
                            <% List<ImpiantoDTO> lista = (List<ImpiantoDTO>) request.getAttribute("impiantis"); %>
                            <%
                            for (ImpiantoDTO impianto : lista) {
                            %>
                             <option> <%=impianto.getIdImpianto()%></option>
                             <%}%>
                              </select>
    	</div>
	<div class="button-wrapper">

		<button type="submit" >Aggiungi Attrezzatura</button>
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
