package com.deepak.db;

import java.sql.*;
import java.sql.SQLException;

public class DBConnection {
	protected final static String url = "jdbc:mysql://localhost:3306/user_db";
	protected final static String user = "deepak";
	protected final static String password = "deepak123";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Database Connection Failed");
			e.printStackTrace();
		}
		return conn;
	}
}
