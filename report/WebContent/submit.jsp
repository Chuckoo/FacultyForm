<%@page import="org.apache.pdfbox.pdmodel.PDDocument"%>
<%@page import="org.apache.pdfbox.pdmodel.PDPage"%>
<%@page import="org.apache.pdfbox.pdmodel.PDPageContentStream"%>
<%@page import="org.apache.pdfbox.pdmodel.font.PDFont"%>
<%@page import="org.apache.pdfbox.pdmodel.font.PDType1Font"%>
<%@page import="com.sql.jsp.pdf"%>
<%@page import="com.sql.jsp.User"%>
<%@page import="mail.toHOD"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>form submission</title>
</head>
<body>
	<h1>submitting form...</h1><br/><br/><br/>
<%  
    if(request.getParameter("nameform1")!=null) {
  		PDDocument doc = new PDDocument(); 			
   		PDPage pg = new PDPage(); 					
		doc.addPage(pg);							
		PDPageContentStream contents = new PDPageContentStream(doc, pg); 
		
		contents.beginText();						
		contents.setLeading(25);					
  		contents.setFont( PDType1Font.TIMES_ROMAN, 16 );
		contents.newLineAtOffset(50, 700);			
		contents.showText("Name of the employee:     "+ request.getParameter("nameform1"));
		contents.newLine();		
		contents.showText("Service to which the employee belongs:     "+ request.getParameter("cb1form1"));
		contents.newLine();		
		contents.showText("Name of the department to which you belong:     "+ request.getParameter("depform1"));
		contents.newLine();		
		contents.showText("Residential Address:     "+ request.getParameter("Addressform1"));
		contents.newLine();		
		contents.showText("Mobile Number:     "+ request.getParameter("Mobileform1"));
		contents.newLine();		
		contents.showText("E-mail:     "+ request.getParameter("e-mailform1"));
		contents.newLine();		
		contents.showText("Post held during the year/period of report:     "+ request.getParameter("postform1"));
		contents.newLine();		
		contents.showText("Date of Birth:     "+ request.getParameter("dateofbirthform1"));
		contents.newLine();		
		contents.showText("Educational Qualification:     "+ request.getParameter("Educationalform1"));
 		contents.endText();							
 		contents.close();
		
 		byte [] data = pdf.toByteArray(doc);  
		pdf.storePDF(data, User.uid);
		doc.close();
		
		toHOD.submission(User.uid); //sends the mail to HOD of respective dept

		out.print("submission successfull, HOD notified");
	}
		
%>

</body>
</html>