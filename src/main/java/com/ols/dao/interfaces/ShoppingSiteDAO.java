package com.ols.dao.interfaces;

import com.ols.model.Product;
import com.ols.model.ProductOrder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ShoppingSiteDAO {
    List<Product> getProducts() throws Exception;
    List<Product> getProductsForConfirmation(String productId)throws Exception;
    List<ProductOrder> getSelectedProducts(String productId, String quantity) throws Exception;
    int confirmOrder(String productId, String quantity, String userName,int insertCount,String order_time) throws Exception;
    int getCustomerId(String userName) throws Exception;
    int getResultFromQuery(PreparedStatement preparedStatement)throws Exception;
}
