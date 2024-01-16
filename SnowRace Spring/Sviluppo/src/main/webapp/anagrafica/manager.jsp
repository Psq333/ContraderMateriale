<%@ page import="it.contrader.dto.UserDTO"
        import="it.contrader.dto.AnagraficaDTO"
        import="java.util.*"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="User Management">
    <meta name="author" content="Vittorio Valent">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <title>Anagrafica Manager</title>

</head>
<body>
<%@ include file="../css/header.jsp"%>

<div class="navbar">

    <a class="flex-logo text-decoration-none" href="/home<%= ((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase() %>.jsp">
    <img src="images/mini-logo.png" alt="mini logo">
    <span>
					Contrader Formazione
			</span>
    </a>
    <ul>
        <li>

            <a href="/home<%= ((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase() %>.jsp" class="nav-link">Home</a>
        </li>
        <li>
            <a href="/user/read?id=<%=((UserDTO)request.getSession().getAttribute("user")).getId()%>" class="nav-link">
            Account
            </a>
        </li>
        <li>
            <a href="/user/logout" id="logout">Logout</a>
        </li>
    </ul>

</div>
<div class="main">
    <%
    List<AnagraficaDTO> list = (List<AnagraficaDTO>) request.getAttribute("anagraficaDTO");
    %>

    <br>

    <table>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Indirizzo</th>
            <th>Luogo di nascita</th>
            <th>Data di nascita</th>
            <th>Username</th>
            <th></th>
            <th></th>
        </tr>
        <%
        for (AnagraficaDTO a : list) {
            if(!((UserDTO)a.getUser()).equals((UserDTO)request.getSession().getAttribute("user"))){
        %>
        <tr>
            <td><%=a.getNome()%></td>
            <td><%=a.getCognome()%></td>
            <td><%=a.getIndirizzo()%></td>
            <td><%=a.getLuogoNascita()%></td>
            <td><%=a.getDataNascita()%></td>
            <td><a href="/user/read?id=<%=((UserDTO)a.getUser()).getId()%>"><%=((UserDTO)a.getUser()).getUsername()%></a></td>
            <td><a href="/anagrafica/preupdate?id=<%=a.getId()%>">Edit</a></td>
            <td><a href="/anagrafica/delete?id=<%=a.getId()%>">Delete</a></td>

        </tr>
        <%
        }}
        %>
    </table>



    <form id="floatright" action="/anagrafica/insert" method="post">

        <h1 id="title">Aggiungi i dati dell'utente</h1>
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
        <div class="row">
            <div class="col-25">
                <label for="user">Username</label>
            </div>
            <div class="col-75">
                <select id="user" name="user">
                    <%
                    List<UserDTO> users = (List<UserDTO>)request.getAttribute("users");
                    for (UserDTO u : users) {
                        boolean noDetails = true;
                        for (AnagraficaDTO a : list) {
                            if (a.getUser() != null){
                                if (((UserDTO)a.getUser()).getId() == u.getId()) {
                                    noDetails = false;
                                    break;
                                }
                            }
                        }
                        if (noDetails) { %>
                            <option value="<%= u.getId() %>"><%= u.getUsername() %></option>
                        <%}
                    }%>
                </select>
            </div>
        </div>
        <div class="button-wrapper">
            <button type="submit">Insert</button>
        </div>
    </form>

    <script>
        // Controlla se il select ha opzioni, altrimenti nasconde la form
        document.addEventListener("DOMContentLoaded", function () {
            var select = document.getElementById("user");
            if (!select.options.length) {
                var form = document.getElementById("floatright");
                form.style.display = "none";
            }
        });
    </script>

</div>
<br>
<%@ include file="../css/footer.jsp"%>
</body>
</html>