package com.ols.dao.classes;

import com.ols.dao.interfaces.ShoppingSiteDAO;
import com.ols.dbconfig.HikariCPDataSource;
import com.ols.model.Category;
import com.ols.model.Product;
import com.ols.model.ProductOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*Class - ShoppingSiteDAOImpl
* Implements - ShoppingSiteDAO
* DAO layer of Shopping Site*/
public class ShoppingSiteDAOImpl implements ShoppingSiteDAO {
    //PreparedStatement object used throughout this class
    private PreparedStatement preparedStatement;
    /*Method - getProducts
    * Return type - List of Products
    * Parameters - none
    * Functionality - Gets a list of all products with its category*/
    @Override
    public List<Product> getProducts() {

        //Creating a list of products
        List<Product> productList=new ArrayList<>();
        try {
            String query="select * from PRODUCT P,CATEGORY C where P.CATEGORY_ID=C.CATEGORY_ID";
            preparedStatement= HikariCPDataSource.getConnection().prepareStatement(query);
            //Executing query
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product=new Product();
                Category category=new Category();

                product.setProductID(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setProductDescription(resultSet.getString(3));
                product.setPrice(resultSet.getFloat(4));
                product.setStock(resultSet.getInt(5));
                category.setCategoryID(resultSet.getInt(7));
                category.setCategoryName(resultSet.getString(8));
                category.setCategoryDescription(resultSet.getString(9));
                //Adding products and its category to the list
                productList.add(product);
                product.setCategory(category);

            }
            resultSet.close();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return productList;
    }
    /*Method - getSelectedProducts
    * Return type - List of Product Order
    * Parameters - productId & quantity
    * Functionality - Gets list of selected products which needs to be added to the cart*/
    @Override
    public List<ProductOrder> getSelectedProducts(String productId, String quantity) {

        //Creating products list but here we will only add required fields
        List<ProductOrder> productList=new ArrayList<>();
        try {
            String query="select PRODUCT_ID,PRODUCT_NAME,PRICE,STOCK from PRODUCT where PRODUCT_ID=?";
            preparedStatement=HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1,productId);

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                ProductOrder productOrder=new ProductOrder();
                Product product=new Product();
                productOrder.setProduct(product);

                productOrder.getProduct().setProductID(resultSet.getInt(1));
                productOrder.getProduct().setProductName(resultSet.getString(2));
                productOrder.getProduct().setPrice(resultSet.getFloat(3));
                productOrder.getProduct().setStock(resultSet.getInt(4));
                productOrder.setQuantity(Integer.parseInt(quantity));

                productList.add(productOrder);
            }
            resultSet.close();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return productList;
    }
    /*Method - getProductsForConfirmation
    * Return type - List of Products
    * Parameters - ProductId
    * Functionality - Gets a list of selected products for confirmation*/
    @Override
    public List<Product> getProductsForConfirmation(String productId) {

        //Creating products list but here we will only add required fields
        List<Product> productList=new ArrayList<>();
        try {
            String query="select PRODUCT_ID,PRODUCT_NAME,PRICE,STOCK from PRODUCT where PRODUCT_ID=?";
            preparedStatement=HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1,productId);

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product=new Product();

                product.setProductID(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setPrice(resultSet.getFloat(3));
                product.setStock(resultSet.getInt(4));

                productList.add(product);
            }
            resultSet.close();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return productList;
    }
    /*Method - checkStock
    * Return type - boolean
    * Parameters - sock & quantity
    * Functionality - Checks whether stock is available or not*/
    private boolean checkStock(int stock, String quantity){
        if (stock>Integer.parseInt(quantity)){
            return true;
        }
        else {
            return false;
        }
    }
    /*Method - executeMyQuery
    * Return type - Generic
    * Parameters - query & generic condition
    * Functionality - Generic method which executes a query & returns a generic type based on the condition*/
    private <T> int executeMyQuery(String query,T condition){
        int return_value=0;
        try {
            preparedStatement = HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1,condition.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return_value=resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return return_value;
    }
    /*Method - checkAvailability
    * Return type - boolean
    * Parameters - productId & quantity
    * Functionality - Sets the query & gives call for execution of query & checking the stock */
    private boolean checkAvailability(String productId,String quantity){
        int stock=0;
        String query="select STOCK from PRODUCT where PRODUCT_ID=?";
        stock=executeMyQuery(query,productId);
        return checkStock(stock,quantity);
    }
    /*Method - getCustomerId
    * Return type - integer
    * Parameters - User Name
    * Functionality - Sets the required query & gives call execution of query*/
    @Override
    public int getCustomerId(String userName){
        String query="select CUSTOMER_ID from CUSTOMER where USER_NAME=?";
        return executeMyQuery(query,userName);
    }
    /*Method - getResultFromQuery
    * Return type - integer
    * Parameters - PreparedStatement object
    * Functionality - Executes the required query*/
    @Override
    public int getResultFromQuery(PreparedStatement preparedStatement){
        int result=0;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return result;
    }
    /*Method - getOrderId
    * Return type - integer
    * Parameters - customerId & order time
    * Functionality - Gets orderId of a particular customer having the respective order time*/
    private int getOrderId(int customerId,String orderTime){
        int temp_orderId=0;
        String query="select CO.ORDER_ID from CUSTOMER_ORDER CO,CUSTOMER C where CO.CUSTOMER_ID=C.CUSTOMER_ID AND CO.CUSTOMER_ID=? AND CO.ORDER_TIME=?";
        try {
            preparedStatement=HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,customerId);
            preparedStatement.setString(2,orderTime);

            temp_orderId=getResultFromQuery(preparedStatement);
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return temp_orderId;
    }
    /*Method - addToProductOrder
    * Return type - integer
    * Parameters - orderID,productId & quantity
    * Functionality - Inserts the Product Order details into the table*/
    private int addToProductOrder(int orderId,String productID,String quantity){
        int updated=0;
        try {
            String query = "insert into PRODUCT_ORDER values (?,?,?)";
            preparedStatement = HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, Integer.parseInt(productID));
            preparedStatement.setInt(3, Integer.parseInt(quantity));

            updated=preparedStatement.executeUpdate();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return updated;
    }
    /*Method - checkUpdated
    * Return type - integer
    * Parameters - Update parameters
    * Functionality - Checks for update is completed or not*/
    private int checkUpdated(int update1,int update2){
        //Returns 1 if both updates are complete
        if (update1==1 && update2==1){
            return 1;
        }
        else {
            return 0;
        }
    }
    /*Method - addToOrder
    * Return type - integer
    * Parameters - order time & customerId
    * Functionality - Inserts the customer order details into the table*/
    private int addToOrder(String order_time,int customerId){

        int updated_Customer_Order=0;
        try {
            String query="insert into CUSTOMER_ORDER values(?,?,?,?)";
            preparedStatement=HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1,null);
            preparedStatement.setInt(2,customerId);
            preparedStatement.setString(3,order_time);
            preparedStatement.setString(4,"Confirmed");

            updated_Customer_Order=preparedStatement.executeUpdate();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return updated_Customer_Order;
    }
    /*Method - updateStock
    * Return type - integer
    * Parameters - productID & quantity
    * Functionality - Updates the stock of a particular productId*/
    private int updateStock(String productId,String quantity){
        //First getting the stock of that product
        String query="select STOCK from PRODUCT where PRODUCT_ID=?";
        int stock=executeMyQuery(query,productId);
        int updated=0;
        try {
            String update_query="update PRODUCT set STOCK=? where PRODUCT_ID=?";
            preparedStatement=HikariCPDataSource.getConnection().prepareStatement(update_query);
            preparedStatement.setInt(1,(stock-Integer.parseInt(quantity)));
            preparedStatement.setInt(2,Integer.parseInt(productId));

            updated=preparedStatement.executeUpdate();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return updated;
    }
    private int updated_addition=0;

    public void setUpdated_addition(int updated_addition) {
        this.updated_addition = updated_addition;
    }

    public int getUpdated_addition() {
        return updated_addition;
    }
    /*Method - confirmOrder
    * Return type - integer
    * Parameters - productId,quantity,user name,insert count & order time
    * Functionality - Gives call to required methods of insertion & updation for confirmation of complete order*/
    @Override
    public int confirmOrder(String productId, String quantity, String userName,int insertCount,String order_time) {
        //Call to check availability of product
        boolean availability=checkAvailability(productId,quantity);
        int updated,stock_updated,customerId;
        //If available then do the processing
        if (availability==true){
            //Getting customerId
            customerId = getCustomerId(userName);
            //Inserting to customer order only once
            if (insertCount<=1) {
                updated = addToOrder(order_time, customerId);
                setUpdated_addition(updated);
            }
            //Getting orderId
            int orderId=getOrderId(customerId,order_time);
            //Update stock
            stock_updated = updateStock(productId, quantity);
            //Add to product order
            int updated_Product_Order=addToProductOrder(orderId,productId,quantity);
            //Check all insertion & updates. Will return true if everything is processed correctly
            return checkUpdated(checkUpdated(getUpdated_addition(),updated_Product_Order),stock_updated);
        }
        else {
            return 0;
        }
    }
}
