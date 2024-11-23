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


@WebServlet("/addSoftware")
public class AddSoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"Admin".equals(role)) {
            response.sendRedirect("dashboard.jsp?error=notAuthorized");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("access_levels");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, accessLevels);
            stmt.executeUpdate();

            response.sendRedirect("dashboard.jsp?success=softwareAdded");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("addSoftware.jsp?error=databaseError");
        }
    }
}
