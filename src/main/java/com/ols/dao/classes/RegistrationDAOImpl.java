package com.ols.dao.classes;

import com.ols.dao.interfaces.RegistrationDAO;
import com.ols.dbconfig.HikariCPDataSource;
import com.ols.model.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/*Class - RegistrationDAOImpl
 * Implements - RegistrationDAO
 * DAO layer of Registration*/
public class RegistrationDAOImpl implements RegistrationDAO {
    //PreparedStatement object used throughout this class
    private PreparedStatement preparedStatement;

    /*Method - insertIntoCustomer
    * Return type - void
    * Parameters - Customer object
    * Functionality - Inserting the customer details into the Customer table*/
    private void insertIntoCustomer(Customer customer) throws SQLException{
        try {
            preparedStatement = HikariCPDataSource.getConnection().prepareStatement("insert into CUSTOMER values (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, customer.getUserName());
            preparedStatement.setString(3, customer.getUserPassword());
            preparedStatement.setString(4, customer.getFullName());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getContact());
            preparedStatement.setString(7, customer.getBirthDate());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            HikariCPDataSource.closeConnection();
        }catch (SQLException s){
            throw  s;
        }
    }
    /*Method - register
    * Return type - void
    * Parameters - Customer object
    * Functionality - Gives a call to insertIntoCustomer method with same parameter*/
    @Override
    public void register(Customer customer) throws SQLException{
        insertIntoCustomer(customer);
    }
}
