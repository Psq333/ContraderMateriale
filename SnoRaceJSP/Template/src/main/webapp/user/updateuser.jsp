<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="css/style.css" rel="stylesheet">
    <title>Edit User</title>
</head>
<body>
<nav class="navbar">

    <a class="flex-logo text-decoration-none" href="homeadmin.jsp">
        <img src="images/mini-logo.png" alt="mini logo">
        <span>
					Contrader Formazione
			</span>
    </a>
    <ul>
        <li>
            <a href="homeadmin.jsp" class="nav-link">Home</a>
        </li>
        <li>
            <a href="UserServlet?mode=getall" class="nav-link">
                Utenti
            </a>
        </li>
        <li>
            <a href="LogoutServlet" class="nav-link">Logout</a>
        </li>
    </ul>

</nav>

    <div class="main container">

        <%UserDTO u = (UserDTO) request.getAttribute("userToUpdate");%>

        <form class="create-form" action="UserServlet" method="post">
            <input type="hidden" name="mode" value="update">
            <input type="hidden" name="id" value="<%=u.getId()%>">
            <h1 id="title">Modifica utente</h1>
            <div class="form-group">
                <label for="user">Username </label>
                <input type="text" id="user" name="username" value=<%=u.getUsername()%> required>
            </div>
            <div class="form-group">
                <label for="pass">Password </label>
                <input type="password" id="pass" name="password" value=<%=u.getPassword()%> required>
            </div>
            <div class="form-group">

                <label for="type">Usertype</label>
                <select id="type" name="usertype">
                    <option value="ADMIN" <%if(u.getUsertype().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
                    <option value="USER" <%if(u.getUsertype().equals("USER")) {%>selected<%}%>>USER</option>
                </select>

            </div>
            <div class="button-wrapper">

                <button type="submit" >Modifica Account</button>
            </div>
        </form>

    </div>

    <div class="footer">
        <div class="footer-container">
            <img src="images/logo-final.png" alt="Logo Contrader">
            <p> We make innovation happen </p>
        </div>
        <p class="footer-copyright"> ©2023 Contrader Srl all rights reserved </p>
    </div>
</body>
</html>