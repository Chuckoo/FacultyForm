<%@page import="com.sql.jsp.pdf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1><%
		pdf.appendPDF(request.getParameter("UID"),request.getParameter("comments"));
		out.print("Review submitted");
	%></h1>
</body>
</html>