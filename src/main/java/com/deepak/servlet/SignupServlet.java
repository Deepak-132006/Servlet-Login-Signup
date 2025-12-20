package com.deepak.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.deepak.util.DBConnection;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try (Connection con = DBConnection.getConnection()) {
			String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);

			int rows = ps.executeUpdate();

			if (rows > 0)
				out.println("<h3>✅ Signup Successful! You can now <a href='login.html'>login</a>.</h3>");
			else
				out.println("<h3>❌ Signup failed. Try again.</h3>");
		} catch (SQLException e) {
			e.printStackTrace(out);
		}
	}
}
