package com.ols.dao.interfaces;

import com.ols.model.ProductOrder;

import java.util.List;

public interface CheckoutDAO {
    List<ProductOrder> getOrderDetails(String userName) throws Exception;
}
