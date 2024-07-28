<%-- 
    Document   : cart
    Created on : Feb 23, 2022, 10:07:09 PM
    Author     : Admin
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Cart</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <c:if test="${sessionScope.account != null}">
                    <a class="navbar-brand" href="HomeController">Hello ${sessionScope.account.user}</a>
                </c:if>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="HomeController">Home</a></li>
                            <c:if test="${sessionScope.account.user != null}">
                                <c:if test="${sessionScope.account.isadmin == 1 || sessionScope.account.issell == 1}">
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Manage</a>
                                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <c:if test="${sessionScope.account.isadmin == 1}">
                                                <li><a class="dropdown-item" href="edit-account">Manage Account</a></li>
                                                <li><a class="dropdown-item" href="manage-category">Manage Category</a></li>
                                                <li><a class="dropdown-item" href="manage-product">Manage Product</a></li>
                                            </c:if>
                                            <c:if test="${sessionScope.account.issell == 1 && sessionScope.account.isadmin != 1}">
                                                <li><a class="dropdown-item" href="manage-product">Manage Product</a></li>
                                                <li><a class="dropdown-item" href="manage-category">Manage Category</a></li>
                                            </c:if>
                                        </ul>
                                    </li>
                                </c:if>
                            </c:if>    
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="HomeController">All Products</a></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- Search form -->
                    <form class="d-flex me-3" action="search-controller" method="POST">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search">
                        <button class="btn btn-outline-dark" type="submit">Search</button>
                    </form>
                    <!-- Login button -->
                    <c:if test="${sessionScope.account == null}">
                        <button class="btn btn-outline-dark me-2" type="button">
                            <a href="login-controller" class="text-dark text-decoration-none">Login</a>
                        </button>
                    </c:if>
                    <c:if test="${sessionScope.account != null}">
                        <button class="btn btn-outline-dark me-2" type="button">
                            
                            <a href="logout-controller" class="text-dark text-decoration-none">Logout</a>
                        </button>                        
                    </c:if>


                    <!-- Cart button -->
                    <div class="d-flex my-2">
                        <c:choose>
                            <c:when test="${empty sessionScope.account}">
                                <a class="btn btn-outline-dark" href="login-controller">
                                    <i class="bi-cart-fill me-1"></i>
                                    Cart
                                    <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-outline-dark" href="view-cart">
                                    <i class="bi-cart-fill me-1"></i>
                                    Cart
                                    <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.cart.totalAmount()}</span>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>

            </div>
        </div>
    </nav>
        <!-- Product section-->
        <section class="py-5">
            <div class="container" style="min-height: 1000px">
                <c:choose>
                    <c:when test="${sessionScope.cart.item == null}">
                        <h1>List Cart is Empty</h1>
                    </c:when>
                    <c:otherwise>
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
                                        <td>${C.product.id}</td>
                                        <td><img src="${C.product.image}" width="50" alt="${C.product.name}"></td>
                                        <td>${C.product.name}</td>
                                        <td>${C.product.price}</td>
                                        <td>
                                            <div class="product-amount">
                                                <a href="change-amount?amount=${C.amount + 1}&pid=${C.product.id}" class="btn btn-outline-secondary">+</a>
                                                <span class="amount-value">${C.amount}</span>
                                                <a href="change-amount?amount=${C.amount - 1}&pid=${C.product.id}" class="btn btn-outline-secondary">-</a>
                                            </div>
                                        </td>



                                        <td>${C.product.price * C.amount}</td>
                                        <td>
                                            <a href="delete-cart?pid=${C.product.id}" class="btn btn-outline-danger">
                                                <i class="bi bi-trash"></i>Delete
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <h3>Total Amount: $${sessionScope.cart.getTotalMoney()}</h3>
                        <a href="check-out" class="btn btn-success w-25">Check out</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
        <footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">
            Viet Website 2022
        </p>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
    </body>
</html>
