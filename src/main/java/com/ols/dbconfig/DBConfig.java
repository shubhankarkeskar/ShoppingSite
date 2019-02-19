package com.ols.dbconfig;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*Class - DBConfig
* Used for reading from properties file for database connection*/
public class DBConfig {
    private String driver;
    private String database;
    private String dbUser;
    private String dbPassword;
    public static final Logger logger=Logger.getLogger(DBConfig.class);

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbuser) {
        this.dbUser = dbuser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void readConfigFile() {
        logger.info("READING CONFIG FILE");
        //Properties class used for setting & getting properties
        Properties properties=new Properties();
        InputStream input=null;
        try {
            //Input properties file
            input=new FileInputStream("/home/shubhankar/IdeaProjects/ShoppingSite/src/main/java/com/ols/dbconfig/dbconfig.properties");
            //Loading the input file by load method of properties
            properties.load(input);
            logger.info("SETTING PROPERTIES");
            // Setting database properties
            setDriver(properties.getProperty("driver"));
            setDatabase(properties.getProperty("database"));
            setDbUser(properties.getProperty("dbuser"));
            setDbPassword(properties.getProperty("dbpassword"));

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (input!=null){
                try {
                    input.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new DBConfig().readConfigFile();
    }
}
