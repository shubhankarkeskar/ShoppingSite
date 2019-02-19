<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 30/1/19
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <%--JSP Tag Library - core tags (mostly used) --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%--This is a responsive meta tag
        This means the browser will render the width of the page at the width
        of its own screen. Avoids unecessary zoom out
        width=device-width sets the width of the page to follow screen width
        initial-scale sets initial zoom level when page is first loaded by browser--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--This is used for building responsive sites.
    So we need to add this css link of BootstrapCDN.--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <%--This css is mostly used for icons in our web pages
    Haven't used any icons for now but may use in future--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <%--Trying to include css file from resources folder
        but its not working.--%>
        <%--<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>" type="text/css">--%>
    <%--<link rel="stylesheet" href="/resources/css/login.css" type="text/css">--%>
    <title>Login</title>

        <%--CSS for login page--%>
    <style type="text/css">
        /*css attributes set for input tags*/
        input[type=email], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        /*css attributes set for button(submit,using tag name)*/
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            width: 100%;
        }
        /*css attributes set for clear button(using its class)*/
        .clear_button {
            width: auto;
            padding: 10px 18px;
            background-color: #f44336;
        }
        /*css attributes set for container-fluid*/
        .container-fluid {
            align: right;
            margin-left: 60%;
            margin-top: 10%;
            border: 3px solid black;
            width: 35%;
            border-radius:3px;
            box-shadow: 12px 12px 5px;
        }
        /*css attributes set for a tag(register link)*/
        a[id=register]
        {
            color: red;
        }
        /*css attributes set for p tag(error purpose)*/
        p{
            color: red;
        }
    </style>
</head>
<body>
<form action="sign_in" method="post">
    <%--This is used for wrapping login page contents into a single container.
    This class of div tag i.e. container-fluid provides full width container
    spanning the entire width of viewport.(It utilizes entire width of screen)--%>
    <div class="container-fluid">
        <%--Bootstrap grid allows 12 columns across the page
        You can add columns as per your requirement--%>
        <%--Here we are adding an entire row for title of page--%>
        <div class="row">
            <%--Here xl is the grid class for used for laptops,desktops etc.
            You can use other classes like xs,sm,md,lg for different devices--%>
            <div class="col-xl-12">
                <h2>LOGIN</h2>
            </div>
        </div>

        <br>
            <%--Error message for wrong login or password--%>
        <p>${login_error_message}</p><br>
            <%--Again adding an entire row for two contents i.e. label and input for User Name--%>
        <div class="row">
            <%--Utilizing entire column for it--%>
            <div class="col-xl-12">
                <label><b>User Name as Email Id</b></label>
                <%--type is email and we have the regex pattern for validating email id,
                required is used for mandatory fields--%>
                <input type="email" placeholder="Enter Email Id" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" name="userName" required>
            </div>
        </div>

        <div class="row">
            <div class="col-xl-12">
                <label><b>Password</b></label>
                <%--typ is password so you will not be able to see actual letters (if explicitly done)
                pattern for password i.e. should contain atleast 8 characters,1 lower case, 1 upper case and 1 number--%>
                <input type="password" placeholder="Enter Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" name="userPassword" required>
            </div>
        </div>

        <div class="row">
            <div class="col-xl-12">
                <button type="submit" id="user_login">Login</button>
            </div>
        </div>

        <br>
        Don't have an Account?<a href="register" id="register">Register</a>
        <br>

        <div class="row">
            <div class="col-xl-12">
                <button type="reset" class="clear_button">Clear</button>
            </div>
        </div>

    </div>
</form>
</body>
</html>