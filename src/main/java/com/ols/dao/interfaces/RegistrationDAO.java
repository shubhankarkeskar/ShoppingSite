package com.ols.dao.interfaces;

import com.ols.model.Customer;

import java.sql.SQLException;

public interface RegistrationDAO {
    void register(Customer customer) throws SQLException;
}
