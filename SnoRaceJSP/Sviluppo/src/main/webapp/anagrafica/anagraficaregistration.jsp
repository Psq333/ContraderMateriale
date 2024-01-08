<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.List"
import="it.contrader.dto.AnagraficaDTO"%>

<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="css/style.css" rel="stylesheet">
    <title>Registanag</title>
</head>
<body>


<div class="main">

    <figure>

        <img src="images/logo_contrader.png" alt="logo contrader">
    </figure>

    <form class="registration-form" action="AnagraficaServlet?mode=Register" method="post">
        <h1 id="title">Registrazione anagrafica</h1>
        <div class="form-group">
            <label for="nome">Nome </label>
            <input type="text" id="nome" name="nome" placeholder="Inserisci nome..." required>
        </div>
        <div class="form-group">
            <label for="cognome">Cognome </label>
            <input type="text" id="cognome" name="cognome" placeholder="Inserisci cognome..." required>
        </div>
        <div class="form-group">
            <label for="cognome">Indirizzo </label>
            <input type="text" id="indirizzo" name="indirizzo" placeholder="Inserisci il tuo indizzo..." required>
        </div>
        <div class="form-group">
            <label for="luogo">Luogo di nascita </label>
            <input type="text" id="luogo" name="luogo" placeholder="Inserisci luogo di nascita..." required>
        </div>
        <div class="form-group">
            <label for="data">Data di nascita </label>
            <input type="date" id="data" name="data" placeholder="Inserisci data di nascita..." required>
        </div>
        <input type="hidden" id="username" name="username" value=<%=request.getParameter("username")%>>
        <input type="hidden" id="usertype" name="usertype" value=<%=request.getParameter("usertype")%>>

        <div class="button-wrapper">
            <button type="submit" >Registra i dati</button>
        </div>
    </form>

</div>

<%--<%@ include file="../css/footer.jsp" %>--%>
</body>
</html>