package com.ols.exception;

import java.sql.SQLException;

public class UserNameDuplicateException extends SQLException {
    private String errorMessage;

    public UserNameDuplicateException(String errorMessage) {
        super(errorMessage);
    }
}
