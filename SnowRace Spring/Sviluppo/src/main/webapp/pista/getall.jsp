
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
<meta name="description" content="Homepage for Admin">
<meta name="author" content="Vittorio Valent">

<title>Home</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<body>
<%@ include file="../css/navbar.jsp"%>
    <div>
        <%
        List<PistaDTO> list = (List<PistaDTO>) request.getSession().getAttribute("piste");
        %>

        <div class="row">
            <div class="col-xl-6">
             <table class="table w-100">
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
                <td><a href="/pista/preupdate?idPista=<%=p.getIdPista()%>">Edit</a></td>
                <td><a href="/pista/delete?idPista=<%=p.getIdPista()%>">Delete</a></td>
                <% } %>
                </tr>
            </tbody>

        </table>
    </div>

        <!-- Inserimento della pista -->
         <div class="col-xl-4">
         <table class="table w-100">
            <form class="create-form" action="/pista/insert" method="post">
                <h1 id="title">Aggiungi Pista</h1>

                <!-- Inizio della modifica per l'allineamento -->
                <div class="form-group">
                    <label for="nome">Nome Pista </label>
                    <input type="text" id="nome" name="nomePista" class="form-control" placeholder="Inserisci nome Pista..." required>
                </div>

                <div class="form-group">
                    <label for="difficolta">Difficolta </label>
                    <input type="text" id="difficolta" name="difficoltaPista" class="form-control" placeholder="Inserisci difficoltà Pista..." required>
                </div>

                <div class="form-group">
                    <label for="prezzo">Prezzo </label>
                    <input type="number" id="prezzo" name="prezzoPista" class="form-control" placeholder="Inserisci prezzo Pista..." required>
                </div>

                <div class="form-group">
                    <label for="prenMax">Prenotazioni Massime </label>
                    <input type="number" id="prenMax" name="prenMax" class="form-control" placeholder="Inserisci numero prenotazioni massime..." required>
                </div>
                <!-- Fine della modifica per l'allineamento -->

                <div class="form-group">
                    <% UserDTO user = (UserDTO) request.getSession().getAttribute("user"); %>
                    <% if (user.getUsertype().name().equals("AMMINISTRATORE")) { %>
                        <label for="type">Impianto Pista</label>
                        <select id="type" name="idImpianto" class="form-control">
                            <% List<ImpiantoDTO> listImp = (List<ImpiantoDTO>) request.getAttribute("impianti"); %>
                            <% for (ImpiantoDTO imp : listImp) { %>
                                <option value=<%=imp.getIdImpianto()%>> <%=imp.getNome()%></option>
                            <% } %>
                        </select>
                    <% } %>
                </div>
            <div class="button-wrapper">

                <button type="submit" >Aggiungi Pista</button>
            </div>
        </form>
                </table>
            </div>
         </div>
        </div>
        <%@ include file="../css/footer.jsp"%>
    </body>
</html>