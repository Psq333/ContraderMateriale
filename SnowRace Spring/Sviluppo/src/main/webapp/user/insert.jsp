<%@ page import="it.contrader.dto.UserDTO" import="java.util.*"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="User Insert">
    <meta name="author" content="Vittorio Valent">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <title>Registrazione</title>

</head>
<body>
<%@ include file="../css/header.jsp"%>


<div class="main">
    <form action="/user/insert" method="post">
        <%if (request.getSession().getAttribute("errorMessage") != null) { %>
        <div class="form-group">
            <a><%=request.getSession().getAttribute("errorMessage")%></a>
        </div>
        <% request.getSession().setAttribute("errorMessage", null);
        }%>
        <div class="row">
            <div class="col-25">
                <label for="user">Username</label>
            </div>
            <div class="col-75">
                <input type="text" id="user" name="username"
                       placeholder="inserisci username">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="pass">Password</label>
            </div>
            <div class="col-75">
                <input type="text" id="pass" name="password"
                       placeholder="inserisci password">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="type">Usertype</label>
            </div>
            <div class="col-75">
                <select id="type" name="usertype">
                    <option value="ADMIN">ADMIN</option>
                    <option value="USER">USER</option>
                    <option value="AMMINISTRATORE">AMMINISTRATORE</option>
                </select>
            </div>
        </div>
        <button type="submit">Insert</button>
    </form>
</div>
