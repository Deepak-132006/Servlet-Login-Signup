package com.deepak.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.deepak.util.DBConnection;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try (Connection con = DBConnection.getConnection()) {
			String query = "SELECT * FROM users WHERE email=? AND password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				out.println("<h2>Welcome back, " + rs.getString("username") + " 👋</h2>");
			} else {
				out.println("<h3>❌ Invalid email or password. Try again.</h3>");
			}
		} catch (SQLException e) {
			e.printStackTrace(out);
		}
	}
}
