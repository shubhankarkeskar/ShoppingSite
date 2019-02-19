<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 30/1/19
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
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

    <title>Registration</title>
        <%--CSS for registration page--%>
    <style type="text/css">
        /*css attributes set for input tags*/
        input[type=text], input[type=password] {
            width: auto;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid black;
            box-sizing: border-box;
        }
        /*css attributes set for button(submit,using tag name)*/
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            margin: 5px 0;
        }
        /*css attributes set for clear button(using its class)*/
        .clearButton {
            color: white;
            padding: 10px 15px;
            background-color: #f44336;
            margin: 5px 0;
        }
        /*css attributes set for container-fluid*/
        .container-fluid {
            align: center;
            margin-left: 30%;
            margin-top: 2%;
            border: 3px solid black;
            width: 45%;
            border-radius:3px;
            box-shadow: 12px 12px 5px;
        }
        /*css attributes set for a tag(register link)*/
        p{
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid">
<form id="userForm" action="addUser" method="post">
    <div class="row">
        <div class="col-xl-12">
            <h2>REGISTRATION</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-xl-12">
            <label><b>Name </b></label>
            <input type="text" placeholder="Enter Name" name="fullName">
        </div>
    </div>

    <div class="row">
        <div class="col-xl-12">
            <label><b>Address </b></label>
            <input type="text" placeholder="Enter Address" name="address">
        </div>
    </div>

    <div class="row">
        <div class="col-xl-12">
            <label><b>Contact </b></label>
            <%--Pattern for mobile number should be 10 digit number
            We can give additional pattern for numbers starting with specific digit--%>
            <input type="text" placeholder="Enter valid mobile number" pattern="[0-9]{10}" name="contact" required>
        </div>
    </div>

    <div class="row">
        <div class="col-xl-12">
            <label><b>Birth Date </b></label>
            <%--type date is used for selecting date from calender--%>
            <input type="date" placeholder="Enter Birth Date" name="birthDate" required>
        </div>
    </div>

    <%--<label><b>Profile Image </b></label>
    <br>
    <input type="file" name="image" accept="image/*">
    <br>--%>
    <br>
    <p>${username_error}</p>
    <div class="row">
        <div class="col-xl-12">
            <label><b>User Name as Email Id</b></label>
            <input type="email" placeholder="Enter Email Id" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" name="userName" required>
        </div>
    </div>

    <font size="1.9`" color="red">(* Note: Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters)</font>
    <div class="row">
        <div class="col-xl-12">
            <label><b>Password</b><br></label>
            <input type="password" placeholder="Enter Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" name="userPassword" required>
        </div>
    </div>

    <div class="row">
        <div class="col-xl-12">
            <label><b>Re-Type Password</b></label>
            <input type="password" placeholder="Enter Password Again" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" name="retypePassword" required>
        </div>
    </div>
    <%--For error if both passwords does not match--%>
    <p>${password_error}</p>
    <div class="row">
        <div class="col-xl-12">
            <button type="submit" <%--onclick="successfulRegister()"--%>>Submit</button>
            <button type="reset" class="clearButton">Clear</button>
        </div>
    </div>
</form>
</div>
<%--<script type="text/javascript">
    function successfulRegister() {
        alert("Registration Successful");
    }
</script>--%>
</body>
</html>
