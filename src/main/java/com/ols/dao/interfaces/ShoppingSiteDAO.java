package com.ols.dao.interfaces;

import com.ols.model.Product;
import com.ols.model.ProductOrder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ShoppingSiteDAO {
    List<Product> getProducts() throws SQLException,NullPointerException;
    List<Product> getProductsForConfirmation(String productId);
    List<ProductOrder> getSelectedProducts(String productId, String quantity) throws SQLException,NullPointerException;
    int confirmOrder(String productId, String quantity, String userName,int insertCount,String order_time);
    int getCustomerId(String userName);
    int getResultFromQuery(PreparedStatement preparedStatement);
}
