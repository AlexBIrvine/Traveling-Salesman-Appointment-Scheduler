package DAO;

import Model.Customer;
import Utilities.TimeAndPlace;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDAO {
    

    //Get All Customers
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception {
        //Declare return & SQL statement variables
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM customer";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Loop through resultSet, get cities from database
        while( result.next() ) {
            //get variables from resultSet
            int customerId = result.getInt("customerId");
            String customerName = result.getString("customerName");
            int addressId = result.getInt("addressId");
            int active = result.getInt("active");
            String createDateInput = result.getString("createDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");

            //create City & add to allCities
            Customer resultCustomer = new Customer(customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy);
            allCustomers.add(resultCustomer);
        }
        
        //Return list of all cities
        return allCustomers;
        
        
    }
    
    
    //Get Customer
    public static Customer getCustomer(int ID) throws SQLException, Exception {
        //Decalre SQL statement variable
        String sqlStatement = "SELECT * FROM customer WHERE customerId = '" + ID + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Access ResultSet, get CITY data from database
        while( result.next() ) {
            //get variables from resultSet
            int customerId = result.getInt("customerId");
            String customerName = result.getString("customerName");
            int addressId = result.getInt("addressId");
            int active = result.getInt("active");
            String createDateInput = result.getString("createDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");
            
            //Create customer & return it
            Customer resultCustomer = new Customer(customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy);
            return resultCustomer;
        }
        //If no result found, return NULL
        return null;
    }
    
    
    //Update CustomerName
    public static void updateCustomerName(int customerID, String newCustomerName) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( customerID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE customer SET customerName = '" + newCustomerName + "' WHERE customerId = " + customerID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    //Update CustomerAddressID
    public static void updateCustomerAddressID(int customerID, int newAddressID) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( customerID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE customer SET addressId = '" + newAddressID + "' WHERE customerId = " + customerID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    

    //Delete Customer
    public static void deleteCustomer(int customerID) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( customerID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "DELETE FROM customer WHERE customerId = " + customerID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }

    
    //Create Customer
    public static void createCustomer(String customerName, int addressId) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)VALUES('"+customerName+"', '"+addressId+"', 1, NOW(), 'test', NOW(), 'test');";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
    }

    
    //Get CutomerID
     public static int getCutomerID(String customerName) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT customerId FROM customer WHERE customerName  = '" + customerName + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        // Returns appointmentID if exists, returns -1 if it doesn't exist
        //exists
        if ( result.next() ) {
            int ID =  result.getInt("customerId");
            return ID;
        }
        //doesn't exist
        else {
            return -1;
        }   
    }
}