<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.AttrezzaturaDTO"
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
<meta name="description" content="Attrezzatura user">
<meta name="author" content="Vittorio Valent">

<title>Selezione Attrezzatura</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<body>
<%@ include file="../css/navbar.jsp"%>
    <div>
        <%
        List<AttrezzaturaDTO> list = (List<AttrezzaturaDTO>) request.getSession().getAttribute("attrezzatura");
        %>

        <table class="table w-25">
            <thead>
            <tr>
                <th class="text-primary-emphasis" scope="col">ID Attrezzatura</th>
                <th class="text-primary-emphasis" scope="col">Nome </th>
                <th class="text-primary-emphasis" scope="col">Descrizione</th>
                <th class="text-primary-emphasis" scope="col">Prezzo</th>
                <th class="text-primary-emphasis" scope="col">ID Impianto Max</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
            for (AttrezzaturaDTO a : list) {
            %>
            <tr>
                <th scope="row"><a href="/attrezzatura/read?idAttrezzatura=<%=a.getIdAttrezzatura()%>">
                    <%=a.getIdAttrezzatura()%>
                </a></th>
                <td><%=a.getNome()%></td>
                <td><%=a.getDescrizione()%></td>
                <td><%=a.getPrezzo()%></td>

                <th scope="row"><a href="/noleggio/prenUser?idAttrezzatura=<%=a.getIdAttrezzatura()%>">Scegli</a></th>
                <% } %>
                </tr>
            </tbody>

            </table>
        </form>
     <%@ include file="../css/footer.jsp"%>
    </body>
</html>