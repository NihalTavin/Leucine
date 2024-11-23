<% 
    HttpSession session = request.getSession(false);
    if (session == null || !"Employee".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<h2>Request Software Access</h2>
<form action="requestAccess" method="post">
    <label>Software:</label>
    <select name="software_id">
        <%-- Fetch and display software applications from database here --%>
        <option value="1">Software 1</option>
        <option value="2">Software 2</option>
    </select><br>
    <label>Access Type:</label>
    <select name="access_type">
        <option value="Read">Read</option>
        <option value="Write">Write</option>
        <option value="Admin">Admin</option>
    </select><br>
    <label>Reason:</label><textarea name="reason"></textarea><br>
    <input type="submit" value="Request Access">
</form>
