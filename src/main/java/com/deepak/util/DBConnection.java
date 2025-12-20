package com.deepak.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/user_db";
	private static final String USER = "deepak";
	private static final String PASSWORD = "deepak123";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver class not found!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database connection failed!");
		}
		return conn;
	}
}
