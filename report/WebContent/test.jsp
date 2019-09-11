<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sql.jsp.department"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>

<%
	//retrieve.appendPDF(request.getParameter("UID"),request.getParameter("comments"));
	//out.print("Review submitted");
	ArrayList<department> list = department.listDept();
%>
		<table border="1">
<%
	for(int i=0;i<department.getCount();i++) {
		out.println("<tr>");
		out.println("<th>"+list.get(i).Dname+"</th>");
		out.println("<th>"+list.get(i).Dtag+"</th>");
		out.println("<th>"+list.get(i).DID+"</th>");
		out.println("</tr>");
	}

%>
		</table>
	</body>
</html>