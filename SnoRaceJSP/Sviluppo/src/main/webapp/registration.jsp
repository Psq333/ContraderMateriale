<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.List"
         import="it.contrader.dto.UserDTO"%>

<html>
<head>
  <meta charset="ISO-8859-1">
  <link href="css/style.css" rel="stylesheet">
  <title>Registration</title>
</head>
<body>


<div class="main">

  <figure>

    <img src="images/logo_contrader.png" alt="logo contrader">
  </figure>
  <% if (request.getAttribute("duplicate") != null && request.getAttribute("duplicate").equals("true")){ %>
  <label>Username esistente. Inseriscine un altro.</label>
  <%}%>
  <form class="registration-form" action="UserServlet?mode=register" method="post">
    <h1 id="title">Registrazione</h1>
    <div class="form-group">
      <label for="user">Username </label>
      <input type="text" id="user" name="username" placeholder="Inserisci username..." required>
    </div>
    <div class="form-group">
      <label for="pass">Password </label>
      <input type="password" id="pass" name="password" placeholder="Inserisci password..." required>
    </div>
    <div class="button-wrapper">
      <label for="type">Usertype</label>
      <select id="type" name="usertype">
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
        <option value="AMMINISTRATORE">AMMINISTRATORE</option>
      </select>

      <button type="submit" >Crea Account</button>
    </div>
  </form>

</div>

<%--<%@ include file="../css/footer.jsp" %>--%>
</body>
</html>