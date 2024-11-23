<%@ page import="javax.servlet.http.*, java.util.*" %>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String role = (String) session.getAttribute("role");
%>

<h2>Welcome, <%= session.getAttribute("username") %>!</h2>
<p>Your role is: <%= role %></p>

<% if ("Admin".equals(role)) { %>
    <a href="addSoftware.jsp">Add Software</a>
<% } %>

<% if (!"Employee".equals(role)) { %>
    <a href="manageRequests.jsp">Manage Access Requests</a>
<% } else { %>
    <a href="requestAccess.jsp">Request Software Access</a>
<% } %>
