package com.ols.dao.classes;

import com.ols.dao.interfaces.CheckoutDAO;
import com.ols.dao.interfaces.ShoppingSiteDAO;
import com.ols.dbconfig.HikariCPDataSource;
import com.ols.model.Order;
import com.ols.model.Product;
import com.ols.model.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*Class - CheckoutDAOImpl
* Implements - CheckoutDAO
* DAO layer of Checkout*/
public class CheckoutDAOImpl implements CheckoutDAO {
    @Autowired
    public ShoppingSiteDAO siteDAO;
    //PreparedStatement object used throughout this class
    private PreparedStatement preparedStatement;

    /*Method - getOrderId
    * Return type - integer
    * Parameters - customerID
    * Functionality - Gets the latest orderID of that customer who has placed the order*/
    private int getOrderId(int customerID){
        int orderID=0;
        try {
            String query="select CO.ORDER_ID from CUSTOMER_ORDER CO,CUSTOMER C where CO.CUSTOMER_ID=C.CUSTOMER_ID AND CO.CUSTOMER_ID=? order by CO.ORDER_TIME desc limit 1";
            preparedStatement= HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,customerID); //Setting customerId to the query condition

            //Getting the orderID by calling getResultFromQuery method of siteDAO & passing preparedStatement object as parameter
            orderID=siteDAO.getResultFromQuery(preparedStatement);
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return orderID;
    }
    /*Method - getDetails
    * Return type - List of ProductOrder
    * Parameters - orderId
    * Functionality - Gets product details of that order & also calculating product total(price*quantity)*/
    private List<ProductOrder> getDetails(int orderID){
        //Declaration of list
        List<ProductOrder> list=new ArrayList<>();
        try {
            String query="select PO.ORDER_ID,P.PRODUCT_NAME,P.PRICE,PO.QUANTITY,(PO.QUANTITY * P.PRICE) AS PRODUCT_TOTAL from PRODUCT_ORDER PO,PRODUCT P where PO.PRODUCT_ID=P.PRODUCT_ID AND PO.ORDER_ID=?";
            preparedStatement=HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,orderID);    //Setting orderID to the query condition

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                //Creating objects of ProductOrder,Order & Product
                ProductOrder productOrder=new ProductOrder();
                Order order=new Order();
                Product product=new Product();

                //Setting object of order & product in ProductOrder
                productOrder.setOrder(order);
                productOrder.setProduct(product);

                //Setting the required attributes
                productOrder.getOrder().setOrderID(resultSet.getInt(1));
                productOrder.getProduct().setProductName(resultSet.getString(2));
                productOrder.getProduct().setPrice(resultSet.getFloat(3));
                productOrder.setQuantity(resultSet.getInt(4));
                productOrder.setProductTotal(resultSet.getInt(5));

                //Adding productOrder object to the list
                list.add(productOrder);
            }
            resultSet.close();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return list;
    }
    /*Method - updateOrderStatus
    * Return type - integer
    * Parameters - orderID
    * Functionality - Updates order status of that particular order*/
    private int updateOrderStatus(int orderID){
        int statusUpdated=0;
        try {
            String query="update CUSTOMER_ORDER set ORDER_STATUS='Confirmed' where ORDER_ID=?";
            preparedStatement=HikariCPDataSource.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,orderID);
            statusUpdated=preparedStatement.executeUpdate();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return statusUpdated;
    }
    /*Method - getOrderDetails
    * Return type - List of Product Order
    * Parameters - User Name
    * Functionality - Gives call to all the required methods which will in turn return a list*/
    @Override
    public List<ProductOrder> getOrderDetails(String userName) {

        //Gets customerID by calling getCustomerID method of siteDAO & userName as parameter
        int customerID=siteDAO.getCustomerId(userName);
        //Gets orderId by calling getOrderId method & customerID as parameter
        int orderID=getOrderId(customerID);
        //int status_update=updateOrderStatus(orderID);
        //Gets list of ProductOrder by calling getDetails method & orderID as parameter
        return getDetails(orderID);
    }
}
