<% 
    HttpSession session = request.getSession(false);
    if (session == null || !"Admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<h2>Add New Software</h2>
<form action="addSoftware" method="post">
    <label>Name:</label><input type="text" name="name" required><br>
    <label>Description:</label><textarea name="description"></textarea><br>
    <label>Access Levels:</label>
    <select name="access_levels">
        <option value="Read">Read</option>
        <option value="Write">Write</option>
        <option value="Admin">Admin</option>
    </select><br>
    <input type="submit" value="Add Software">
</form>
