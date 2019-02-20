package com.ols.service.classes;

import com.ols.dao.interfaces.ShoppingSiteDAO;
import com.ols.model.Product;
import com.ols.service.interfaces.HomeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
/*Class - HomeServiceImpl
* Implements - HomeService
* Service layer for Home*/
public class HomeServiceImpl implements HomeService {
    @Autowired
    public ShoppingSiteDAO siteDAO;

    public static final Logger logger=Logger.getLogger(HomeServiceImpl.class);
    /*Method - getProductList
    * Return type - List of Products
    * Parameters - None
    * Functionality - Gives call to getProducts method of siteDAO & gets a list of Products*/
    @Override
    public List<Product> getProductList() throws Exception {
        logger.info("INSIDE HOME SERVICE : getProductList");
        return siteDAO.getProducts();
    }
    /*Method - getSelectedProducts
    * Return type - List of Products
    * Parameters - ProductId
    * Functionality - Gives a call to the getProductsForConfirmation method of siteDAO & gets a list of products*/
    @Override
    public List<Product> getSelectedProducts(String productId) throws Exception {
        logger.info("INSIDE HOME SERVICE : getSelectedProducts");
        return siteDAO.getProductsForConfirmation(productId);
    }
}
