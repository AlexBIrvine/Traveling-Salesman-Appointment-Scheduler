package DAO;

import Model.Country;
import Utilities.TimeAndPlace;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryDAO {
    
    
    //Get All Countries
    public static ObservableList<Country> getAllCountries() throws SQLException, Exception {
        //Declare return & SQL statement variables
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM country";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Loop through resultSet, get countries from database
        while( result.next() ) {
            //get variables from resultSet
            int ID = result.getInt("countryId");
            String country = result.getString("country");
            String createDateInput = result.getString("createDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");

            //create Country & add to allCountries
            Country resultCountry = new Country(ID, country, createDate, createdBy, lastUpdate, lastUpdateBy);
            allCountries.add(resultCountry);
        }
        
        //Return list of all countries
        return allCountries;
    }
    
    
    //Get Country
    public static Country getCountry(int ID) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT * FROM country WHERE countryId = '" + ID + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Access resultSet, get counry data from database
        while( result.next() ) {
            //get variables from resultSet
            int resultID = result.getInt("countryId");
            String country = result.getString("country");
            String createDateInput = result.getString("createDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");
            
            //Create Country & return
            Country resultCountry = new Country(resultID, country, createDate, createdBy, lastUpdate, lastUpdateBy);
            return resultCountry;
        }
        
        //If no result found, return NULL
        return null;
    }

    
    //Update Country
    public static void updateCountry(int countryID, String newCountryName) throws SQLException, Exception {
        
        //if ID found update record
        if( countryID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE country SET country = '" + newCountryName + "' WHERE countryId = " + countryID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
        //if ID NOT found, print to console error
        else
            System.out.println("NO COUNTRY EXISTS WITH ID: " + countryID);
    }

    
    //Delete Country
    public static void deleteCountry(String country) throws SQLException, Exception {
        //Gets ID of country
        int countryID = getCountryID(country);
        
        //If country exists, delete it
        if ( countryID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "DELETE FROM country WHERE countryId = " + countryID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Create Country
    public static void createCountry(String country) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy)VALUES ('"+country+"', NOW(), 'test', NOW(), 'test');";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
    }

    
    //Get CountryID
    public static int getCountryID(String country) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT countryId FROM country WHERE country  = '" + country + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        // Returns countryID if exists, returns -1 if it doesn't exist
        //exists
        if ( result.next() ) {
            int ID =  result.getInt("countryId");
            return ID;
        }
        //doesn't exist
        else {
            return -1;
        }   
    }
}