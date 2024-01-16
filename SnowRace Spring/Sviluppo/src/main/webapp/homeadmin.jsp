<%@ page import="it.contrader.model.User.Usertype"
		 import="it.contrader.dto.UserDTO"
		 import="java.lang.String"
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Homepage for Admin ">
<meta name="author" content="Vittorio Valent">

<title>Home</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/vittoriostyle.css" rel="stylesheet">
</head>

<body>
	<%@include file="css/header.jsp"%>


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
		<h1>Welcome <%=((UserDTO)request.getSession().getAttribute("user")).getUsername()%></h1>

		<form class="create-form">

			<h1> Menu Admin</h1>
			<div class="button-wrapper">
				<button type="button" onclick="window.location.href='/impianto/getall'">Gestione Impianti</button>
			</div>
			<div class="button-wrapper">
				<button type="button" onclick="window.location.href='/pista/getalladmin'">Gestione Piste</button>
			</div>


		</form>




	</div>


	<%@ include file="css/footer.jsp"%>

</body>
</html>