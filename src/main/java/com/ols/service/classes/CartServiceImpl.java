package com.ols.service.classes;

import com.ols.dao.interfaces.CartDAO;
import com.ols.model.ProductOrder;
import com.ols.service.interfaces.CartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
/*Class - CartServiceImpl
* Implements - CartService
* Service layer for Cart*/
public class CartServiceImpl implements CartService {
    @Autowired
    public CartDAO cartDAO;

    public static final Logger logger=Logger.getLogger(CartServiceImpl.class);
    /*Method - isAvailable
    * Return type - boolean
    * Parameters - Array of product id & quantity
    * Functionality - Gives call to isAvailable method of CartDAO with same parameters & gets a boolean value*/
    @Override
    public boolean isAvailable(String[] productID, String[] quantity) throws NumberFormatException, SQLException,NullPointerException {
        logger.info("INSIDE CART SERVICE : isAvailable");
        return cartDAO.isAvailable(productID,quantity);
    }
    /*Method - getCart
    * Return type - List of Product Order
    * Parameters - ProductId & Quantity
    * Functionality - Gives call to getCart method of CartDAO with same parameters & gets a list of ProductOrder*/
    @Override
    public List<ProductOrder> getCart(String productId, String quantity) throws NullPointerException,SQLException{
        logger.info("INSIDE CART SERVICE : getCart");
        return cartDAO.getCart(productId,quantity);
    }
    /*Method - removeItem
    * Return type - Array of String
    * Parameters - Array of cart(product,quantity) & index from which it should be removed
    * Functionality - Removing items from the cart*/
    @Override
    public String[] removeItem(String[] cart, int index) {
        logger.info("INSIDE CART SERVICE : removeItem");
        logger.info("REMOVING ITEM");
        //Checking if cart is null or index less than 0 or index greater than length
        if (cart==null || index<0 || index>cart.length){
            logger.warn("CART EMPTY");
            return cart;    //If true return cart
        }
        //Creating a temporary array of string
        String[] temp_array=new String[cart.length-1];
        for (int i=0,k=0;i<cart.length;i++){
            //If i is equal to the index then continue
            if (i==index){
                continue;
            }
            //Adding the elements in temporary array excluding the one which needs to be removed
            temp_array[k++]=cart[i];
        }
        return temp_array;
    }
    /*Method - addItem
    * Return type - Array of string
    * Parameters - Array of cart(product,quantity), new length for array, item to be added
    * Functionality - Adding items to the cart*/
    @Override
    public String[] addItem(String[] cart,int newLength,String item) {
        logger.info("INSIDE CART SERVICE : addItem");
        logger.info("ADDING ITEM");
        if (newLength==0){
            newLength=1;
        }
        //Creating a copy of array with new length
        cart = Arrays.copyOf(cart,(cart.length+newLength));
        //Appending the item at the end
        cart[cart.length-1]=item;
        return cart;
    }
}
