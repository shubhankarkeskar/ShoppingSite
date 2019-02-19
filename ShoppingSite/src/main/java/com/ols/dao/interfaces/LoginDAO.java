package com.ols.dao.interfaces;

import com.ols.model.Login;

public interface LoginDAO {
    boolean checkFromDB(Login login);
    String getFullName(Login login);
    String getPassword(Login login);
}
