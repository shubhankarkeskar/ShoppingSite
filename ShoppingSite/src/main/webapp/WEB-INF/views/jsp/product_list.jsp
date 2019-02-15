<%--
  Created by IntelliJ IDEA.
  User: shubhankar
  Date: 5/2/19
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <%--This css is mostly used for icons in our web pages
    Haven't used any icons for now but may use in future--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <%--This link is used for adding JQuery functions to our page and use it--%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js">

        <%--Trying to add js file but not working--%>
    <%--<script src="/resources/js/product_sorting.js"></script>--%>

    <title>List of Products</title>
        <%--CSS for product list page--%>
    <style type="text/css">
        /*css for button tag*/
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
        }
    </style>
</head>
<body>
<form action="confirm" method="post">
    <%--This class of container provides fixed width container--%>
<div class="container">
    <div class="row">
        <div class="col-xl-12">
            <h2>List of Products</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-xl-12" style="margin-left:78.5%">
            <%--Select used for sorting purpose--%>
            <label>Sort By : </label>
                <%--Event onchange will call sortProducts() function of javascript--%>
            <select name="Sort" id="sort" onchange="sortProducts()">
                <option value="-1" selected>Select..</option>
                <option value="name" >Name</option>
                <option value="category">Category</option>
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col-xl-12">
            <%--For searching products--%>
            <label>Search : </label>
                <%--onkeyup is an event which occurs when user releases a key on keyboard
                for which we are calling the search() function of javascript to search for products
                when we enter key from keyboard--%>
            <input type="text" id="searchInput" onkeyup="searchProducts()" placeholder="Search Products">
        </div>
    </div>
    <TABLE class="table" cellpadding="15" border="1" id="product_table" style="background-color: #ffffcc;">
        <%--Can use class thead-color classes for styling purpose--%>
        <thead class="thead-dark">
        <TR>
            <TH>Product Name</TH>
            <TH>Description</TH>
            <TH>Price</TH>
            <TH>Stock</TH>
            <TH>Category</TH>
            <TH>Select</TH>
        </TR>
        </thead>
            <tbody>
            <%--We are using expression language for printing values--%>
            <%--c:if is conditional tag which evaluates its body if condition is true--%>
            <%--If product list not empty then proceed--%>
    <c:if test="${not empty productList}">
        <%--c:forEach for iterating over collection productList that we passed--%>
        <c:forEach items="${productList}" var="list">
        <TR>
            <TD>${list.productName}</TD>
            <TD>${list.productDescription}</TD>
            <TD>${list.price}</TD>
            <TD>${list.stock}</TD>
            <TD>${list.category.categoryName}</TD>
            <TD><input type="checkbox" id="productID" name="select" value="${list.productID}">Select</TD>
        </TR>
        </c:forEach>
    </c:if>
            </tbody>
    </TABLE>

    <div class="row">
        <div class="col-xl-12" style="margin-left:45%;">
            <button type="submit" id="submit_button">Submit</button>
        </div>
    </div>
</div>
</form>
<script type="text/javascript">
    /*JQuery code for submit button*/
    /*$ sign is used to define/access JQuery
    * in round brackets is the selector which here is document i.e. it specifies window. we query on it
    * then comes the action part
    * ready event is used to prevent any JQuery code to run before the document is finished loading*/
    $(document).ready(function () {
        /*here we are initially hiding our submit button*/
        $("#submit_button").hide();
    });
    $(document).ready(function(){
        $("input").click(function(){
            /*show the submit button if he selects one of the products*/
            $("#submit_button").show();
        });
    });
    //Function for sorting products by name and category
    function sortProducts() {
        /*A DOM object represents html document that is displayed
        * It has properties which allows us to access and modify the document content*/
        //Getting the select tag element by ID from document
        var selection=document.getElementById("sort").value;
        //used to display selection variable value to the console
        console.log("Selected : "+selection);
        var sortBy=selection;
        //For sorting by name
        if(sortBy=="name") {
            var table, rows, swapping, i, current_row, next_row, shouldSwap;
            //Getting the table element by id
            table = document.getElementById("product_table");
            //Initial condition
            swapping = true;
            while (swapping) {
                //Initial condition that swapping is not performed
                swapping = false;
                //Getting rows of table by tag name
                rows = table.getElementsByTagName("TR");
                /*Loop for each row except the head*/
                for (i = 1; i < (rows.length - 1); i++) {
                    //Initial condition for each row that there should be no swap
                    shouldSwap = false;
                    /*Getting the name column for comparison by tag name and its index position*/
                    current_row = rows[i].getElementsByTagName("TD")[0];
                    next_row = rows[i + 1].getElementsByTagName("TD")[0];
                    //Comparing both the rows
                    //innerHTML property is used to write dynamic html on html document
                    //here if current row is greater than next row we are identifying the the position
                    // of it using innerHTML and comparison is done via converting the name of product to lower case
                    if (current_row.innerHTML.toLowerCase() > next_row.innerHTML.toLowerCase()) {
                        //if so, mark as a swap and break the loop
                        shouldSwap = true;
                        break;
                    }
                }
                // If compared and found that rows should be interchanged
                if (shouldSwap) {
                    /*parentNode property returns the parent node of specified node as node object
                    * In html document itself is the parent node
                    * This property is read only*/
                    /*Here it is calling parent node of rows i.e. table*/
                    /*insertBefore method inserts a node as child right before an existing child
                    * Here rows[i+1] is new node(which you want to insert)
                    * and rows[i] is existing node(child node which you want to insert new node before) */
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    swapping = true;
                }
            }
        }
        //For sorting by category
        if(sortBy=="category") {
            var table, rows, swapping, i, current_row, next_row, shouldSwap;
            //Getting the table element by id
            table = document.getElementById("product_table");
            //Initial condition
            swapping = true;
            while (swapping) {
                //Initial condition that swapping is not performed
                swapping = false;
                /*Getting the name column for comparison by tag name and its index position*/
                rows = table.getElementsByTagName("TR");
                /*Loop for each row except the head*/
                for (i = 1; i < (rows.length - 1); i++) {
                    //Initial condition for each row that there should be no swap
                    shouldSwap = false;
                    /*Getting the name column for comparison by tag name and its index position*/
                    current_row = rows[i].getElementsByTagName("TD")[4];
                    next_row = rows[i + 1].getElementsByTagName("TD")[4];
                    //Comparing both the rows
                    //innerHTML property is used to write dynamic html on html document
                    //here if current row is greater than next row we are identifying the the position
                    // of it using innerHTML and comparison is done via converting the name of product to lower case
                    if (current_row.innerHTML.toLowerCase() > next_row.innerHTML.toLowerCase()) {
                        //if so, mark as a swap and break the loop
                        shouldSwap = true;
                        break;
                    }
                }
                // If compared and found that rows should be interchanged
                if (shouldSwap) {
                    /*parentNode property returns the parent node of specified node as node object
                    * In html document itself is the parent node
                    * This property is read only*/
                    /*Here it is calling parent node of rows i.e. table*/
                    /*insertBefore method inserts a node as child right before an existing child
                    * Here rows[i+1] is new node(which you want to insert)
                    * and rows[i] is existing node(child node which you want to insert new node before) */
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    swapping = true;
                }
            }
        }
    }
    //Search by name of product
    function searchProducts() {
        var search_input,filter,table,rows,current_row,search_value,i;
        //Getting the search input by id
        search_input=document.getElementById("searchInput");
        //We will filter our search by either converting string(search input value) to lower or upper case
        filter=search_input.value.toUpperCase();
        //Getting table element by id. Similar to what we did in sorting
        table = document.getElementById("product_table");
        //Now getting rows of table by tag name similar to our sorting function
        rows = table.getElementsByTagName("TR");
        for (i=0;i<rows.length;i++){
            //Getting the current row by tag name
            //position 0 for product name
            current_row=rows[i].getElementsByTagName("TD")[0];
            //Checking for that product name of current row
            if(current_row){
                //textContent property sets or returns the text content of specified node
                //It returns text content of all elements
                //innerText returns text content of all elements except script and style tag
                //checking if current row product contains only text or not (and not html)
                search_value=current_row.textContent || current_row.innerText;
                //indexOf method returns the position of first occurrence of our filter in search value of table
                //returns -1 if search does not happen
                //If the current search value does not match input from user
                if (search_value.toUpperCase().indexOf(filter)>-1){
                    // if found then changing css of rows of product table
                    rows[i].style.display="";
                }else {
                    // if not found don't display any rows of product table
                    rows[i].style.display="none";
                }
            }
        }
    }
</script>
</body>
</html>