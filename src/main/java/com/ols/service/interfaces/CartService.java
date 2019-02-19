package com.ols.service.interfaces;

import com.ols.model.ProductOrder;

import java.sql.SQLException;
import java.util.List;

public interface CartService {
    boolean isAvailable(String productID[],String quantity[]) throws NumberFormatException,NullPointerException, SQLException;
    List<ProductOrder> getCart(String productId, String quantity) throws SQLException,NullPointerException;
    String[] removeItem(String[] cart,int index);
    String[] addItem(String[] cart,int newLength,String item);
}
