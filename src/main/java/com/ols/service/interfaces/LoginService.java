package com.ols.service.interfaces;

import com.ols.model.Login;

import java.sql.SQLException;

public interface LoginService {
    boolean validate(Login login) throws SQLException;
    String getFullName(Login login) throws SQLException;
}
