<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 12/2/19
  Time: 12:01 PM
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
<body onload="setTimeout('home()',5000)">
<p>ERROR: CART IS EMPTY
    <br>
    <br>
    PLEASE WAIT... WE ARE REDIRECTING TO THE HOME PAGE
</p><br>
<script type="text/javascript">
    function home() {
        window.location="back_home";
    }
</script>
</body>
</html>
