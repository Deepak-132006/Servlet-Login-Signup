package com.deepak.servlet;

import com.deepak.db.DBConnection;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ClassCastException, IOException{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		Connection conn = null;
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM users WHERE email=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();			
			
			if (rs.next()) {
				String storedHash = rs.getString("password");
				if(BCrypt.checkpw(password, storedHash)){
					out.println("<h2>Logged In!!!</h2>");
				}
				else out.println("<h2>Wrong Password</h2>");	
			} else {
				out.println("<h2>User Not Found</h2>");
			}
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		
	}
}
