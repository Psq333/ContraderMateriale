<%@ page import="it.contrader.dto.UserDTO"
        import="it.contrader.dto.AnagraficaDTO"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="User Edit page">
    <meta name="author" content="Vittorio Valent">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <title>Edit Anagraf</title>

</head>
<body>
<%@ include file="../css/header.jsp" %>
<br>
<div class="main">

    <%AnagraficaDTO a = (AnagraficaDTO) request.getAttribute("anagraficaDTO");%>


    <form id="floatleft" action="/anagrafica/update" method="post">
        <h1 id="title">Modifica i dati di <%=((UserDTO)a.getUser()).getUsername()%></h1>
        <div class="row">
            <div class="col-25">
                <label for="nome">Nome </label>
            </div>
            <div class="col-75">
                <input type="text" id="nome" name="nome" placeholder="<%=a.getNome()%>" required>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="cognome">Cognome </label>
            </div>
            <div class="col-75">
                <input type="text" id="cognome" name="cognome" placeholder="<%=a.getCognome()%>" required>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="indirizzo">Indirizzo </label>
            </div>
            <div class="col-75">
                <input type="text" id="indirizzo" name="indirizzo" placeholder="<%=a.getIndirizzo()%>" required>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="data">Data di nascita </label>
            </div>
            <div class="col-75">
                <input type="date" id="data" name="data" placeholder="<%=a.getDataNascita()%>" required>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="luogo">Luogo di nascita </label>
            </div>
            <div class="col-75">
                <input type="text" id="luogo" name="luogo" placeholder="<%=a.getLuogoNascita()%>" required>
            </div>
        </div>
        <div>
            <input type="hidden" name="user" value="<%=((UserDTO)a.getUser()).getId()%>">
            <input type="hidden" name="id" value="<%=a.getId()%>">
            <% if (((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().equals("AMMINISTRATORE")) {%>
            <input type="hidden" name="source" value=<%=request.getAttribute("source")%>
            <%}%>
        </div>
        <button type="submit" >Edit</button>
    </form>


</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>