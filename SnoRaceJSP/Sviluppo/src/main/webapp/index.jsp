<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
import="java.lang.*"%>
<html>
<head>
	<link href="css/style.css" rel="stylesheet">

	<title>SnowRace - Login</title>
</head>
<body>
<div class="main">
	<figure>
		<img src="images/logo_contrader.png" alt="logo contrader">
	</figure>

	<form class="login" action="LoginServlet" method="post">
		<h1 id="title">Login</h1>
		<%if (request.getAttribute("errorMessage") != null) { %>
		<div class="form-group">
			<a><%=request.getAttribute("errorMessage")%></a>
		</div>
		<%}%>
		<div class="form-group">
			<label for="user">Username </label>
			<input type="text" id="user" name="username" placeholder="Inserisci username..." required>
		</div>
		<div class="form-group">
			<label for="pass">Password </label>
			<input type="password" id="pass" name="password" placeholder="Inserisci password..." required>
		</div>
		<div class="button-wrapper">
			<button type="submit" value="Login" name="pulsante">Login</button>
			<a class="registration-btn" href="registration.jsp">Registrati</a>
		</div>
	</form>

</div>


</body>
</html>