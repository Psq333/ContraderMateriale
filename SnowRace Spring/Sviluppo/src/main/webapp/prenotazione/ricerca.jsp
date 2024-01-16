<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.util.List"
import="it.contrader.dto.PrenotazioneDTO"
import="it.contrader.dto.PistaDTO"
import="it.contrader.dto.UserDTO"
import="java.lang.String"
import="java.sql.Date"
import="java.time.LocalDate"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Ricerca prenotazioni">
    <meta name="author" content="Vittorio Valent">

    <title>Ricerca prenotazioni</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<div class="navbar">
    <a class="flex-logo text-decoration-none" href=/home<%=((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase()%>.jsp>

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
            <a href="LogoutServlet" class="nav-link">Logout</a>
        </li>
    </ul>
</div>
<br>
<div class="main container d-flex">
    <%     List<PrenotazioneDTO> list = (List<PrenotazioneDTO>) request.getAttribute("prenotazioni");    %>

    <table class="table w-25">
        <thead>
        <tr>
            <th class="text-primary-emphasis" scope="col">ID</th>
            <th class="text-primary-emphasis" scope="col">Pista</th>
            <th class="text-primary-emphasis" scope="col">Username</th>
            <th class="text-primary-emphasis" scope="col">Data di Inizio</th>
            <th class="text-primary-emphasis" scope="col">Data di Fine</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
        for (PrenotazioneDTO pr : list) {
        %>
        <tr>
            <th scope="row">
                <a ><%=pr.getIdPrenotazione()%></a>
            </th>
            <td><%=((PistaDTO)pr.getPista()).getNome()%></td>
            <td><%=((UserDTO)pr.getUser()).getUsername()%></td>
            <td><%=pr.getDataInizio()%></td>
            <td><%=pr.getDataFine()%></td>
            <%
                LocalDate providedDate = pr.getDataInizio().toLocalDate();
                LocalDate today = LocalDate.now();
                if (providedDate.isAfter(today)) { %>
            <td><a href=/prenotazione/preupdate?source=ricerca&idPrenotazione=<%=pr.getIdPrenotazione()%>>Edit</a></td>
            <td><a href=/prenotazione/delete?source=ricerca&idPrenotazione=<%=pr.getIdPrenotazione()%>>Delete</a></td>
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
    </div>

    <form id="floatright" class="floatright" action="/prenotazioni/search" method="get">
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

        <div id="datepicker-container" >
            <input type="text" id="ricerca" name="which" placeholder="" required>
        </div>
        <%if (((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().equals("AMMINISTRATORE")) {%>
        <select id="user" name="who">
            <%for (UserDTO user: (List<UserDTO>)request.getAttribute("users")) {%>
                <option value="<%= user.getUsername() %>"><%= user.getUsername() %></option>
            <%}%>
        </select>
        <%} else {%>
        <input type="hidden" name="who" value="<%=((UserDTO)request.getSession().getAttribute("user")).getUsername()%>" >
        <%}%>

        <div class="button-wrapper">
            <button type="submit">Cerca</button>
        </div>

        <script>
            var ricercaInput = document.getElementById('ricerca');
            var dataRadio = document.getElementById('data');
            var pistaRadio = document.getElementById('pista');
            var datepickerContainer = document.getElementById('datepicker-container');
            var form = document.querySelector('.floatright');

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
                form.setAttribute('action', '/prenotazione/searchByDate');
            });

            pistaRadio.addEventListener('change', function() {
                // Rimuove l'input di tipo "date" e mostra di nuovo la casella di testo
                var datePicker = document.getElementById('datePicker');
                if (datePicker) {
                    datepickerContainer.removeChild(datePicker);
                }
                ricercaInput.style.display = 'block'; // Mostra la casella di testo
                ricercaInput.setAttribute('placeholder', 'Inserisci nome della pista da cercare');
                form.setAttribute('action', '/prenotazione/searchByPista');
            });
        </script>
    </form>



<div class="footer">
    <div class="footer-container">
        <img src="images/logo-final.png" alt="Logo Contrader">
        <p> We make innovation happen </p>
    </div>
    <p class="footer-copyright"> �2023 Contrader Srl all rights reserved </p>
</div>

</body>
</html>