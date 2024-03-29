<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="User Edit page">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Edit User</title>

</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="/home<%= ((UserDTO)request.getSession().getAttribute("user")).getUsertype().name().toLowerCase() %>.jsp">Home</a>
  <a href="/user/logout" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%UserDTO u = (UserDTO) request.getSession().getAttribute("dto");%>


<form id="floatleft" action="/user/update" method="post">
  <div class="row">
    <div class="col-25">
      <label for="user">Username</label>
    </div>
    <div class="col-75">
      <input type="text" id="user" name="username" value=<%=u.getUsername()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="pass">Password</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="pass" name="password" value=<%=u.getPassword()%>> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="type">Usertype</label>
    </div>
   		 <div class="col-75">
 			<select id="type" name="usertype" <% if (!u.getUsertype().name().equals("AMMINISTRATORE")) { %> disabled <% } %>>
  				<option value="ADMIN" <%if(u.getUsertype().toString().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
  				<option value="USER" <%if(u.getUsertype().toString().equals("USER")) {%>selected<%}%>>USER</option>
                <option value="AMMINISTRATORE" <%if(u.getUsertype().toString().equals("AMMINISTRATORE")) {%>selected<%}%>>AMMINISTRATORE</option>
			</select>
    	</div>
    	<input type="hidden" name="id" value=<%=u.getId()%>>
      <% if (!u.getUsertype().name().equals("AMMINISTRATORE")) {%>
      <input type="hidden" name="usertype" value=<%=u.getUsertype()%>>
      <%} else {%>
      <input type="hidden" name="source" value=<%=request.getAttribute("source")%>
      <%}%>
  </div>
      <button type="submit" >Edit</button>
</form>

	
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>