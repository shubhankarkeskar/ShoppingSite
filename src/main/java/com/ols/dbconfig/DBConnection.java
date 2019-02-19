package com.ols.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connection;
    public static void connectToDB(String driver,String database,String user,String password) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(database, user, password);
            if (connection==null){
                System.out.println("Connection Failed");
            }
            else {
                System.out.println("Connection Successful");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
