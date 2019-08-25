<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
<div align="center">
    <form action="/user/checkout" method="POST">
        <table align="top" border="1" cellpadding="4" cellspacing="0">
            <caption>Products list in the basket</caption>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th></th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>
                        <input type="button" value="delete"
                               onClick='location.href="/user/basket/product/delete?id=${product.id}"'>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <label for="name">First name:</label><br/>
            <input type="text" name="firstName"/>
        </p>
        <p>
            <label for="lastName">Last name:</label><br/>
            <input type="text" name="lastName"/>
        </p>
        <p>
            <label for="city">City:</label><br/>
            <input type="text" name="city"/>
        </p>
        <p>
            <label for="code">Street:</label></br>
            <input type="text" name="street"/>
        </p>
        <p>
            <label for="code">House number:</label></br>
            <input type="number" min="0" step="1" name="houseNumber"/>
        </p>
        <p>
            <label for="code">Phone number:</label></br>
            <input type="text" name="phoneNumber"/>
        </p>
        ${message}
        <p>
            <input type="submit" class="inline" value="Submit">
            <input type="button" class="inline" value="Exit" onClick='location.href="/exit"'>
        </p>
    </form>
</div>
</body>
</html>
