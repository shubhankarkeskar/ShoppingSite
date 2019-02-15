package com.ols.dao.classes;

import com.ols.dao.interfaces.LoginDAO;
import com.ols.dbconfig.HikariCPDataSource;
import com.ols.model.Login;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*Class - LoginDAOImpl
* Implements - LoginDAO
* DAO layer of Login*/
public class LoginDAOImpl implements LoginDAO {

    //PreparedStatement object used throughout this class
    private PreparedStatement preparedStatement;

    /*Method - isPresent
    * Return type - boolean
    * Parameters - Object of Login model
    * Functionality - Checks if that particular user is present or not*/
    private boolean isPresent(Login login){
        int exists=0;
        try{
            String query="select exists(select USER_NAME from CUSTOMER where USER_NAME=?)";
            preparedStatement=HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1,login.getUserName());

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                exists = resultSet.getInt(1); //If exists then it will return 1
            }
            resultSet.close();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        //If exists then return true otherwise false
        if (exists==1){
            return true;
        }
        else {
            return false;
        }
    }
    /*Method - executeMyQuery
    * Return type - String
    * Parameters - PreparedStatement Object
    * Functionality - Generic method for executing query which returns a result as string*/
    private String executeMyQuery(PreparedStatement preparedStatement){
        String return_string=null;
        try {
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                return_string=resultSet.getString(1);
            }
            resultSet.close();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return return_string;
    }
    /*Method - prepareMyQuery
    * Return type - String
    * Parameters - Login object & query
    * Functionality - Generic method for preparing query & giving a call to execute query which returns a result as string*/
    private String prepareMyQuery(Login login,String query){
        String return_string=null;
        try {
            preparedStatement = HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1, login.getUserName());

            return_string = executeMyQuery(preparedStatement);
            preparedStatement.close();

            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return return_string;
    }
    /*Method - getPassword
    * Return type - String
    * Parameters - Login object
    * Functionality - Gets password of the user with specified user name*/
    @Override
    public String getPassword(Login login) {
        String password;
        String query="select PASSWORD from CUSTOMER where USER_NAME=?";
        password=prepareMyQuery(login,query); //Call to prepare query
        return password;
    }
    /*Method - checkFromDB
    * Return type - boolean
    * Parameters - Login object
    * Functionality - Calls isPresent method to check if user is present or not*/
    @Override
    public boolean checkFromDB(Login login) {
        return isPresent(login);
    }
    /*Method - getFullName
    * Return type - String
    * Parameters - Login object
    * Functionality - Gets full name of a user*/
    @Override
    public String getFullName(Login login) {
        String fullName;
        String query="select FULL_NAME from CUSTOMER where USER_NAME=?";
        fullName=prepareMyQuery(login,query);
        return fullName;
    }
}
