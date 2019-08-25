<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<div align="center">
    <form action="/admin/user" method="post">
        <p>
            <label for="email">Email:</label><br/>
            <input id="email" name="email" value="${user.email}"/>
        </p>
        <p>
            <label for="password">Password:</label><br/>
            <input type="password" name="password"/>
        </p>
        <p>
            <label for="confirmPassword">Confirm password:</label><br/>
            <input type="password" name="confirmPassword"/>
        </p>
        <p>
            <label><input type="radio" name="role" value="admin">admin</label>
            <label><input type="radio" name="role" value="user" checked="true">user</label><br/>
        </p>
        ${message}
        <p>
            <button type="submit" name="id" value="${user.id}">save</button>
        </p>
    </form>
</div>
</body>
</html>
