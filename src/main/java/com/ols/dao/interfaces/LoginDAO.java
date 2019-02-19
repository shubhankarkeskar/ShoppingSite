package com.ols.dao.interfaces;

import com.ols.model.Login;

import java.sql.SQLException;

public interface LoginDAO {
    boolean checkFromDB(Login login) throws SQLException;
    String getFullName(Login login) throws SQLException;
    String getPassword(Login login) throws SQLException;
}
