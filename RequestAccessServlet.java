package com.example.nihal.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.nihal.database.DatabaseConnection;

@WebServlet("/requestAccess")
public class RequestAccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        String role = (String) session.getAttribute("role");

        if (userId == null || !"Employee".equals(role)) {
            response.sendRedirect("login.jsp");
            return;
        }

        int softwareId = Integer.parseInt(request.getParameter("software_id"));
        String accessType = request.getParameter("access_type");
        String reason = request.getParameter("reason");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO requests (user_id, software_id, access_type, reason) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, softwareId);
            stmt.setString(3, accessType);
            stmt.setString(4, reason);
            stmt.executeUpdate();

            response.sendRedirect("dashboard.jsp?success=requestSubmitted");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("requestAccess.jsp?error=databaseError");
        }
    }
}
