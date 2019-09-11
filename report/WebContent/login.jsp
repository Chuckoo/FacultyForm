<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@page import="com.sql.jsp.User"%>

<!DOCTYPE html>
<html>
<head>
<title>Faculty Form Page</title>
<style>
.weight {
	font-weight: bold;
	font-size: 17px;
}
</style>
<head>
<body>
	<div class="container">
		<div>
			<h1>Login Page</h1>
		</div>
		<br>
		<form action="#" method="post">
			<div class="weight">
				<label for="usr_name"><b>Email ID:</b></label><br> <input
					type="text" id="name" name="user_name" class="form-control"
					placeholder="Email ID">
			</div>
			<div class="weight">
				<label for="pwd">Password:</label><br> <input type="password"
					id="pwd" name="password" class="form-control"
					placeholder="password">
			</div>
			<br>
			<div class="button">
				<button type="submit">Login</button>
			</div>
		</form>
	<%
		if(request.getParameter("user_name") != null) {
   			
			out.println("Logging in .....<br/><br/>");
   			String pwd = User.getPwd(request.getParameter("user_name"));

   	// if password matches	
   			if(pwd.equals(request.getParameter("password").toString())) {
   				
   				User.uid=request.getParameter("user_name");//temporary variable for remembering user info 
														   //will be removed once a proper login scheme is done
   				
   				switch(User.getDes(request.getParameter("user_name")))
   	   			{
   	   				case "professor":
   	   					response.sendRedirect("MyAccount.jsp");
   	   					break;
   	   				
   	   				case "HOD":
	   					response.sendRedirect("hod.jsp");
	   					break;
   	   				
   	   				case "Principal":
   	   					out.print("PRINCIPAL");
   	   					response.sendRedirect("principal.jsp");
   	   					break;
   	   					
   	   				case "admin":
	   					response.sendRedirect("admin.jsp");
	   					break;
   	   			}
   			}
   	
   			else {
   				out.println("Invalid Email or Password, <br/>Please try again.");
   			}
		}
    %>
		
	</div>
</body>
</html>


