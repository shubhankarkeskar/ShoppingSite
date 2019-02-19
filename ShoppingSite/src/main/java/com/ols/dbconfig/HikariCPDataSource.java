package com.ols.dbconfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPDataSource {
    private static HikariConfig config=new HikariConfig();
    private static DBConfig dbConfig;
    private static Connection connection;
    private static DataSource dataSource;

    private static Logger logger= LoggerFactory.getLogger(HikariCPDataSource.class);
    static {
        dbConfig=new DBConfig();
        dbConfig.readConfigFile();
    }
    private static DataSource getDataSource() {

        config.setDriverClassName(dbConfig.getDriver());
        config.setJdbcUrl(dbConfig.getDatabase());
        config.setUsername(dbConfig.getDbUser());
        config.setPassword(dbConfig.getDbPassword());

        //config.setMaximumPoolSize(50);
        config.setAutoCommit(true);
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");
        dataSource=new HikariDataSource(config);
        return dataSource;
    }
    public static Connection getConnection() throws SQLException {
        DataSource dataSource=HikariCPDataSource.getDataSource();
        connection=dataSource.getConnection();
        //logger.info("Connection opened "+connection);
        System.out.println("\nConnection opened "+connection);
        return connection;
    }
    public static void closeConnection() throws SQLException{
        //logger.info("Connection closed "+connection);
        System.out.println("Connection closed "+connection);
        connection.close();
    }
    public HikariCPDataSource() {
    }
}