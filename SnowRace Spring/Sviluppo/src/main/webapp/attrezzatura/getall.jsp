<%@ page import="it.contrader.dto.UserDTO" %>
<%@ page import="java.util.*" %>
<%@ page import="it.contrader.dto.AttrezzaturaDTO" %>
<%@ page import="it.contrader.dto.ImpiantoDTO" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Homepage for Admin">
    <meta name="author" content="Vittorio Valent">

    <title>Home</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<body>
    <div>
        <%
            List<AttrezzaturaDTO> list = (List<AttrezzaturaDTO>) request.getSession().getAttribute("attrezzatura");
        %>

        <table class="table w-25">
            <thead>
                <tr>
                    <th class="text-primary-emphasis" scope="col">ID Attrezzatura</th>
                    <th class="text-primary-emphasis" scope="col">Nome</th>
                    <th class="text-primary-emphasis" scope="col">Descrizione</th>
                    <th class="text-primary-emphasis" scope="col">Prezzo</th>
                    <th class="text-primary-emphasis" scope="col">Nome Impianto</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (AttrezzaturaDTO a : list) {
                %>
                <tr>
                    <th scope="row">
                        <a href="/attrezzatura/read?idAttrezzatura=<%=a.getIdAttrezzatura()%>">
                            <%=a.getIdAttrezzatura()%>
                        </a>
                    </th>
                    <td><%=a.getNome()%></td>
                    <td><%=a.getDescrizione()%></td>
                    <td><%=a.getPrezzo()%></td>
                    <td><%=a.getIdImpianto().getNome()%></td>
                    <td>
                        <a href="/attrezzatura/delete?idAttrezzatura=<%=a.getIdAttrezzatura()%>">DELETE</a>
                        <a href="/attrezzatura/preupdate?idAttrezzatura=<%=a.getIdAttrezzatura()%>">EDIT</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <form class="create-form" action="/attrezzatura/insert" method="post">
            <h1 id="title">Aggiungi Attrezzatura</h1>

            <div class="form-group">
                <label for="nome">nome attrezzatura </label>
                <input type="text" id="nome" name="nomeAttrezzatura" placeholder="Inserisci Nome attrezzatura..." required>
            </div>

            <div class="form-group">
                <label for="descrizione">descrizione </label>
                <input type="text" id="descrizione" name="descrizioneAttrezzatura" placeholder="Inserisci descrizione attrezzatura..." required>
            </div>

            <div class="form-group">
                <label for="prezzo">prezzo attrezzatura </label>
                <input type="number" id="prezzo" name="prezzoAttrezzatura" placeholder="Inserisci Prezzo attrezzatura..." required>
            </div>

            <div class="form-group">
                <%UserDTO user = (UserDTO) request.getSession().getAttribute("user");%>
                <%if(user.getUsertype().name().equals("AMMINISTRATORE")) {%>
                    <label for="type">Impianto</label>
                    <select id="type" name="idImpianto">
                        <% List<ImpiantoDTO> listImp = (List<ImpiantoDTO>) request.getAttribute("impianti"); %>
                        <%
                            for (ImpiantoDTO imp : listImp) {
                        %>
                        <option value=<%=imp.getIdImpianto()%>> <%=imp.getNome()%></option>
                        <%}%>
                    </select>
                <%}%>
            </div>

            <!-- Aggiunto il pulsante "Aggiungi Attrezzatura" -->
            <button type="submit" class="btn btn-primary">Aggiungi Attrezzatura</button>


            <button type="button" onclick="window.location.href='/homeamministratore.jsp'" class="btn btn-secondary">HOME</button>



        </form>
    </div>
</body>
</html>