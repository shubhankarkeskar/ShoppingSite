<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 30/1/19
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registered</title>
    <style type="text/css">
        p{
            color: red;
        }
    </style>
</head>
<body onload="setTimeout('backToHome()',6000)">

<p>ERROR: PRODUCT NOT AVAILABLE OR QUANTITY OF PRODUCTS MUST BE LESS THAN OR EQUAL TO AVAILABLE STOCK
<br>
    PLEASE CHECK THE AVAILABILITY OF PRODUCTS AGAIN!!
    <br>
    <br>
    PLEASE WAIT... WE ARE REDIRECTING TO THE PRODUCTS
</p><br>

<script type="text/javascript">
    function backToHome() {
        window.location="products";
    }
</script>
</body>
</html>
