<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.PistaDTO"
    import="it.contrader.dto.UserDTO"
    import="it.contrader.dto.ImpiantoDTO"
    import="java.util.List"
    import="java.util.*"
    %>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Piste user">
<meta name="author" content="Vittorio Valent">

<title>Selezione Pista</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<body>
<%@ include file="../css/navbar.jsp"%>
    <div>
        <%
        List<PistaDTO> list = (List<PistaDTO>) request.getAttribute("piste");
        %>

        <table class="table w-25">
            <thead>
            <tr>
                <th class="text-primary-emphasis" scope="col">ID Pista</th>
                <th class="text-primary-emphasis" scope="col">ID Impianto</th>
                <th class="text-primary-emphasis" scope="col">Difficolta</th>
                <th class="text-primary-emphasis" scope="col">Prezzo</th>
                <th class="text-primary-emphasis" scope="col">Prenotazioni Max</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
            for (PistaDTO p : list) {
            %>
            <tr>
                <th scope="row"><a href="/pista/read?idPista=<%=p.getIdPista()%>">
                    <%=p.getIdPista()%>
                </a></th>
                <td><%=p.getNome()%></td>
                <td><%=p.getDifficolta()%></td>
                <td><%=p.getPrezzo()%></td>
                <td><%=p.getPrenotazioniMax()%></td>
                <th scope="row"><a href="/prenotazione/prenUser?idImpianto=<%=p.getImpianto()%>&idPista=<%=p.getIdPista()%>">Scegli</a></th>
                <% } %>
                </tr>
            </tbody>

            </table>
        </form>
     <%@ include file="../css/footer.jsp"%>
    </body>
</html>