<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 8/2/19
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Checkout</title>
    <style type="text/css">
        button {
            width: auto;
            padding: 10px 18px;
            background-color: #f44336;
        }
    </style>
</head>
<body>
<form action="remove_cart" method="get">
    <div class="container">
        <div class="row">
            <div class="col-xl-12">
                <h2>Order Details</h2>
            </div>
        </div>
        <TABLE class="table" cellpadding="15" border="1" id="product_table" style="background-color: #ffffcc;">
            <thead class="thead-dark">
            <TR>
                <TH>Order ID</TH>
                <TH>Product Name</TH>
                <TH>Price</TH>
                <TH>Quantity</TH>
                <TH>Product Total</TH>
            </TR>
            </thead>
            <c:if test="${not empty order}">
                <c:forEach items="${order}" var="order">
                    <TR>
                        <TD>${order.order.orderID}</TD>
                        <TD>${order.product.productName}</TD>
                        <TD>${order.product.price}</TD>
                        <TD>${order.quantity}</TD>
                        <TD>${order.productTotal}</TD>
                    </TR>
                </c:forEach>
            </c:if>
        </TABLE>

        <div class="row">
            <div class="col-xl-12" style="margin-left: 50%">
                <h2>Total : ${totalPrice}</h2>
            </div>
        </div>

        <div class="row">
            <div class="col-xl-12" style="margin-left:45%;">
                <button type="submit" id="submit_button">Back To Home</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
