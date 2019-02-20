package com.ols.service.interfaces;

import com.ols.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface HomeService {
    List<Product> getProductList() throws Exception;
    List<Product> getSelectedProducts(String productId) throws Exception;
}
