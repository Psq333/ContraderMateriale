<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.List"
import="it.contrader.dto.PrenotazioniDTO"
import="it.contrader.enums.Usertype"
import="java.lang.String"
import="java.sql.Date"
import="java.time.LocalDate"%>
<html>
<!-- prenotazionimanager.jsp= (onclick) gestione prenotazioni post log AMMINISTRATORE-->
<head>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <title>Ricerca Prenotazioni</title>

</head>
<body>
<nav class="navbar">
    <a class="flex-logo text-decoration-none" href=home<%=((Usertype)request.getSession().getAttribute("usertype")).name().toLowerCase()%>.jsp>

        <img src="images/mini-logo.png" alt="mini logo">
        <span>
                    Contrader Formazione
            </span>
    </a>
    <ul>
        <li>
            <a href="home<%=((Usertype)request.getSession().getAttribute("usertype")).name().toLowerCase()%>.jsp" class="nav-link">Home</a>
        </li>
        <li>
            <a href="LogoutServlet" class="nav-link">Logout</a>
        </li>
    </ul>

</nav>
<div class="main container d-flex">
    <%     List<PrenotazioniDTO> list = (List<PrenotazioniDTO>) request.getAttribute("prenotazioni");    %>

    <table class="table w-25">
        <thead>
        <tr>
            <th class="text-primary-emphasis" scope="col">ID</th>
            <th class="text-primary-emphasis" scope="col">Id_pista</th>
            <th class="text-primary-emphasis" scope="col">Username</th>
            <th class="text-primary-emphasis" scope="col">Data_inizio</th>
            <th class="text-primary-emphasis" scope="col">Data_fine</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
        for (PrenotazioniDTO u : list) {
        %>
        <tr>
            <th scope="row">
                <a href=PrenotazioniServlet?mode=read&id=<%=u.getId_prenotazione()%>><%=u.getId_prenotazione()%></a>
            </th>
            <td><%=u.getId_pista()%></td>
            <td><%=u.getUsername()%></td>
            <td><%=u.getData_inizio()%></td>
            <td><%=u.getData_fine()%></td>
            <%
                LocalDate providedDate = LocalDate.parse(u.getData_inizio());
                LocalDate today = LocalDate.now();
                if (providedDate.isAfter(today)) { %>
            <td><a href=PrenotazioniServlet?mode=prepare_update&id=<%=u.getId_prenotazione()%>>Edit</a></td>
            <td><a href=PrenotazioniServlet?mode=delete&id=<%=u.getId_prenotazione()%>>Delete</a></td>
            <% } else { %>
            <td></td>
            <td></td>
            <% } %>
        </tr>
        </tbody>
        <%
        }
        %>
    </table>

    <form class="create-form" action="PrenotazioniServlet?mode=search" method="get">
        <h1 id="title">Cerca prenotazione</h1>
        <p>Ricerca prenotazione per:</p>


        <label class="radio-label" for="data">
            Data
            <input type="radio" id="data" name="what" value="data" required><br>
            <span class="custom-radio"></span>
        </label>
        <label class="radio-label" for="pista">
            Pista
            <input type="radio" id="pista" name="what" value="pista" required><br>
            <span class="custom-radio"></span>
        </label>


        <input type="hidden" name="mode" value="search">
        <div id="datepicker-container">
            <input type="text" id="ricerca" name="which" placeholder="" required>
        </div>

        <%if (((Usertype)request.getSession().getAttribute("usertype")).name().equals("AMMINISTRATORE")) {%>
        <input type="text" id="username" name="username" placeholder="username da ricercare" required>
        <%}%>

        <div class="button-wrapper">
            <button type="submit">Cerca</button>
        </div>

        <script>
            var ricercaInput = document.getElementById('ricerca');
            var dataRadio = document.getElementById('data');
            var pistaRadio = document.getElementById('pista');
            var datepickerContainer = document.getElementById('datepicker-container');

            dataRadio.addEventListener('change', function() {
                ricercaInput.style.display = 'none'; // Nasconde la casella di testo
                // Crea e visualizza l'input di tipo "date" al posto della casella di testo
                var dateInput = document.createElement('input');
                dateInput.setAttribute('type', 'date');
                dateInput.setAttribute('id', 'datePicker');
                dateInput.setAttribute('name', 'which');
                dateInput.setAttribute('placeholder', 'Seleziona una data');
                dateInput.setAttribute('required', 'required');

                dateInput.addEventListener('change', function() {
                    ricercaInput.value = this.value; // Imposta il valore dell'input di testo con la data selezionata
                });
                datepickerContainer.appendChild(dateInput);
            });

            pistaRadio.addEventListener('change', function() {
                // Rimuove l'input di tipo "date" e mostra di nuovo la casella di testo
                var datePicker = document.getElementById('datePicker');
                if (datePicker) {
                    datepickerContainer.removeChild(datePicker);
                }
                ricercaInput.style.display = 'block'; // Mostra la casella di testo
                ricercaInput.setAttribute('placeholder', 'Inserisci ID pista da cercare');
            });
        </script>
    </form>

</div>

<div class="footer">
    <div class="footer-container">
        <img src="images/logo-final.png" alt="Logo Contrader">
        <p> We make innovation happen </p>
    </div>
    <p class="footer-copyright"> �2023 Contrader Srl all rights reserved </p>
</div>

</body>
</html>