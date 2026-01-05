package com.deepak.servlet;

import com.deepak.db.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("username");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String password = BCrypt.hashpw(pass, BCrypt.gensalt() );
        
        if (name == null || name.isEmpty() ||
        	    email == null || email.isEmpty() ||
        	    password == null || password.isEmpty()) {
        	    res.getWriter().println("All fields are required!");
        	    return;
        	}

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();

            if (conn == null) {
                out.println("<h3>Database connection failed</h3>");
                return;
            }

            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<h2>User Registered Successfully!</h2>");
            } else {
                out.println("<h2>Registration Failed</h2>");
            }

        } catch (SQLException e) {
            out.println("<h3>Error occurred</h3>");
            e.printStackTrace(out);

        }
    }

}
