function sortProducts() {
    var selection=document.getElementById("sort").value;
    console.log("Selected : "+selection);
    var sortBy=selection;

    if(sortBy=="name") {
        var table, rows, swapping, i, x, y, shouldSwap;
        table = document.getElementById("product_table");
        swapping = true;
        while (swapping) {
            //Initial condition that swapping is not performed
            swapping = false;
            rows = table.getElementsByTagName("TR");
            /*Loop through all table rows (except the
            first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //Initial condition for each row that there should be no swap
                shouldSwap = false;
                /*Get the two elements you want to compare,
                one from current row and one from the next:*/
                x = rows[i].getElementsByTagName("TD")[0];
                y = rows[i + 1].getElementsByTagName("TD")[0];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwap = true;
                    break;
                }
            }
            if (shouldSwap) {
                /*If a switch has been marked, make the switch
                and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                swapping = true;
            }
        }
    }
    if(sortBy=="category") {
        var table, rows, swapping, i, x, y, shouldSwap;
        table = document.getElementById("product_table");
        swapping = true;
        while (swapping) {
            //start by saying: no switching is done:
            swapping = false;
            rows = table.getElementsByTagName("TR");
            /*Loop through all table rows (except the
            first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwap = false;
                /*Get the two elements you want to compare,
                one from current row and one from the next:*/
                x = rows[i].getElementsByTagName("TD")[4];
                y = rows[i + 1].getElementsByTagName("TD")[4];
                //check if the two rows should switch place:
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwap = true;
                    break;
                }
            }
            if (shouldSwap) {
                /*If a switch has been marked, make the switch
                and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                swapping = true;
            }
        }
    }
}