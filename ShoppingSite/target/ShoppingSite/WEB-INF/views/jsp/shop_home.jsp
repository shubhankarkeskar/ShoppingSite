<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 31/1/19
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>HOME</title>
</head>
<body>
<form method="post">
<%--This is used for wrapping login page contents into a single container.
   This class of div tag i.e. container-fluid provides full width container
   spanning the entire width of viewport.(It utilizes entire width of screen)--%>
<div class="container-fluid">

    <div class="row">
        <div class="col-xl-12">
            <h1><b>S.K. Market</b></h1>
        </div>
    </div>

    <div class="row">
        <%--style margin-left is used for aligning the a tag to the right--%>
        <div class="col-xl-12" style="margin-left:84%;">
            <%--Sign out link--%>
            <h6><b><a href="signedOut" id="sign_out">Sign Out?</a></b></h6>
        </div>
    </div>
    <%--Can use font tag right now but its deprecated
    Other way is css--%>
    <b><font color="black">
        <div class="row">
            <div class="col-xl-12">
                <%--navbar class is used for navigation bar
                We can extend as per our needs
                Can also add drop down list using li tag with class as dropdown--%>
                <%--standard navbar followed by responsive collapsing class
                navbar-expand-sm(small screen,similar class of grids)
                Can use any background color classes. Here we are using bg-dark--%>
                <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                    <%--We add menu items to navbar using ul(unordered list) tag and li(list) tag--%>
                    <ul class="navbar-nav">
                        <%--nav-item is the class for adding item to our navbar--%>
                        <li class="nav-item">
                            <%--If it is a link then we use nav-link class in a tag--%>
                            <a class="nav-link" href="${pageContext.request.contextPath}/home/products" id="products">Products</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/home/added_cart" id="cart">Cart</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </font></b>
    <h4>Welcome <%=session.getAttribute("userName")%></h4>
    <div class="row">
        <div class="col-xl-12" style="margin-top:2%;">
            <div id='common'>
            </div>
        </div>
    </div>

</div>
</form>
</body>
</html>
