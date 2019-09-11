<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="com.sql.jsp.User"%>

<html>
<head>
<title>My Profile</title>
<script type="text/javascript">		  	
  </script>
</head>
<h1>WORK IN PROGRESS...<br/>
	<% 
		out.print("Welcome! <br/>  " + User.uid);
   	%>
</h1>
	<br/><h1>designation: professor</h1> <br/> 
	<a href='form.jsp'> Go to form </a>
<body>


</body>
</html>