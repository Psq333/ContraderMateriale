<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.AttrezzatureDTO"
         import="it.contrader.dto.ImpiantoDTO"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="css/style.css" rel="stylesheet">
    <title>Edit Attrezzature</title>
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

        <%AttrezzatureDTO u = (AttrezzatureDTO) request.getAttribute("AttrezzatureToUpdate");%>

        <form class="create-form" action="AttrezzatureServlet" method="post">
            <input type="hidden" name="mode" value="update">
            <input type="hidden" name="id" value="<%=u.getIdAttrezzature()%>">
            <h1 id="title">Modifica Attrezzatura</h1>

            <div class="form-group">
                <label for="idattrezzature">ID Attrezzature </label>
                <input type="text" id="idattrezzature" name="idattrezzature" value=<%=u.getIdAttrezzature()%> readonly>
            </div>

            <div class="form-group">
                <label for="prezzo">Prezzo </label>
                <input type="double" id="prezzo" name="prezzo" value=<%=u.getPrezzo()%> required>
            </div>

            <div class="form-group">
                <label for="descrizione">Descrizione </label>
                <input type="text" id="descrizione" name="descrizione" value=<%=u.getDescrizione()%> required>
            </div>

            <div class="form-group">
                <label for="nome">Nome </label>
                <input type="text" id="nome" name="nome" value=<%=u.getPrezzo()%> required>
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

                <button type="submit" >Modifica Attrezzature</button>
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