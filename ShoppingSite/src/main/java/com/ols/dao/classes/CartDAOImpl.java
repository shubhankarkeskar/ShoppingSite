package com.ols.dao.classes;

import com.ols.dao.interfaces.CartDAO;
import com.ols.dbconfig.HikariCPDataSource;
import com.ols.model.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/*Class - CartDAOImpl
* Implements - CartDAO
* DAO layer for Cart*/
public class CartDAOImpl implements CartDAO {
    @Autowired
    public ShoppingSiteDAOImpl siteDAO;
    //PreparedStatement object used throughout this class
    private PreparedStatement preparedStatement;

    /*Method - checkAvailability
    * Return type - boolean
    * Parameters - Stock & Quantity of Product
    * Functionality - Checking if the product has available stock */
    private boolean checkAvailability(int stock,int quantity){
        //Returns true if quantity of product is less than the stock
        if (stock>=quantity){
            return true;
        }
        else {
            return false;
        }
    }
    /*Method - isAvailable
    * Return type - boolean
    * Parameters - Array of productId & quantity
    * Functionality - Checking if stock is available or not from the database*/
    @Override
    public boolean isAvailable(String[] productID, String[] quantity) {

        //Declaration of stock array
        int stock[]=new int[productID.length];
        ResultSet resultSet=null;
        //Declaration of availability flag array
        boolean availabilityFlag[]=null;
        try {
            //Query for getting stock for that particular productID
            String query="select STOCK from PRODUCT where PRODUCT_ID=?";
            preparedStatement= HikariCPDataSource.getConnection().prepareStatement(query);
            for (int i=0;i<productID.length;i++) {
                //Setting productId to the condition of query
                preparedStatement.setString(1, productID[i]);

                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    stock[i]=resultSet.getInt(1);
                }
            }
            resultSet.close();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
            //Setting length for availability flag
            availabilityFlag=new boolean[productID.length];
            for (int i=0;i<stock.length;i++) {
                try {
                    //Checking availability of each product
                    availabilityFlag[i]=checkAvailability(stock[i], Integer.parseInt(quantity[i]));
                }catch (NumberFormatException n){ //Can throw NumberFormatException
                    n.printStackTrace();
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        boolean flag=true;
        //Checking availability flag array
        for (int i=0;i<availabilityFlag.length;i++){
            //if one element is false then set flag as true & break the loop
            if (availabilityFlag[i]==false){
                flag=false;
                break;
            }
        }
        return flag;
    }
    /*Method - getCart
    * Return type - List of ProductOrder
    * Parameters - productId & quantity
    * Functionality - Gives call to getSelectedProducts method of siteDAO with same parameters*/
    @Override
    public List<ProductOrder> getCart(String productID, String quantity) {
        return siteDAO.getSelectedProducts(productID,quantity);
    }
}
