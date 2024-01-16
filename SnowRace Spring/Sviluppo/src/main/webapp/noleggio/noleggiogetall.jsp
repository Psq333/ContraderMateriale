<%@ page import="it.contrader.dto.UserDTO" import="java.util.*" import="it.contrader.dto.NoleggioDTO" import="it.contrader.dto.AttrezzaturaDTO"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Homepage for Admin">
    <meta name="author" content="Vittorio Valent">
    <title>Home</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">

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
                %>
                <tr>
                    <th scope="row">
                        <a href="/noleggio/read?idnoleggio=<%=p.getIdnoleggio()%>">
                            <%=p.getIdnoleggio()%>
                        </a>
                        <td><a href="/noleggio/read?idattrezzatura=<%=p.getIdattrezzatura().getIdAttrezzatura()%>">
                                    <%=p.getIdattrezzatura().getIdAttrezzatura()%>
                         </a>
                    </td>
                    <td><%=p.getUser().getUsername()%></td>
                    <td><%=p.getStartDate()%></td>
                    <td><%=p.getEndDate()%></td>
                    <th scope="row">
                        <a href="/noleggio/preupdate?idnoleggio=<%=p.getIdnoleggio()%>">EDIT</a>
                    </th>
                    <th scope="row">
                         <a href="/noleggio/delete?idnoleggio=<%=p.getIdnoleggio()%>">DELETE</a>
                    </th>
                </tr>
                <% } %>
            </tbody>
        </table>


       <form class="create-form" action="/noleggio/insert" method="post">
           <h1 id="title">Aggiungi Noleggio</h1>

           <div class="form-group">
               <label for="idattrezzatura">Attrezzatura </label>
               <select id="idattrezzatura" name="idattrezzatura" required>
                   <!-- Ciclo per ottenere le attrezzature e mostrarle nel menù a tendina -->
                   <c:forEach var="attrezzatura" items="${attrezzature}">
                       <option value="${attrezzatura.idAttrezzatura}">${attrezzatura.nome}</option>
                   </c:forEach>
               </select>
           </div>

           <div class="form-group">
               <label for="user">username </label>
               <select id="user" name="user">
               <%for (UserDTO user: (List<UserDTO>)request.getAttribute("users")) {%>
                   <option value="<%=user.getId()%>"><%=user.getUsername()%></option>
               <%}%>
           </select>
           </div>

           <div class="form-group">
               <label for="date">data inizio </label>
               <input type="date" id="date" name="startDate" placeholder="Inserisci data inizio noleggio..." required>
           </div>

           <div class="form-group">
               <label for="date">data fine </label>
               <input type="date" id="date" name="endDate" placeholder="Inserisci data fine noleggio..." required>
           </div>


           <button type="submit" class="btn btn-primary">Aggiungi Noleggio</button>
       </form>

</body>
</html>