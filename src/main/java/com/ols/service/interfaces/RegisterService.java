package com.ols.service.interfaces;

import com.ols.model.Customer;

import java.sql.SQLException;

public interface RegisterService {
    void register(Customer customer) throws SQLException;
    boolean validatePassword(String password, String retype_password);
}
