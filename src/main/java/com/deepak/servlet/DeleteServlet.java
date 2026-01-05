package com.deepak.servlet;

import com.deepak.db.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServerException, IOException{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			Connection conn = DBConnection.getConnection();
			String query = "DELETE FROM users WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			
			if(rows > 0) out.println("<h2>Deleted SuccessFully</h2>");
			else out.println("<h2>User not Found</h2>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
