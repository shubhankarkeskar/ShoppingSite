<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 7/2/19
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Product Confirmation</title>
    <style type="text/css">
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
        }
    </style>
</head>
<body>
<form action="cart" method="post">
    <div class="container">
        <div class="row">
            <div class="col-xl-12">
                <h2>Product Confirmation</h2>
            </div>
        </div>

        <TABLE class="table" cellpadding="15" border="1" id="product_table" style="background-color: #ffffcc;">
            <thead class="thead-dark">
            <TR>
                <TH>Product ID</TH>
                <TH>Product Name</TH>
                <TH>Price</TH>
                <TH>Stock</TH>
                <TH>Enter Quantity</TH>
                <TH style="display: none"></TH>
            </TR>
            </thead>
            <c:if test="${not empty selectedProductList}">
                <c:forEach items="${selectedProductList}" var="outerlist">
                    <c:forEach var="inner" items="${outerlist}">
                    <TR>
                        <TD>${inner.productID}</TD>
                        <TD>${inner.productName}</TD>
                        <TD>${inner.price}</TD>
                        <TD id="stock">${inner.stock}</TD>
                        <TD><input type="text" name="quantity" id="qty" pattern="[1-9]{1,2}" required></TD>
                        <TD style="display: none"><input type="text" name="productID" value="${inner.productID}"></TD>
                    </TR>
                    </c:forEach>
                </c:forEach>
            </c:if>
        </TABLE>
        <div class="row">
            <div class="col-xl-12" style="margin-left:45%;">
                <button type="submit" onclick="addToCartAlert()">Add To Cart</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    function addToCartAlert() {
        var quantity=document.getElementById("qty").value;
        var stock=document.getElementById("stock").value;
        if (quantity<stock) {
            alert("Products added to cart successfully!!");
        }
    }
</script>
</body>
</html>
