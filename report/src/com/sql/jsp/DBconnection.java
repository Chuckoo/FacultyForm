package com.sql.jsp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	
	static String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/test";
	static String USERNAME = "root";
	static String PASSWORD = "";
	
	public static Connection getConnection() {
		Connection myConnection = null;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
			myConnection = DriverManager.getConnection(CONNECTIONSTRING, USERNAME, PASSWORD);
			return myConnection;
		} catch (Exception e) {
			System.out.println("Error in DBconnection/getConnection() " + e);
			return myConnection;
		}
	}
	
	public static void setCONNECTIONSTRING(String connectionString) {
		CONNECTIONSTRING = connectionString;
	}

	public static void setUSERNAME(String username) {
		USERNAME = username;
	}

	public static void setPASSWORD(String password) {
		PASSWORD = password;
	}

}
