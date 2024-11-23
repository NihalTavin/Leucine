<% 
    HttpSession session = request.getSession(false);
    if (session == null || !"Manager".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<h2>Manage Access Requests</h2>

<table>
    <tr>
        <th>User</th>
        <th>Software</th>
        <th>Access Type</th>
        <th>Reason</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <%-- Fetch and display pending requests from database here --%>
    <tr>
        <td>John Doe</td>
        <td>Software 1</td>
        <td>Read</td>
        <td>For project X</td>
        <td>Pending</td>
        <td>
            <form action="manageRequest" method="post" style="display:inline;">
                <input type="hidden" name="request_id" value="1">
                <button type="submit" name="action" value="approve">Approve</button>
                <button type="submit" name="action" value="reject">Reject</button>
            </form>
        </td>
    </tr>
</table>
