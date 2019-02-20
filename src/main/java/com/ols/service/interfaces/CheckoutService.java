package com.ols.service.interfaces;

import com.ols.model.ProductOrder;

import java.sql.SQLException;
import java.util.List;

public interface CheckoutService {
    int confirmOrder(String productId,String quantity,String userName,int insertCount) throws Exception;
    boolean checkConfirmation(int confirm[]) throws NullPointerException;
    List<ProductOrder> getOrderDetails(String userName) throws Exception;
    int totalAmount(List<ProductOrder> list);
}
