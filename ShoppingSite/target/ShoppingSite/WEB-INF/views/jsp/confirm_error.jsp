<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 11/2/19
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        p{
            color: red;
        }
    </style>
</head>
<body onload="setTimeout('redirectNew()',5000)">

    <p>ERROR: YOU HAVE NOT SELECTED ANY PRODUCTS FROM THE LIST
        <br>
        PLEASE SELECT THE PRODUCTS AGAIN!!
        <br>
        <br>
        PLEASE WAIT... WE ARE REDIRECTING TO THE PRODUCTS PAGE
    </p><br>

<script type="text/javascript">
    function redirectNew() {
        window.location="products";
    }
</script>
</body>
</html>
