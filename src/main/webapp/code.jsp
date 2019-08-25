<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Confirm code</title>
</head>
<body>
<div style="text-align: center;">
    <form action="/user/code" method="POST">
        <p>Confirm code:<br><input type="password" name="code"></p>
        ${message}
        <p>
            <input type="submit" class="inline" value="Submit">
            <input type="button" class="inline" value="Exit" onClick='location.href="/exit"'>
        </p>
    </form>
</div>
</body>
</html>
