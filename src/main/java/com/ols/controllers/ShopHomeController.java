package com.ols.controllers;

import com.ols.model.Product;
import com.ols.model.ProductOrder;
import com.ols.service.interfaces.CartService;
import com.ols.service.interfaces.CheckoutService;
import com.ols.service.interfaces.HomeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*Class - ShopHomeController
* Requests for shop home related operations will be handled by this controller*/
@Controller
@RequestMapping("home")
public class ShopHomeController {
    @Autowired
    public HomeService homeService;
    @Autowired
    public CartService cartService;
    @Autowired
    public CheckoutService checkoutService;

    public static final Logger logger=Logger.getLogger(ShopHomeController.class);
    /*Method - productList
    * This method redirects the view to the default view for products list*/
    @RequestMapping(value = "products", method = RequestMethod.GET)
    public RedirectView productsList(){
        logger.info("INTO PRODUCT LIST. REDIRECTING VIEW");
        return new RedirectView("/home/productsList",true);
    }
    /*Method - confirmation
    * This method will render the view of confirmation after the user has selected the products*/
    @RequestMapping(value = "confirm",method = RequestMethod.POST)
    public ModelAndView confirmation(HttpServletRequest request) throws Exception {
        logger.info("INSIDE CONFIRMATION");
        //Getting selected products from the view
        String[] productID = request.getParameterValues("select");

        ModelAndView modelAndView=new ModelAndView();
        //Checking if user has selected a product or not
        if (productID==null){
            logger.warn("NO PRODUCTS SELECTED");
            modelAndView.setViewName("confirm_error");
        }
        else {
            List<List<Product>> list=new ArrayList<>();
            List<Product> productList;
            //Getting details of each product selected
            for (int i=0;i<productID.length;i++) {
                productList = homeService.getSelectedProducts(productID[i]);
                //Adding those details in the list
                list.add(productList);
            }
            logger.info("SELECTED PRODUCTS & ADDED TO THE LIST");
            modelAndView.setViewName("confirmation");
            //Adding the list object to the model & view
            modelAndView.addObject("selectedProductList",list);
        }

        return modelAndView;
    }
    /*Method - addToCart
    * This method will handle the operations when the user has confirmed the products to be added to the cart*/
    @RequestMapping(value = "cart",method = RequestMethod.POST)
    public ModelAndView addToCart(HttpServletRequest request, HttpSession session) throws SQLException,NumberFormatException,NullPointerException {
        logger.info("INSIDE ADD TO CART");
        //Getting productID & quantity
        String[] quantity = request.getParameterValues("quantity");
        String[] productID = request.getParameterValues("productID");
        boolean isAvailable;
        //Checking if product is available or not
        isAvailable = cartService.isAvailable(productID, quantity);
        ModelAndView modelAndView=new ModelAndView();
        //If available then check session attributes
        if (isAvailable){
            logger.info("PRODUCT IS IN STOCK");
            //If session attribute is null then set both productID & quantity
            if (session.getAttribute("shopping_cart_productId")==null) {
                logger.info("CART IS EMPTY");
                session.setAttribute("shopping_cart_productId", productID);
                session.setAttribute("shopping_cart_quantity", quantity);
            }else {
                logger.info("CART HAS PREVIOUSLY SELECTED ITEMS");
                //Else it will add to existing session
                String[] cartProducts = (String[]) session.getAttribute("shopping_cart_productId");
                String[] cartQuantity = (String[]) session.getAttribute("shopping_cart_quantity");
                for (int i=0;i<productID.length;i++){
                    cartProducts = cartService.addItem(cartProducts, cartProducts.length, productID[i]);
                    cartQuantity = cartService.addItem(cartQuantity, cartQuantity.length, quantity[i]);
                }
                //After adding new elements set the session attribute
                session.setAttribute("shopping_cart_productId", cartProducts);
                session.setAttribute("shopping_cart_quantity", cartQuantity);
                logger.info("ADDED NEW PRODUCTS TO CART");
            }
            modelAndView.setViewName("redirect:/home/back_home");
            return modelAndView;
        }
        else {
            logger.error("PRODUCT IS NOT AVAILABLE");
            modelAndView.setViewName("availability_error");
            return modelAndView;
        }
    }
    /*Method - removeCart
    * This method will handle operations after checkout has been done*/
    @RequestMapping(value = "remove_cart",method = RequestMethod.GET)
    public ModelAndView removeCart(HttpSession session){
        logger.info("INSIDE REMOVE CART");
        session.removeAttribute("shopping_cart_productId");
        session.removeAttribute("shopping_cart_quantity");
        return new ModelAndView("redirect:/home/back_home");
    }
    /*Method - backToHome
    * This method will redirect to Home page. Calls through this will go to home*/
    @RequestMapping(value = "back_home",method = RequestMethod.GET)
    public ModelAndView backToHome(){
        logger.info("REDIRECTING TO HOME");
        return new ModelAndView("redirect:/backToHome");
    }
    /*Method - defaultView
    * This method gets the list of products & renders it to Product List view*/
    @RequestMapping(value = "/productsList",method = RequestMethod.GET)
    public ModelAndView defaultView() throws Exception{
        logger.info("INSIDE DEFAULT VIEW OF PRODUCT LIST");
        List<Product> list;
        list = homeService.getProductList();
        ModelAndView modelAndView=new ModelAndView("product_list");
        modelAndView.addObject("productList",list);
        return modelAndView;
    }
    /*Method -addedToCart
    * This method is used for handling the addition of products in cart*/
    @RequestMapping(value = "added_cart",method = RequestMethod.GET)
    public ModelAndView addedToCart(HttpSession session, ModelMap map) throws Exception{
        logger.info("INSIDE CART");
        //Getting session attributes
        String[] productIDs = (String[]) session.getAttribute("shopping_cart_productId");
        String[] quantity = (String[]) session.getAttribute("shopping_cart_quantity");
        //If cart is empty
        if (session.getAttribute("shopping_cart_productId")==null || productIDs.length==0){
            logger.error("CART IS EMPTY (FOR FIRST CLICK AFTER LOGIN)");
            return new ModelAndView("cart_error");
        }else {
            logger.info("CART IS NOT EMPTY");
            List<ProductOrder> productList;
            List<List<ProductOrder>> cart=new ArrayList<>();
            //Getting the confirmed products
            for (int i = 0; i < productIDs.length; i++) {
                productList = cartService.getCart(productIDs[i], quantity[i]);
                cart.add(productList);  //Adding it to the list
            }
            logger.info("GOT CART ITEMS IN LIST");
            map.addAttribute("cart_list", cart);
            //map.addAttribute("quantity", quantity);
            return new ModelAndView("cart", map);
        }
    }
    /*Method - placeOrder
    * This method handles the request to checkout page */
    @RequestMapping(value = "checkoutPage",method = RequestMethod.POST)
    public ModelAndView placeOrder(HttpSession session,ModelMap modelMap) throws Exception{
        logger.info("INSIDE PLACE ORDER");
        //Getting session attributes
        String userName=(String) session.getAttribute("userName");
        String[] productIDs = (String[]) session.getAttribute("shopping_cart_productId");
        String[] quantity = (String[]) session.getAttribute("shopping_cart_quantity");
        int insertCount=1;  //initializing insert count
        int[] confirm = new int[productIDs.length];
        logger.info("CONFIRMING THE ORDER");
        //Confirming the order which adds the details to the database
        for (int i=0;i<productIDs.length;i++){
            confirm[i] = checkoutService.confirmOrder(productIDs[i], quantity[i], userName, insertCount);
            insertCount++;
        }
        List<ProductOrder> orderList;
        //Checks if all products successfully added or not
        boolean confirmFlag;
        confirmFlag = checkoutService.checkConfirmation(confirm);
        int totalPrice;
        //If it is true then it will get the details of that order & will also calculate total price
        if (confirmFlag){
            logger.info("ALL PRODUCTS ARE CONFIRMED");
            orderList=checkoutService.getOrderDetails(userName);
            totalPrice=checkoutService.totalAmount(orderList);
            modelMap.addAttribute("order",orderList);
            modelMap.addAttribute("totalPrice",totalPrice);
            return new ModelAndView("checkout",modelMap);
        }else {
            logger.error("PRODUCTS ARE NOT CONFIRMED");
            return new ModelAndView("confirm_error");
        }
    }
    /*Method - removeItems
    * This method handles the request for removing items from the cart*/
    @RequestMapping(value = "removeItems",method = RequestMethod.GET)
    public ModelAndView removeItems(HttpServletRequest request,HttpSession session){
        logger.info("INSIDE REMOVE ITEMS");
        //Getting session attributes
        String productId=request.getParameter("cartProductID");
        String[] productIDs = (String[]) session.getAttribute("shopping_cart_productId");
        String[] quantity = (String[]) session.getAttribute("shopping_cart_quantity");
        int removal_index=0;//Index from which it should be removed
        for (int i=0;i<productIDs.length;i++){
            //If product to be removed
            if (productIDs[i].equals(productId)){
                removal_index=i;
                productIDs=cartService.removeItem(productIDs,i);
            }
        }
        quantity=cartService.removeItem(quantity,removal_index);
        logger.info("PRODUCT/S REMOVED");
        //Setting the session attributes after removal
        session.setAttribute("shopping_cart_productId",productIDs);
        session.setAttribute("shopping_cart_quantity",quantity);
        return new ModelAndView("redirect:/home/added_cart");
    }

}
