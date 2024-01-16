<%@ page import="it.contrader.dto.AnagraficaDTO"
        import="it.contrader.dto.UserDTO"
        import="java.util.*"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Anagrafica Insert">
    <meta name="author" content="Vittorio Valent">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <title>Registrazione</title>

</head>
<body>
<%@ include file="../css/header.jsp"%>


<div class="main">
    <form action="/anagrafica/insert" method="post">

        <h1 id="title">Aggiungi i dati di <%=((UserDTO)request.getSession().getAttribute("user")).getUsername()%></h1>
        <div class="row">
            <div class="col-25">
                <label for="nome">Nome </label>
            </div>
            <div class="col-75">
                <input type="text" id="nome" name="nome" placeholder="Inserisci nome..." required>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="cognome">Cognome </label>
            </div>
            <div class="col-75">
                <input type="text" id="cognome" name="cognome" placeholder="Inserisci cognome..." required>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="indirizzo">Indirizzo </label>
            </div>
            <div class="col-75">
                <input type="text" id="indirizzo" name="indirizzo" placeholder="Inserisci indirizzo..." required>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="data">Data di nascita </label>
            </div>
            <div class="col-75">
                <input type="date" id="data" name="data" placeholder="Inserisci la data di nascita..." required>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="luogo">Luogo di nascita </label>
            </div>
            <div class="col-75">
                <input type="text" id="luogo" name="luogo" placeholder="Inserisci il luogo di nascita..." required>
            </div>
        </div>
        <input type="hidden" name="user" value=<%=((UserDTO)request.getSession().getAttribute("user")).getId()%>>
        <button type="submit">Insert</button>
    </form>
</div>
