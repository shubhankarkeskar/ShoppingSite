package com.ols.service.interfaces;

import com.ols.model.ProductOrder;

import java.util.List;

public interface CheckoutService {
    int confirmOrder(String productId,String quantity,String userName,int insertCount);
    boolean checkConfirmation(int confirm[]);
    List<ProductOrder> getOrderDetails(String userName);
    int totalAmount(List<ProductOrder> list);
}
