package com.ols.controllers;

import com.ols.exception.UserNameDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLException;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController {
    @ExceptionHandler(UserNameDuplicateException.class)
    public ModelAndView duplicateError(){
        return new ModelAndView("redirect:/register_error");
    }
    @ExceptionHandler(SQLException.class)
    public ModelAndView sqlError(){
        ModelAndView modelAndView=new ModelAndView("500");
        modelAndView.addObject("exception_message","Database Error");
        return modelAndView;
        //return new ResponseEntity<>("Database Error\nPlease Contact Support",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView nullPointerError(){
        ModelAndView modelAndView=new ModelAndView("500");
        modelAndView.addObject("exception_message","Internal Server Error");
        return modelAndView;
        //return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView numberFormatError(){
        ModelAndView modelAndView=new ModelAndView("406");
        modelAndView.addObject("exception_message","Number Format Not Accepted");
        return modelAndView;
        //return new ResponseEntity<>("Number Format Not Accepted",HttpStatus.NOT_ACCEPTABLE);
    }
}
