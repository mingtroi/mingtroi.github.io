<%-- 
    Document   : CheckOut.jsp
    Created on : Jul 8, 2024, 10:55:17 PM
    Author     : nhat minh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            .item-details {
    margin-right: 20px; /* Điều chỉnh giá trị này để tạo khoảng cách phù hợp */
}

.item-image {
    margin-left: 20px; /* Điều chỉnh giá trị này để tạo khoảng cách phù hợp */
}

            .modal .modal-dialog {
                max-width: 400px;
            }
            .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                padding: 20px 30px;
            }
            .modal .modal-content {
                border-radius: 3px;
                font-size: 14px;
            }
            .modal .modal-footer {
                background: #ecf0f1;
                border-radius: 0 0 3px 3px;
            }
            .modal .modal-title {
                display: inline-block;
            }
            .modal .form-control {
                border-radius: 2px;
                box-shadow: none;
                border-color: #dddddd;
            }
            .modal textarea.form-control {
                resize: vertical;
            }
            .modal .btn {
                border-radius: 2px;
                min-width: 100px;
            }
            .modal form label {
                font-weight: normal;
            }
        </style>
    </head>
    <body>
<section class="h-100 h-custom" style="background-color: #eee;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col">
        <div class="card">
          <div class="card-body p-4">

            <div class="row">

              <div class="col-lg-7">
    <h5 class="mb-3"><a href="HomeController" class="text-body"><i class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a></h5>
    <hr>

    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <p class="mb-1">Shopping cart</p>
            <p class="mb-0">You have ${sessionScope.cart.totalAmount()} items in your cart</p>
        </div>
    </div>
        
    <c:forEach var="item" items="${sessionScope.cart.item}">
        <div class="card mb-3">
            <div class="card-body">
                <div class="d-flex justify-content-between">
                    <div class="d-flex flex-row align-items-center">
                        <div class="ms-3 item-details">
                            <h5>${item.product.name}</h5>
                        </div>
                        <div class="item-image">
                            <img src="${item.product.image}" class="img-fluid rounded-3" alt="Shopping item" style="width: 65px;">
                        </div>


                    </div>
                    <div class="d-flex flex-row align-items-center">
                        <div style="width: 50px;">
                            <h5 class="fw-normal mb-0">${item.amount}</h5>
                        </div>
                        <div style="width: 80px;">
                            <h5 class="mb-0">$${item.product.price * item.amount}</h5>
                        </div>
                        <a href="remove-item?pid=${item.product.id}" style="color: #cecece;"><i class="fas fa-trash-alt"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
              <div class="col-lg-5">

                <div class="card bg-primary text-white rounded-3">
                  <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                      <h5 class="mb-0">Order Detail</h5>
                    </div>

                    

                    <form class="mt-4" action="check-out" method="post">
                        <div data-mdb-input-init class="form-outline form-white mb-4">
                            <input name="name" type="text" id="typeName" class="form-control form-control-lg" />
                            <label class="form-label" for="typeName">Name</label>
                        </div>

                        <div data-mdb-input-init class="form-outline form-white mb-4">
                            <input name="phone" type="text" id="typePhone" class="form-control form-control-lg" minlength="0" maxlength="10" />
                            <label class="form-label" for="typePhone">Phone</label>
                        </div>

                        <div class="row mb-4">
                            <div class="col-md-6">
                                <div data-mdb-input-init class="form-outline form-white">
                                    <input name="address" type="text" id="typeAddress" class="form-control form-control-lg" />
                                    <label class="form-label" for="typeAddress">Address</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div data-mdb-input-init class="form-outline form-white">
                                    <input name="note" type="text" id="typeNote" class="form-control form-control-lg"/>
                                    <label class="form-label" for="typeNote">Note</label>
                                </div>
                            </div>
                        </div>

                        <div class="d-flex justify-content-between mb-4">
                            <p class="mb-2">Total Money</p>
                            <p class="mb-2">$${sessionScope.cart.getTotalMoney()}</p>
                        </div>

                        <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-info btn-block btn-lg">
                            <div class="d-flex justify-content-between">
                                <span>$${sessionScope.cart.getTotalMoney()}</span>
                                <span>Submit<i class="fas fa-long-arrow-alt-right ms-2"></i></span>
                            </div>
                        </button>
                    </form>


                  </div>
                </div>

              </div>

            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>
    </body>
</html>
