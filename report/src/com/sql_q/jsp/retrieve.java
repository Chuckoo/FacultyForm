package com.sql_q.jsp;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;


public class retrieve {
	
	static String url = "jdbc:mysql://localhost:3306/test";
	static String username = "admin";
	static String password = "admin";
	static Connection conn = null;
	public static String user = null;
	public static String Eid = null;

	
	public static ResultSet SqlQuery (String query) {
		ResultSet rs = null;
		try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
        } 
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		return rs;
		
	}
	
	
	public static byte[] toByteArray(PDDocument pdDoc) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            pdDoc.save(out);
            pdDoc.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return out.toByteArray();
    }
	
	public static String hodtable () {
		String table="";
		ResultSet rs=null;
		try { 
            //rs = SqlQuery("SELECT deptID FROM user_info WHERE UID='" + user + "'");       
            //rs.next();
            //deptID = rs.getInt("deptID");
            rs = SqlQuery("SELECT * FROM pdf"); 
            while(rs.next())
            {
        		table = table + "<tr>"
        		+"<th>"+rs.getString("UID")+"</th>"
        		+"<th>"+rs.getString("LastUpdated")+"</th>"
        		+"<th><button type='submit' name='button' value='"+rs.getString("UID")+"'>view form</button></th>"
        		+"</tr>";
        	}
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
	
		return table;	
	}
	
	public static int login (String user, String password) {
		int status= -2;
		try {
            ResultSet rs = SqlQuery("SELECT UID,Password FROM user_info WHERE UID=" + "'" + user + "'");          
            if (rs.next() == false) { status = -1; }  //UID does not not exist
            else if (password.equals(rs.getString("Password").toString())) {  status = 1; }   //UID exists and valid password
            else {  status = 0; } //UID exists but invalid password
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		return status;
	}
		

		public static void storePDF (byte[] pdfData,String name) { //work in progress
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
				conn = DriverManager.getConnection(url,username,password);		
				PreparedStatement ps = conn.prepareStatement("INSERT INTO pdf (UID,PDF) VALUES (?,?)");
				ps.setString(1, name);
				ps.setBytes(2, pdfData);  // byte[] array
				ps.executeUpdate();	
			}
			catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
			}	
		}
		
		public static void appendPDF(String name,String data) {
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		        conn = DriverManager.getConnection(url,username,password);		
				PreparedStatement ps = conn.prepareStatement("SELECT PDF FROM pdf WHERE UID=?");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
	            rs.next();
	            PDDocument doc = PDDocument.load(rs.getBinaryStream("PDF"));
	            rs.close();
	            
	            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
	            conn = DriverManager.getConnection(url,username,password);
	            Statement stmt = conn.createStatement();
	            stmt.execute("DELETE FROM pdf WHERE UID='"+name+"'");
	            
	            PDPage pg = new PDPage(); 					
	        	 	doc.addPage(pg);							
	        		PDPageContentStream contents = new PDPageContentStream(doc, pg); 
	        		contents.beginText();						
	        		contents.setLeading(25);					
	        	   	contents.setFont( PDType1Font.TIMES_ROMAN, 16 );
	        		contents.newLineAtOffset(50, 700);		
	        		contents.showText("HOD COMMENTS");
	        		contents.newLine();
	        		contents.newLine();
	        		contents.showText(data);
	        		contents.endText();
	        		contents.close();
	        		storePDF(toByteArray(doc), name);
	        		doc.close();
	            
			}
			catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
			}
			
			
		}
		
		public static int getPDF_size(String name) {
			int size=0;
			try {
			ResultSet rs = SqlQuery("SELECT OCTET_LENGTH(PDF) AS size FROM pdf WHERE UID='"+ name + "'");       
            rs.next();
            size = rs.getInt("size");   
            conn.close();
			}
			catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
			}
			return size;
		}
		
		public static String getPDF (String name) { //work in progress
			String Str ="";
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		        conn = DriverManager.getConnection(url,username,password);		
				PreparedStatement ps = conn.prepareStatement("SELECT PDF FROM pdf WHERE UID=?");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				
	            while (rs.next()) {
	                InputStream input = rs.getBinaryStream("PDF");
	                int size = getPDF_size(name);
	                byte[] buffer = new byte[size];
	                input.read(buffer,0,size);
	                input.close();
	                Str = Base64.encodeBase64String(buffer);
	                //conn.close();
	            }	
			}
			catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
			}
			return Str;
		}
		
		public static void addDept(String deptName,String tag) {
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
				conn = DriverManager.getConnection(url,username,password);		
				PreparedStatement ps = conn.prepareStatement("INSERT INTO department_info (deptName,deptTag) VALUES (?,?)");
				ps.setString(1, deptName);
				ps.setString(2, tag);
				ps.executeUpdate();
	            conn.close();
	        } 
	        catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
	        }
			
		}
		
		public static int getDeptID (String tag) {
			int ID = -1;
			try {
	            ResultSet rs = SqlQuery("SELECT deptID FROM department_info WHERE deptTag='" + tag + "'");       
	            rs.next();
	            ID = rs.getInt("deptID");   
	            conn.close();
	        } 
	        catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
	        }
			return ID;
		}
		
		public static String[] getDeptInfo (int id) {
			String[] info = new String[2];
			try {
	            ResultSet rs = SqlQuery("SELECT deptTag,deptName FROM department_info WHERE deptID='" + Integer.toString(id) + "'");       
	            rs.next();
	            info[0] = rs.getString("deptTag");
	            info[1] = rs.getString("deptName");
	            conn.close();
	        } 
	        catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
	        }
			return info;
		}
		
		
		public static void Email(String to,String subject,String messg) {
		
			    final String from = "vikram.is17@bmsce.ac.in";
			    final String pass = "dragon@27";

			    String host = "smtp.gmail.com";
			    Properties props = new Properties();
			    props.put("mail.smtp.host", host);
			    props.put("mail.transport.protocol", "smtp");
			    props.put("mail.smtp.auth", "true");
			    props.put("mail.smtp.starttls.enable", "true");
			    props.put("mail.user", from);
			    props.put("mail.password", pass);
			    props.put("mail.smtp.port", "587");

			    Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			        @Override
			        protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(from, pass);
			        }
			    });

			    try {
			        MimeMessage message = new MimeMessage(mailSession);
			        message.setFrom(new InternetAddress(from));
			        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			        message.setSubject(subject);
			        message.setText(messg);
			        Transport.send(message);

			    } catch (MessagingException mex) {

			    	System.err.println("Got an exception! ");
		            System.err.println(mex.getMessage());
			    }
		}
		
		
		public static void mailHOD(String id) {
			int dID=0;
			try {
	            ResultSet rs = SqlQuery("SELECT deptID FROM user_info WHERE UID='"+id+"'");       
	            rs.next();
	            dID = rs.getInt("deptID");   
	            conn.close();
	            
	            rs = SqlQuery("SELECT UID FROM user_info WHERE deptID="+dID+" AND designation='HOD'");
	            rs.next();
	            Email(rs.getString("UID"),"notification","the user "+id+" submitted the from \n http://localhost:8080/report/login.jsp");
	            conn.close();
	        } 
	        catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
	        }
		}
	
}
