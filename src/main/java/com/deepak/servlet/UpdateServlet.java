package com.deepak.servlet;

import com.deepak.db.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws SecurityException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		try {
			Connection conn = null;
			conn = DBConnection.getConnection();
			String query = "UPDATE users SET username=?, email=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, id);

			int row = ps.executeUpdate();
			if (row > 0)
				out.println("<h2>User Updated</h2>");
			else
				out.println("<h2>User Not Found</h2>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
