package com.sql.jsp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

public class pdf {
	
	public String uid,lastUpdated;
	
	pdf(String id,String LUT){
		this.uid=id;
		lastUpdated=LUT;
	}
		
	public static int getCount(int deptID) { 	//returns count of PDFs in the DB, for that Department
		int count = 0;
		PreparedStatement ps = null;
		try {
			 Connection conn = DBconnection.getConnection();	
			 if(deptID==0) {	//return total count for principal/admin whos deptID will be 0
				 ps = conn.prepareStatement("SELECT COUNT(*) FROM pdf");
			 }
			 else {
				 ps = conn.prepareStatement("SELECT COUNT(*) FROM pdf WHERE UID IN (SELECT UID FROM user_info WHERE deptID=?)");
				 ps.setInt(1, deptID);
			 }
			 ResultSet rs = ps.executeQuery();
			 rs.next();	
			 count = rs.getInt("COUNT(*)");
			 conn.close();
		}
		catch (Exception e) {
           System.err.println("Got an exception! Error in pdf/getCount() ");
           System.err.println(e.getMessage());
		}
		return count;
	}
	
	public static ArrayList<pdf> listPDF (int deptID) {
		ArrayList<pdf> PDFlist = new ArrayList<pdf>();
		PreparedStatement ps = null;
		try {
			Connection conn = DBconnection.getConnection();
			if(deptID==0) {		//dumps all rows in case of admin/principal whos deptID = 0
				ps = conn.prepareStatement("SELECT UID,LastUpdated FROM pdf");       
			}
			else {
				ps = conn.prepareStatement("SELECT UID,LastUpdated FROM pdf WHERE UID IN (SELECT UID FROM user_info WHERE deptID=?)");       
				ps.setInt(1, deptID);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PDFlist.add(new pdf(rs.getString("UID"),rs.getString("LastUpdated")));
			}
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in department/listPDF() ");
            System.err.println(e.getMessage());
        }
		return PDFlist;
	}
		
	public static void storePDF (byte[] pdfData,String name) { 
		try {
			Connection conn = DBconnection.getConnection();		
			PreparedStatement ps = conn.prepareStatement("INSERT INTO pdf (UID,PDF) VALUES (?,?)");
			ps.setString(1, name);
			ps.setBytes(2, pdfData); 
			ps.executeUpdate();	
			conn.close();
		}
		catch (Exception e) {
            System.err.println("Got an exception! Error in pdf/storePDF() ");
            System.err.println(e.getMessage());
		}	
	}
	
	public static InputStream getPDF_data (String name) {
		InputStream pdfData = null;
		try {
			 Connection conn = DBconnection.getConnection();			
			 PreparedStatement ps = conn.prepareStatement("SELECT PDF FROM pdf WHERE UID=?");
			 ps.setString(1, name);
			 ResultSet rs = ps.executeQuery();
			 rs.next();	
			 pdfData = rs.getBinaryStream("PDF");
			 conn.close();
		}
		catch (Exception e) {
            System.err.println("Got an exception! Error in pdf/getPDF_data() ");
            System.err.println(e.getMessage());
		}
		return pdfData;
	}
	
	public static String getEncodedData(String name) {
		
		InputStream pdfData = getPDF_data(name);
		int size = getPDF_size(name);
		
		byte[] buffer = new byte[size];
		String encodedData=null;
		
		try {
			pdfData.read(buffer,0,size);
			pdfData.close();
			encodedData = Base64.encodeBase64String(buffer);
		}
		catch(Exception e) {
            System.err.println("Got an exception! Error in pdf/getEncodedData() ");
            System.err.println(e.getMessage());
		}
		return encodedData;
	}
	
	public static byte[] toByteArray(PDDocument pdDoc) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            pdDoc.save(out);
            pdDoc.close();
        } 
        catch (Exception e) {
        	System.err.println("Got an exception! Error in pdf/toByteArray() ");
            System.err.println(e.getMessage());
        }
        return out.toByteArray();
    }
	
	public static int getPDF_size(String name) {
		int size=0;
		try {
			Connection conn = DBconnection.getConnection();			
			PreparedStatement ps = conn.prepareStatement("SELECT OCTET_LENGTH(PDF) AS size FROM pdf WHERE UID=?");
			ps.setString(1, name);   
			ResultSet rs = ps.executeQuery();
			rs.next();
			size = rs.getInt("size");   
			conn.close();
		}
		catch (Exception e) {
            System.err.println("Got an exception! Error in pdf/getPDF_size() ");
            System.err.println(e.getMessage());
		}
		return size;
	}
	
	public static void delPDF(String name) {
		try {
			Connection conn = DBconnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM pdf WHERE UID=?");
            ps.setString(1, name);
            ps.execute();   
            conn.close();
		}
		catch (Exception e) {
            System.err.println("Got an exception! Error in pdf/delPDF() ");
            System.err.println(e.getMessage());
		}
	}
	
	public static void appendPDF(String name,String data) {
		try {
            PDDocument doc = PDDocument.load(getPDF_data(name));
            delPDF(name);
            
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
            System.err.println("Got an exception! Error in pdf/appendPDF()  ");
            System.err.println(e.getMessage());
		}			
	}

}
