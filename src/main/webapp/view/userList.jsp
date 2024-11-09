<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>

    <%-- Check if userList is available and not empty --%>
    <%
        List<User> userList = (List<User>) request.getAttribute("userList");
        if (userList != null && !userList.isEmpty()) {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <%-- Iterate through the user list --%>
                <%
                    for (User user : userList) {
                %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= user.getFirstName() %> <%= user.getLastName() %></td>
                        <td><%= user.getEmail() %></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p>No users found.</p>
    <%
        }
    %>
</body>
</html>
