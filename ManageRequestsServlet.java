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

@WebServlet("/manageRequest")
public class ManageRequestsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"Manager".equals(role)) {
            response.sendRedirect("dashboard.jsp?error=notAuthorized");
            return;
        }

        int requestId = Integer.parseInt(request.getParameter("request_id"));
        String action = request.getParameter("action"); // "approve" or "reject"

        String status = "Rejected";
        if ("approve".equals(action)) {
            status = "Approved";
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();

            response.sendRedirect("manageRequests.jsp?success=requestUpdated");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("manageRequests.jsp?error=databaseError");
        }
    }
}
