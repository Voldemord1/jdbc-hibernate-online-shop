<%@ page import="java.io.Writer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<div style="text-align: center;">
    ${message}
    <form action="/add_product" method="post">
        <p>
            <label for="name">Name:</label><br/>
            <input id="name" name="name" value=""/>
        </p>
        <p>
            <label for="description">Description:</label><br/>
            <input type="description" name="description"/>
        </p>
        <p>
            <label for="price">Price:</label><br/>
            <input type="number" name="price" value="0" min="0" step="0.01"/>
        </p>
        <p>
            <button type="submit" name="add">add</button>
        </p>
    </form>
</div>
</body>
</html>
