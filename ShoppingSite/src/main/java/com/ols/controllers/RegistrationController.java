package com.ols.controllers;

import com.ols.model.Customer;
import com.ols.service.interfaces.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/*Class - Registration Controller
* Requests for registration related operations are handled by this controller */
@Controller
public class RegistrationController {

    @Autowired
    public RegisterService registerService;
    /*Method - saveUser
    * This method will add the user in the database & will render the login view
    * If validations are false then it will redirect to the same view with error message*/
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute Customer customer, HttpServletRequest request){

        String password=customer.getUserPassword();
        String retype_password=customer.getRetypePassword();

        boolean validPassword=registerService.validatePassword(password,retype_password);

        ModelAndView modelAndView = new ModelAndView();
        if (validPassword==true){
            registerService.register(customer);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        else {
            /*modelAndView.setViewName("forward:/register");
            modelAndView.addObject("error_message","Passwords does not match");*/
            //modelMap.addAttribute("password_error","Passwords does not match");
            modelAndView.setViewName("redirect:/wrong_registration");
            //redirectAttributes.addFlashAttribute("flashAttribute","Passwords does not match");
            return modelAndView;
        }
    }
    /*Method - registration
    * This method is used for handling wrong registration inputs &
    * will redirect to registration with the respective error message*/
    @RequestMapping("/wrong_registration")
    public ModelAndView registration(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView("registration");
        modelMap.addAttribute("password_error","Passwords does not match");
        return modelAndView;
    }
}
