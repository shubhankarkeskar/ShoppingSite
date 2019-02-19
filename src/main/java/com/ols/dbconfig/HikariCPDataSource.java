package com.ols.dbconfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPDataSource {
    private static HikariConfig config=new HikariConfig();
    private static DBConfig dbConfig;
    private static Connection connection;
    private static DataSource dataSource;

    private static final Logger logger=Logger.getLogger(HikariCPDataSource.class);
    static {
        logger.info("INSIDE HIKARI CP DATA SOURCE : STATIC BLOCK");
        dbConfig=new DBConfig();
        dbConfig.readConfigFile();
    }
    private static DataSource getDataSource() {
        logger.info("INSIDE GET DATASOURCE");
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
        logger.info("INSIDE DATASOURCE : getConnection");
        DataSource dataSource=HikariCPDataSource.getDataSource();
        connection=dataSource.getConnection();
        return connection;
    }
    public static void closeConnection() throws SQLException{
        logger.info("INSIDE DATASOURCE : closeConnection");
        connection.close();
    }
    public HikariCPDataSource() {
    }
}