package com.amdocs.training.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static void main(String[] args) {
		
	}
	
	private static String dbUrl = "jdbc:mysql://localhost:3306/project";
	private static String username = "root";
	private static String password = "root";
	
	public static Connection getConnection() {
		// 1. Load Driver
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection(dbUrl, username, password);
					return conn;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
	}
}
