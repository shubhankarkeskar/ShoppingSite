package com.ols.service.interfaces;

import com.ols.model.Login;

public interface LoginService {
    boolean validate(Login login);
    String getFullName(Login login);
}
