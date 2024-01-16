<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.NoleggioDTO"
    import="it.contrader.dto.UserDTO"
    import="it.contrader.dto.AttrezzaturaDTO"
    import="java.util.List"
    import="java.util.*"
    %>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Noleggio user">
<meta name="author" content="Vittorio Valent">

<title>Selezione Noleggio</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<body>

    <div>
        <%
        List<NoleggioDTO> list = (List<NoleggioDTO>) request.getSession().getAttribute("noleggios");
        %>

        <table class="table w-25">
            <thead>
            <tr>
              <th class="text-primary-emphasis" scope="col">ID noleggio</th>
              <th class="text-primary-emphasis" scope="col">ID attrezzatura</th>
              <th class="text-primary-emphasis" scope="col">username</th>
              <th class="text-primary-emphasis" scope="col">data inizio</th>
              <th class="text-primary-emphasis" scope="col">data fine </th>
              <th></th>

            </tr>
            </thead>
            <tbody>
            <%
            for (NoleggioDTO p : list) {
            if (p.getUser().equals(((UserDTO)request.getSession().getAttribute("user")).getUsername())){
            %>
            <tr>
                <th scope="row"><a href="/noleggio/read?idnoleggio=<%=p.getIdnoleggio()%>">
                    <%=p.getIdnoleggio()%>
                </a></th>

               <td><a href="/noleggio/read?idattrezzatura=<%=p.getIdattrezzatura().getIdAttrezzatura()%>">
                   <%=p.getIdattrezzatura().getIdAttrezzatura()%>
               </a>
               </td>
               <td><%=p.getUser().getUsername()%></td>
                <td><%=p.getStartDate()%></td>
                <td><%=p.getEndDate()%></td>
                <th scope="row"><a href="">Scelga</a></th>
                <% }} %>
                </tr>
            </tbody>

            </table>
        </form>

    </body>
</html>
