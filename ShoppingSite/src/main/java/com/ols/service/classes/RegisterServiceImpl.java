package com.ols.service.classes;

import com.ols.dao.interfaces.RegistrationDAO;
import com.ols.model.Customer;
import com.ols.service.interfaces.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*Class - RegisterServiceImpl
* Implements - RegisterService
* Service layer for Registration*/
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    public RegistrationDAO registrationDAO;
    /*Method - PasswordEncoder
    * Return type - void
    * Parameters - Object of Customer model
    * Functionality - Encodes the password from Customer model & setting the hash code*/
    public void PasswordEncoder(Customer customer){
        //Using the BCryptPasswordEncoder class to encode from spring security framework
        BCryptPasswordEncoder bCrypt=new BCryptPasswordEncoder();
        //Calling encode method to hash the password
        String encrypted_password=bCrypt.encode(customer.getUserPassword());
        //Setting back the hashed password in the Customer
        customer.setUserPassword(encrypted_password);
    }
    /*Method - register
    * Return type - void
    * Parameters - Object of Customer model
    * Functionality - Gives a call to passwordEncoder & register method of registrationDAO*/
    @Override
    public void register(Customer customer) {
        PasswordEncoder(customer);
        registrationDAO.register(customer);
    }
    /*Method - validatePassword
    * Return type - boolean
    * Parameters - Password & Retype Password
    * Functionality - Checks the password & retype password fields of registration form*/
    @Override
    public boolean validatePassword(String password, String retype_password) {
        //Returns true if both are equal
        if (password.equals(retype_password)){
            return true;
        }
        else {
            return false;
        }
    }
}
