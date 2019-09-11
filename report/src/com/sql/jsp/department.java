package com.sql.jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class department {
	
	public String Dname;
	public String Dtag;
	public int DID;
	
	department(String deptName,String tag,int id){
		this.Dname=deptName;
		this.Dtag=tag;
		this.DID=id;
	}
	
	public static void addDept(String deptName,String tag) {
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO department_info (deptName,deptTag) VALUES (?,?)");
			ps.setString(1, deptName);
			ps.setString(2, tag);
			ps.executeUpdate();
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in department/addDept() ");
            System.err.println(e.getMessage());
        }
		
	}
	
	public static void delDept(String id) {
		try {
			Connection conn = DBconnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM department_info WHERE deptID=?");
            ps.setString(1, id);
            ps.execute();   
		}
		catch (Exception e) {
            System.err.println("Got an exception! Error in department/delDept() ");
            System.err.println(e.getMessage());
		}
	}
	
	public static int getDeptID (String tag) {
		int ID = -1;
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT deptID FROM department_info WHERE deptTag=?");
			ps.setString(1, tag);
			ResultSet rs = ps.executeQuery();
            rs.next();
            ID = rs.getInt("deptID");   
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in department/getDeptID() ");
            System.err.println(e.getMessage());
        }
		return ID;
	}
	
	public static String getDeptTag (int id) {
		String tag = null;
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT deptTag FROM department_info WHERE deptID=?");       
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			tag = rs.getString("deptTag");
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in department/getDeptTag() ");
            System.err.println(e.getMessage());
        }
		return tag;
	}
	
	public static int getCount() {
		int count=0;
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM department_info");       
			ResultSet rs = ps.executeQuery();
			rs.next();
			count=rs.getInt("COUNT(*)");
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in department/getCount() ");
            System.err.println(e.getMessage());
        }
		return count;
	}
	
	public static ArrayList<department> listDept () {
		ArrayList<department> deptList = new ArrayList<department>();
		try {
			Connection conn = DBconnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM department_info");       
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				deptList.add(new department(rs.getString("deptName"),rs.getString("deptTag"),rs.getInt("deptID")));
			}
            conn.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception! Error in department/listDept() ");
            System.err.println(e.getMessage());
        }
		return deptList;
	}

}
