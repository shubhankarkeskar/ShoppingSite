package com.ols.controllers;

import com.ols.exception.UserNameDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLException;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController {
    @ExceptionHandler(UserNameDuplicateException.class)
    public ModelAndView duplicateError(){
        ModelAndView modelAndView=new ModelAndView("redirect:/register_error");
        return modelAndView;
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> sqlError(){
        return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerError(){
        return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
