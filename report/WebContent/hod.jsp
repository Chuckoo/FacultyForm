<%@page import="com.sql.jsp.pdf"%>
<%@page import="com.sql.jsp.User"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>review</title>
</head>
<body>
	
	<h1>WORK IN PROGRESS...<br/>
	<% 
		out.print("Welcome! <br/>  " + User.uid);
 	%>
 	</h1><br/><br/>
 	 
<%
 	int deptID = User.getDeptID(User.uid);
	ArrayList<pdf> list = pdf.listPDF(deptID);
%>
<form action="#" method="post">
	<table border="1" width="500">
	<tr>
		<th>UID</th>
		<th>Last Updated</th>
		<th>view pdf</th>
	</tr>
	<%
	
	for(int i=0;i<pdf.getCount(deptID);i++) {
		out.println("<tr>");
		out.println("<th>"+list.get(i).uid+"</th>");
		out.println("<th>"+list.get(i).lastUpdated+"</th>");
		out.println("<th><button type='submit' name='button' value='" + list.get(i).uid + "'>view form</button></th>");
		out.println("</tr>");
	}
	%>
	</table>
		</form>
	<%
		if(request.getParameter("button")!=null){
			String str = request.getParameter("button");
			out.println("<iframe width='70%' height='500px' src='data:application/pdf;base64,"+ pdf.getEncodedData(str) + "'></iframe>");
			out.println("<br/><form action='comments_submission.jsp'><label>Comments</label> <input type='text' size='100' name='comments'/><input type='hidden' name='UID' value='"+ str +"'><button type='submit' value='submit'>Submit</button></form>");
		}
	%>
</body>
</html>