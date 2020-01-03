package DAO;

import Model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressDAO {
    
    
    //Get All Address
    public static ObservableList<Address> getAllAddresses() throws SQLException, Exception {
        //Declare return & SQL statement variables
        ObservableList<Address> allAddresses = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM address";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Loop through resultSet, get addresses from database
        while( result.next() ) {
            //get variables from resultSet
            int ID = result.getInt("addressId");
            String address = result.getString("address");
            String address2 = result.getString("address2");
            int cityId = result.getInt("cityId");
            String postalCode = result.getString("postalCode");
            String phone = result.getString("phone");
            String createDateInput = result.getString("createDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");

            //create Address & add to allAddresses
            Address resultAddress = new Address(ID, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy);
            allAddresses.add(resultAddress);
        }
        
        //Return list of all countries
        return allAddresses;
    }
    
    
    //Get Address
    public static Address getAddress(int ID) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT * FROM address WHERE addressId = '" + ID + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Access ResultSet, get country data from database
        while( result.next() ) {
            //get variables from resultSet
            int resultID = result.getInt("addressId");
            String address = result.getString("address");
            String address2 = result.getString("address2");
            int cityId = result.getInt("cityId");
            String postalCode = result.getString("postalCode");
            String phone = result.getString("phone");
            String createDateInput = result.getString("createDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");
            
            //Create Address & return
            Address resultAddress = new Address(resultID, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy);
            return resultAddress;
        }
        
        //If no result found, return NULL
        return null;
    }
    
    
    //Update Address
    public static void updateAddress(int addressID, String newAddressName) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( addressID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE address SET address = '" + newAddressName + "' WHERE addressId = " + addressID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update Address2
    public static void updateAddress2(int addressID, String newAddress2Name) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( addressID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE address SET address2 = '" + newAddress2Name + "' WHERE addressId = " + addressID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update postalCode
    public static void updatePostalCode(int addressID, String newPostalCode) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( addressID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE address SET postalCode = '" + newPostalCode + "' WHERE addressId = " + addressID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update phone
    public static void updatePhone(int addressID, String newPhone) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( addressID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE address SET phone = '" + newPhone + "' WHERE addressId = " + addressID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Delete Address
    public static void deleteAddress(int addressID) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( addressID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "DELETE FROM address WHERE addressId = " + addressID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }

    
    //Create Address
    public static void createAddress(String address, String address2, int cityId, String postalCode, String phone) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy)VALUES('"+address+"', '"+address2+"', '"+cityId+"', '"+postalCode+"', '"+phone+"',  NOW(), 'test', NOW(), 'test');";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
    }
    
    
    //Get AddressID
    public static int getAddressID(String address) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT addressId FROM address WHERE address  = '" + address + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        // Returns addressID if exists, returns -1 if it doesn't exist
        //exists
        if ( result.next() ) {
            int ID =  result.getInt("addressId");
            return ID;
        }
        //doesn't exist
        else {
            return -1;
        }   
    }
}