<%-- 
    Document   : detail
    Created on : Jun 19, 2024, 10:54:25 PM
    Author     : nhat minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Item - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
<script>
function checkMinValue(input) {
    if (input.value < 1) {
        input.value = 1;
    }
}
</script>

    </head>
    <body>
        <!-- Navigation-->
               <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container px-4 px-lg-5">
            <c:if test="${sessionScope.account != null}">
                <a class="navbar-brand" href="HomeController">Hello ${sessionScope.account.user}</a>
            </c:if>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="HomeController">Home</a></li>
                    <c:if test="${sessionScope.account != null}">
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
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${product.image}" alt="..." /></div>
                    <div class="col-md-6">
                        <div class="small mb-1">${product.id}</div>
                        <h1 class="display-5 fw-bolder">${product.name}</h1>
                        <div class="fs-5 mb-5">
                            <span>$${product.price}</span>
                        </div>
                        <p class="lead">${product.description}</p>
                        <div class="d-flex">
                                <i class="bi-cart-fill me-1"></i>
                                <a class="btn btn-outline-dark mt-auto" href="add-cart?amount=1&pid=${product.id}">
                                                        Add to cart
                                                    </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Related items section-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related products</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <c:forEach items="${list}" var="P">
                        <div class="col mb-5">
                            <div class="card h-100">
                                <!-- Product image-->
                                <a href="detail-controller?pid=${P.id}">
                                    <img class="card-img-top" src="${P.image}" alt="${P.name}" />
                                </a>
                                <!-- Product details-->
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <h5 class="fw-bolder"><a href="detail-controller?pid=${P.id}" class="no-underline">${P.name}</a></h5>
                                        <!-- Product reviews-->
                                        <div class="d-flex justify-content-center small text-warning mb-2">                      
                                        </div>
                                        <!-- Product price-->
                                        $${P.price}
                                    </div>
                                </div>
                                <!-- Product actions-->
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="add-cart?amount=1&pid=${product.id}">Add to cart</a></div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
