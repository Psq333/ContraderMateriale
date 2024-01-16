<%@ page import="it.contrader.dto.UserDTO"
      import="it.contrader.dto.PistaDTO"
        import="it.contrader.dto.PrenotazioneDTO"
        import="java.util.List"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Homepage for Admin ">
    <meta name="author" content="Vittorio Valent">

    <title>PRENOTAZIONI</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<body>
<div>
    <%
    List<PrenotazioneDTO> list = (List<PrenotazioneDTO>) request.getAttribute("prenotazioni"); //"prenotazioni" è prelevato dalla request dentro set all nel controller
    %>

    <table class="table w-25">
        <thead>
        <tr>
            <th class="text-primary-emphasis" scope="col">ID Prenotazione</th>
            <th class="text-primary-emphasis" scope="col">ID Pista</th>
            <th class="text-primary-emphasis" scope="col">User</th>
            <th class="text-primary-emphasis" scope="col">Data di inizio</th>
            <th class="text-primary-emphasis" scope="col">Data di fine</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
        for (PrenotazioneDTO pr : list) {
        %>
        <tr>
            <th scope="row"><%=pr.getIdPrenotazione()%></th>
            <%PistaDTO p = (PistaDTO) pr.getPista();%>
            <td value="<%=p.getIdPista()%>"> <%=p.getNome()%> </td> <!--=pr.getPista() questo non lo faccio perchè in Prenotazione.java io la pista ce l'ho di tipo Pista qui mi trasporto tutto l'oggetto con tutto quello che ha dentro pista, facendo in questo altro modo facciointanto il cast a DTO perchè mi serve e poi prendo solo l'username-->
            <td><%=((UserDTO)pr.getUser()).getUsername()%></td>
            <td><%=pr.getDataInizio()%></td>
            <td><%=pr.getDataFine()%></td>

            <td><a href="/prenotazione/preupdate?idPrenotazione=<%=pr.getIdPrenotazione()%>">Edit</a></td>
            <td><a href="/prenotazione/delete?idPrenotazione=<%=pr.getIdPrenotazione()%>">Delete</a></td>
            <% } %>
        </tr>
        </tbody>

    </table>
</div>
</body>
</html>