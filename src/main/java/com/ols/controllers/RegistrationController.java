package com.ols.controllers;

import com.ols.exception.UserNameDuplicateException;
import com.ols.model.Customer;
import com.ols.service.interfaces.RegisterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.sql.SQLException;

/*Class - Registration Controller
* Requests for registration related operations are handled by this controller */
@Controller
public class RegistrationController {
    @Autowired
    public RegisterService registerService;

    private static final Logger logger=Logger.getLogger(RegistrationController.class);

    /*Method - saveUser
    * This method will add the user in the database & will render the login view
    * If validations are false then it will redirect to the same view with error message*/
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute Customer customer) throws SQLException {
        logger.info("INTO ADD USER");
        String password=customer.getUserPassword();
        String retype_password=customer.getRetypePassword();

        boolean validPassword=registerService.validatePassword(password,retype_password);

        ModelAndView modelAndView = new ModelAndView();
        if (validPassword){
            logger.info("PASSWORD IS VALID");
            try {
                registerService.register(customer);
            }catch (SQLException s){
                logger.info("SQL EXCEPTION IS THROWN");
                throw new UserNameDuplicateException("User name is duplicate");
            }
            modelAndView.setViewName("login");
            return modelAndView;
        }
        else {
            modelAndView.setViewName("redirect:/wrong_registration");
            return modelAndView;
        }
    }
    /*Method - registration
    * This method is used for handling wrong registration inputs &
    * will redirect to registration with the respective error message*/
    @RequestMapping("/wrong_registration")
    public ModelAndView passwordError(ModelMap modelMap){
        logger.error("Wrong registration : PASSWORD ERROR : PASSWORDS DOES NOT MATCH ");
        ModelAndView modelAndView=new ModelAndView("registration");
        modelMap.addAttribute("password_error","Passwords does not match");
        return modelAndView;
    }
    @RequestMapping("/register_error")
    public ModelAndView userNameError(ModelMap modelMap){
        logger.error("Wrong registration : DUPLICATE ERROR : USER NAME ALREADY EXISTS");
        ModelAndView modelAndView=new ModelAndView("registration");
        modelMap.addAttribute("username_error","User Name already exists");
        return modelAndView;
    }

}
