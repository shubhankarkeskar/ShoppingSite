package com.ols.service.interfaces;

import com.ols.model.Customer;

public interface RegisterService {
    void register(Customer customer);
    boolean validatePassword(String password, String retype_password);
}
