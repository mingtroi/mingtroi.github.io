<%-- 
    Document   : test
    Created on : Jul 7, 2024, 11:56:00 PM
    Author     : nhat minh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>List Products</h3>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Image</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Amount</th>
                                    <th scope="col">Total Price</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.cart.item}" var="C">
                                    <tr>
                                        <th scope="row">${C.product.id}</th>
                                        <td>${C.product.name}</td>
                                        <td><img src="${C.product.image}" width="50"/></td>
                                        <td>${C.product.price}</td>
                                        <td><input onchange="this.form.submit()" type="number" name="amount" value="${C.amount}"/></td>
                                        <td>${C.product.price*C.amount}</td>
                                        <td><a href="delete-cart?productId=${C.product.id}" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Delete</a></td>
                                    </tr>
                            </c:forEach>
                            </tbody>
                        </table>
    </body>
</html>
