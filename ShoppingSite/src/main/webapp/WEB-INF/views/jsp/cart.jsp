<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 8/2/19
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Title</title>
    <%--CSS for cart page--%>
    <style type="text/css">
        /*css attributes set for submit button*/
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
        }
        button[id=remove_from_cart]{
            background-color: red;
            color: white;
            padding: 14px 20px;
        }
    </style>
</head>
<body>
<form action="checkoutPage" method="post">
    <%--This class of container provides fixed width container--%>
    <div class="container">
        <div class="row">
            <div class="col-xl-12">
                <h2>Cart</h2>
            </div>
        </div>

        <div class="row">
            <div class="col-xl-6">
                <%--Cart Items--%>
        <TABLE class="table" cellpadding="15" border="1" id="product_table" style="background-color: #ffffcc;">
            <thead class="thead-dark">
            <TR>
                <TH>Product ID</TH>
                <TH>Product Name</TH>
                <TH>Price</TH>
                <TH>Stock</TH>
                <TH>Quantity</TH>
                <TH>Select For Removal</TH>
            </TR>
            </thead>
            <%--We are using expression language for printing values--%>
            <%--c:if is conditional tag which evaluates its body if condition is true--%>
            <%--If cart is not empty then proceed--%>
            <c:if test="${not empty cart_list}">
                <%--For outer list--%>
                <c:forEach items="${cart_list}" var="outer_cart_list">
                    <%--For inner list--%>
                    <c:forEach items="${outer_cart_list}" var="inner_cart_list">
                        <TR>
                            <TD>${inner_cart_list.product.productID}</TD>
                            <TD>${inner_cart_list.product.productName}</TD>
                            <TD>${inner_cart_list.product.price}</TD>
                            <TD>${inner_cart_list.product.stock}</TD>
                            <TD>${inner_cart_list.quantity}</TD>
                            <TD><button type="button" id="remove_from_cart" onclick="location.href='removeItems?cartProductID=${inner_cart_list.product.productID}'">Remove From Cart</button></TD>
                            <%--<TD><input type="radio" name="cartProductID" value="${inner_cart_list.product.productID}"></TD>--%>
                        </TR>
                        </c:forEach>
                </c:forEach>
            </c:if>
        </TABLE>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-12">
                <button type="submit" id="checkout" onclick="checkoutAlert()">Checkout</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    function checkoutAlert() {
        alert("You have successfully placed your order");
    }
    $(document).ready(function () {
       $("#remove_from_cart").click(function () {
            alert("Product is removed from cart");
       });
    });
</script>
</body>
</html>
