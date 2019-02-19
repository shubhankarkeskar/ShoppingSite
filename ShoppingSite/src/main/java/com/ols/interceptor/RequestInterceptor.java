package com.ols.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(request.getRequestURI().equals("/ShoppingSite/")) && !(request.getRequestURI().equals("/ShoppingSite/register"))
         && !(request.getRequestURI().equals("/ShoppingSite/sign_in"))
         && !(request.getRequestURI().equals("/ShoppingSite/wrong_login"))
         && !(request.getRequestURI().equals("/ShoppingSite/wrong_registration"))){

            if (request.getSession().getAttribute("userName")==null && request.getRequestURI().equals("/ShoppingSite/sign_in")) {
                response.sendRedirect("/ShoppingSite/");
                return false;
            }
        }
       //System.out.println(request.getRequestURI());
        return true;
    }
}
