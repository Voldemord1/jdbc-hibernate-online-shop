<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<div align="center">
    <form action="/user/products" method="post">
        <table align="top" border="1" cellpadding="4" cellspacing="0">
            <caption>Products list</caption>
            <tr>
                <c:if test="${user.role == 'admin'}">
                    <th>Id</th>
                </c:if>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <c:choose>
                    <c:when test="${user.role == 'admin'}">
                        <th></th>
                        <th></th>
                    </c:when>
                    <c:otherwise>
                        <th></th>
                    </c:otherwise>
                </c:choose>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <c:if test="${user.role == 'admin'}">
                        <td>${product.id}</td>
                    </c:if>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <c:choose>
                        <c:when test="${user.role=='admin'}">
                            <td>
                                <input type="button" class="inline" value="edit"
                                       onClick='location.href="/product?id=${product.id}"'>
                            </td>
                            <td>
                                <input type="button" class="inline" value="delete"
                                       onClick='location.href="/admin/product/delete?id=${product.id}"'>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <input type="button" class="inline" value="buy"onClick='location.href="/user/product/by" +
                                        "?productId=${product.id}&userId=${user.id}"'>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </form>
    <c:choose>
        <c:when test="${user.role=='admin'}">
            <input type="button" class="inline" value="Users" onClick='location.href="/admin/users"'>
            <input type="button" class="inline" value="Add item" onClick='location.href="/add_product"'>
        </c:when>
        <c:otherwise>
            <p>${message}</p>
            <p>Goods in the basket ${productCountOfBasket}</p>
            <input type="button" class="inline" value="Basket"
                   onClick='location.href="/user/checkout?userId=${user.id}"'>
        </c:otherwise>
    </c:choose>
    <input type="button" class="inline" value="Exit" onClick='location.href="/exit"'>
</div>
</body>
</html>
