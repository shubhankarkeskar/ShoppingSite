package com.ols.service.classes;

import com.ols.dao.interfaces.LoginDAO;
import com.ols.model.Login;
import com.ols.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;
/*Class - LoginServiceImpl
* Implements - LoginService
* Service layer for Login*/
public class LoginServiceImpl implements LoginService {
    @Autowired
    public LoginDAO loginDAO;
    @Autowired
    public BCryptPasswordEncoder encoder;
    /*Method - validatePassword
    * Return type - boolean
    * Parameters - Password
    * Functionality - It validates the password using regular expression. If pattern for password is matched then it returns true
    * */
    private boolean validatePassword(String password){
       return Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}",password);
    }
    /*Method - validateUserName
    * Return type - boolean
    * Parameters - User Name
    * Functionality - It validates the email(user name) using regular expression. If pattern for email is matched then it returns true
    * */
    private boolean validateUserName(String userName){
        return Pattern.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$",userName);
    }
    /*Method - checkEncryptedPassword
    * Return type - boolean
    * Parameters - Password & Object of login model
    * Functionality - It matches the raw password with the hashed password. Returns true if it matches
    * */
    private boolean checkEncryptedPassword(String password,Login login){
        return encoder.matches(login.getUserPassword(),password);
    }
    /*Method - validate
    * Return type - boolean
    * Parameters - Object of login model
    * Functionality - It gives call to all the validations & checks. If all these conditions are satisfied then it returns true*/
    @Override
    public boolean validate(Login login) {
        //Call to validatePassword
        boolean isPasswordValid=validatePassword(login.getUserPassword());
        //Call to validateUserName
        boolean isUserNameValid=validateUserName(login.getUserName());
        //Call to checkFromDB of loginDAO
        boolean isFromDB=loginDAO.checkFromDB(login);
        //Gets password from database
        String password=loginDAO.getPassword(login);
        //Call to checkEncryptedPassword
        boolean checkEncryptedPassword=checkEncryptedPassword(password,login);
        //If all the above conditions are true,then it returns true
        if (isPasswordValid==true && isUserNameValid==true && isFromDB==true && checkEncryptedPassword==true){
            return true;
        }
        else
            return false;
    }
    /*Method - getFullName
    * Return type - String
    * Parameters - Object of login
    * Functionality - Gives a call to getFullName method of loginDAO & gets full name of that user*/
    @Override
    public String getFullName(Login login) {
        return loginDAO.getFullName(login);
    }
}
