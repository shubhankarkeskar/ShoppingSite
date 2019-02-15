package com.ols.service.interfaces;

import com.ols.model.ProductOrder;

import java.util.List;

public interface CartService {
    boolean isAvailable(String productID[],String quantity[]);
    List<ProductOrder> getCart(String productId, String quantity);
    String[] removeItem(String[] cart,int index);
    String[] addItem(String[] cart,int newLength,String item);
}
