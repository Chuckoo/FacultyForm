package com.sql.jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
	
	public static String uid = null; 	//temporary variable for remembering user info 
										//will be removed once a proper login scheme is done
	
	public static void addUser(String Name,String ID,String designation,int deptID) {
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO user_info (Name,UID,designation,deptID) VALUES (?,?,?,?)");
			ps.setString(1, Name);
			ps.setString(2, ID);
			ps.setString(3, designation);
			ps.setInt(4, deptID);
			ps.executeUpdate();
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in User/addUser() ");
            System.err.println(e.getMessage());
        }
		
	}
	
	public static void delUser(String ID) {
		try {
			Connection conn = DBconnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM user_info WHERE UID=?");
            ps.setString(1, ID);
            ps.execute();   
            conn.close();
		}
		catch (Exception e) {
            System.err.println("Got an exception! Error in User/delUser() ");
            System.err.println(e.getMessage());
		}
	}
	
	public static String getName (String ID) {
		String name = null;
		try { 
			Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT Name FROM user_info WHERE UID=?");
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			name = rs.getString("Name");
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in User/getName() ");
            System.err.println(e.getMessage());
        }
		return name;
	}
	
	public static String getDes (String ID) {
		String des = null;
		try {
            Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT designation FROM user_info WHERE UID=?");
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();
            rs.next();
            des = rs.getString("designation");   
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in User/getDes() ");
            System.err.println(e.getMessage());
        }
		return des;
	}
	
	public static int getDeptID (String ID) {
		int deptID = 0;
		try {
            Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT deptID FROM user_info WHERE UID=?");
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();
            rs.next();
            deptID = rs.getInt("deptID");   
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in User/getDeptID() ");
            System.err.println(e.getMessage());
        }
		return deptID;
	}
	
	public static String getPwd (String ID) {
		String pwd = null;
		try {
            Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT Password FROM user_info WHERE UID=?");
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();
            rs.next();
            pwd = rs.getString("Password");   
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in User/getPwd() ");
            System.err.println(e.getMessage());
        }
		return pwd;
	}
	
	public static void setName (String ID, String newName) {
		try {
            Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE user_info SET Name=? WHERE UID=?");
			ps.setString(1, newName);
			ps.setString(2, ID);
			ps.executeUpdate();  
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in User/setName() ");
            System.err.println(e.getMessage());
        }
	}
	
	public static void setPwd (String ID, String newPwd) {
		try {
            Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE user_info SET Password=? WHERE UID=?");
			ps.setString(1, newPwd);
			ps.setString(2, ID);
			ps.executeUpdate();  
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in User/setPwd() ");
            System.err.println(e.getMessage());
        }
	}
	
	public static String getHOD(String ID) {
		String HOD_ID=null;
		try {
            Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT deptID FROM user_info WHERE UID=?");
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();
            rs.next();
            int deptID = rs.getInt("deptID"); 
            
            ps = conn.prepareStatement("SELECT UID FROM user_info WHERE deptID=? AND designation='HOD'");
			ps.setInt(1, deptID);
			rs = ps.executeQuery();
            rs.next();
            HOD_ID=rs.getString("UID");
            conn.close();
            
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in User/getHOD() ");
            System.err.println(e.getMessage());
        }
		return HOD_ID;
	}

}
