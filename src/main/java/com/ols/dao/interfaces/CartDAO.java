package com.ols.dao.interfaces;

import com.ols.model.ProductOrder;

import java.sql.SQLException;
import java.util.List;

public interface CartDAO {
    boolean isAvailable(String[] productID, String[] quantity) throws NumberFormatException,SQLException,NullPointerException;
    List<ProductOrder> getCart(String productID, String quantity) throws SQLException,NullPointerException;
}
