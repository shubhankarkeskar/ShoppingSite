package com.ols.service.classes;

import com.ols.dao.interfaces.CheckoutDAO;
import com.ols.dao.interfaces.ShoppingSiteDAO;
import com.ols.model.ProductOrder;
import com.ols.service.interfaces.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/*Class - CheckoutServiceImpl
* Implements - CheckoutService
* Service layer for Checkout*/
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    public ShoppingSiteDAO siteDAO;
    @Autowired
    public CheckoutDAO checkoutDAO;

    //Declaring order_time class member
    String order_time;
    //Setter method for setting order_time
    public void setOrder_time(){
        //Creating Date object
        Date date = new Date();
        //Formatting the date according to the database field for it
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order_time = simpleDateFormat.format(date);
    }
    // Getter method for order_time
    public String getOrder_time(){
        return order_time;
    }
    /*Method - confirmOrder
    * Return type - integer
    * Parameters - ProductID, Quantity, User Name & insert count
    * Functionality - Gives call to the confirmOrder of siteDAO using same parameters & gets an integer value*/
    @Override
    public int confirmOrder(String productId, String quantity, String userName,int insertCount) {
        //Setting order_time only once
        if (insertCount<=1){
            setOrder_time();
        }
        return siteDAO.confirmOrder(productId,quantity,userName,insertCount,getOrder_time());
    }
    /*Method - checkConfirmation
    * Return type - boolean
    * Parameters - Array of confirmation
    * Functionality - Checks if all the products in an order are confirmed or not*/
    @Override
    public boolean checkConfirmation(int[] confirm) {
        //Initial condition setting confirm flag as true
        boolean confirmFlag=true;
        for (int i=0;i<confirm.length;i++){
            //If a value is 0 then set the flag as false & break the loop
            if (confirm[i]==0){
                confirmFlag=false;
                break;
            }
        }
        return confirmFlag;
    }
    /*Method - getOrderDetails
    * Return type - List of Product Order
    * Parameters - User Name
    * Functionality - Gives a call to getOrderDetails of checkoutDAO with same parameters & gets a list of ProductOrder*/
    @Override
    public List<ProductOrder> getOrderDetails(String userName) {
        return checkoutDAO.getOrderDetails(userName);
    }
    /*Method - totalAmount
    * Return type - integer
    * Parameters - List of ProductOrder
    * Functionality - Calculates sum of the price of products in the cart(including the quantity of products)*/
    @Override
    public int totalAmount(List<ProductOrder> list) {
        //Initial condition sum as 0
        int sum=0;
        //Iterating through the list
        for (ProductOrder po:list){
            sum+=po.getProductTotal();  //Getting each productTotal i.e.(price*quantity) & adding it to the sum
        }
        return sum;
    }
}
