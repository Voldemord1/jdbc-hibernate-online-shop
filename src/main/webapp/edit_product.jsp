<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<div align="center">
    ${message}
    <form action="/product" method="post">
        <p>
            <label for="name">Name:</label><br/>
            <input id="name" name="name" value="${product.name}"/>
        </p>
        <p>
            <label for="description">Description:</label><br/>
            <input type="description" name="description" value="${product.description}"/>
        </p>
        <p>
            <label for="price">Price:</label><br/>
            <input type="number" name="price" value="${product.price}" step="0.01"/>
        </p>
        <p>
            <button type="submit" name="id" value="${product.id}">save</button>
        </p>
    </form>
</div>
</body>
</html>
