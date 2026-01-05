package com.deepak.servlet;

import com.deepak.db.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/users")
public class ViewUsersServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServerException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("username");
				String email = rs.getString("email");

				out.println("<tr>");
				out.println("<td>" + id + "</td>");
				out.println("<td>" + name + "</td>");
				out.println("<td>" + email + "</td>");
				out.println("<tr>");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
