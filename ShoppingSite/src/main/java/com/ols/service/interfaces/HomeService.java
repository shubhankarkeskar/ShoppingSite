package com.ols.service.interfaces;

import com.ols.model.Product;

import java.util.List;

public interface HomeService {
    List<Product> getProductList();
    List<Product> getSelectedProducts(String productId);
}
