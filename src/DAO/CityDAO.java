package DAO;

import Model.City;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CityDAO {
    
    
    //Get All Cities
    public static ObservableList<City> getAllCities() throws SQLException, Exception {
        //Declare return & SQL statement variables
        ObservableList<City> allCities = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM city";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Loop through resultSet, get cities from database
        while( result.next() ) {
            //get variables from resultSet
            int cityId = result.getInt("cityId");
            String city = result.getString("city");
            int countryId = result.getInt("countryId");
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
            City resultCity = new City(cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy);
            allCities.add(resultCity);
        }
        
        //Return list of all cities
        return allCities;
    }
    
    
    //Get City
    public static City getCity(int ID) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT * FROM city WHERE cityId = '" + ID + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Access ResultSet, get CITY data from database
        while( result.next() ) {
            //get variables from resultSet
            int cityId = result.getInt("cityId");
            String city = result.getString("city");
            int countryId = result.getInt("countryId");
            String createDateInput = result.getString("createDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");

            //Create city & return it
            City resultCity = new City(cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy);
            return resultCity;
        }
        
        //If no result found, return NULL
        return null;
    }
    
    
    //Update City
    public static void updateCity(int cityID, String newCity) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( cityID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE city SET title = '" + newCity + "' WHERE cityId = " + cityID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update City
    public static void updateCountryId(int cityID, String newCountryId) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( cityID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE city SET countryId = '" + newCountryId + "' WHERE cityId = " + cityID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Delete City
    public static void deleteCity(int cityID) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( cityID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "DELETE FROM city WHERE cityId = " + cityID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Create City
    public static void createCity(String city, int countryId) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)VALUES('"+city+"', '"+countryId+"', NOW(), 'test', NOW(), 'test');";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
    }
    
    
    //Get cityID
    public static int getCityID(String city) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT cityId FROM city WHERE city  = '" + city + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        // Returns appointmentID if exists, returns -1 if it doesn't exist
        //exists
        if ( result.next() ) {
            int ID =  result.getInt("cityId");
            return ID;
        }
        //doesn't exist
        else {
            return -1;
        }   
    }
}
