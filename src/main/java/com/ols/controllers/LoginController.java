package com.ols.controllers;

import com.ols.model.Login;
import com.ols.service.interfaces.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/*Class - LoginController
* Requests for login & related pages are handled by this controller*/
@Controller
public class LoginController {

    @Autowired
    public LoginService loginService;

    public static final Logger logger=Logger.getLogger(LoginController.class);

    /*Method - login
    * Initial method when application starts. Will render the login page*/
    @RequestMapping("/")
    public ModelAndView login() {
        logger.info("INITIAL PAGE : LOGIN");
        return new ModelAndView("login");
    }
    /*Method - register
    * This method will render the registration page*/
    @RequestMapping("/register")
    public ModelAndView register() {
        logger.info("CLICKED REGISTRATION LINK");
        return new ModelAndView("registration");
    }
    /*Method - signIn
    * This method will render shop home if user is valid otherwise
    * it will render the same view with an error message*/
    @RequestMapping(value = "sign_in",method = RequestMethod.POST)
    public ModelAndView signIn(@ModelAttribute Login login, HttpSession session) throws SQLException{
        logger.info("INTO SIGN IN");
        boolean valid=true;
        try {
            valid=loginService.validate(login);
        }catch (Exception e){
            logger.error("EXCEPTION THROWN");
            throw e;
        }
        //String fullName=loginService.getFullName(login);
        ModelAndView modelAndView=new ModelAndView();
        if (valid){
            logger.info("USER CREDENTIALS VALIDATED");
            //modelAndView.addObject("userName",login.getUserName());
            session.setAttribute("userName",login.getUserName());
            modelAndView.setViewName("shop_home");
            return modelAndView;
        }
        else {
            logger.error("WRONG USER CREDENTIALS");
            modelAndView.setViewName("redirect:/wrong_login");
            return modelAndView;
        }
    }
    /*Method - redirect
    * This method will set error object for login & then render it*/
    @RequestMapping("/wrong_login")
    public ModelAndView redirect() {
        logger.error("INTO WRONG LOGIN. REDIRECTING TO LOGIN WITH ERROR");
        ModelAndView modelAndView=new ModelAndView("login");
        modelAndView.addObject("login_error_message","Invalid User Name or Password");
        System.out.println("wrong login");
        //modelMap.addAttribute("login_error_message","Invalid User Name or Password");
        return modelAndView;
    }
    /*Method - backToHome
    * This method will render the home page*/
    @RequestMapping(value = "backToHome",method = RequestMethod.GET)
    public ModelAndView backToHome(HttpSession session){
        logger.info("BACK TO HOME");
        session.getAttribute("userName");
        return new ModelAndView("shop_home");
    }
    /*Method - signedOut
    * This method will render the default view after removing session attributes & invalidating it*/
    @RequestMapping(value = "/signedOut",method = RequestMethod.GET)
    public ModelAndView signedOut(HttpSession session){
        logger.info("INTO SIGN OUT");
        session.removeAttribute("shopping_cart_productId");
        session.removeAttribute("shopping_cart_quantity");
        session.removeAttribute("userName");
        session.invalidate();
        logger.info("SESSION INVALIDATED");
        ModelAndView model = new ModelAndView("redirect:/");
        return model;
    }
}
