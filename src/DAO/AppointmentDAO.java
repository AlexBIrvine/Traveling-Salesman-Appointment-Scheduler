package DAO;

import Model.Appointment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentDAO {
    //Get All Appointments
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception {
        //Declare return & SQL statement variables
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM appointment";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Loop through resultSet, get appointments from database
        while( result.next() ) {
            //get variables from resultSet
            int appointmentID = result.getInt("appointmentId");
            int customerID = result.getInt("customerId");
            int userID = result.getInt("userId");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String contact = result.getString("contact");
            String type = result.getString("type");
            String URL = result.getString("URL");
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String startInput = result.getString("start");
            LocalDateTime startDateLocal = LocalDateTime.parse(startInput, formatter);             
            ZonedDateTime start = startDateLocal.atZone(ZoneId.of("UTC"));
            
            String endInput = result.getString("end");
            LocalDateTime endDateLocal = LocalDateTime.parse(endInput, formatter);             
            ZonedDateTime end = endDateLocal.atZone(ZoneId.of("UTC"));
            String createDateInput = result.getString("createDate");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");
            
            //create Address & add to allAddresses
            Appointment resultAppointment = new Appointment(appointmentID, customerID, userID, title, description, location, contact, type, URL, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);
            allAppointments.add(resultAppointment);
        }
        
        //Return list of all countries
        return allAppointments;
    }
    
    
    //Get Appointment
    public static Appointment getAppointment(int ID) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT * FROM appointment WHERE appointmentId = '" + ID + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Access ResultSet, get appointment data from database
        while( result.next() ) {
            //get variables from resultSet
            int appointmentID = result.getInt("appointmentId");
            int customerID = result.getInt("customerId");
            int userID = result.getInt("userId");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String contact = result.getString("contact");
            String type = result.getString("type");
            String URL = result.getString("URL");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String startInput = result.getString("start");
            LocalDateTime startDateLocal = LocalDateTime.parse(startInput, formatter);             
            ZonedDateTime start = startDateLocal.atZone(ZoneId.of("UTC"));
            String endInput = result.getString("end");
            LocalDateTime endDateLocal = LocalDateTime.parse(endInput, formatter);             
            ZonedDateTime end = endDateLocal.atZone(ZoneId.of("UTC"));
            String createDateInput = result.getString("createDate");
            LocalDateTime createDateLocal = LocalDateTime.parse(createDateInput, formatter);             
            ZonedDateTime createDate = createDateLocal.atZone(ZoneId.of("UTC"));
            String createdBy = result.getString("createdBy");
            String lastUpdateInput = result.getString("lastUpdate");
            LocalDateTime lastDateLocal = LocalDateTime.parse(lastUpdateInput, formatter);             
            ZonedDateTime lastUpdate = lastDateLocal.atZone(ZoneId.of("UTC"));
            String lastUpdateBy = result.getString("lastUpdateBy");

            //Create appointment & return it
            Appointment resultAppointment = new Appointment(appointmentID, customerID, userID, title, description, location, contact, type, URL, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);
            return resultAppointment;
        }
        
        //If no result found, return NULL
        return null;
    }
    
    
    //Update Customer ID
    public static void updateCustomerId(int appointmentID, int newCustomerId) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET customerId = '" + newCustomerId + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update Appointment title
    public static void updateTitle(int appointmentID, String newTitle) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET title = '" + newTitle + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update Appointment description
    public static void updateDescription(int appointmentID, String newDescription) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET description = '" + newDescription + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update Appointment location
    public static void updateLocation(int appointmentID, String newLocation) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET location = '" + newLocation + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update Appointment contact
    public static void updateContact(int appointmentID, String newContact) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET contact = '" + newContact + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update Appointment type
    public static void updateType(int appointmentID, String newType) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET type = '" + newType + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update Appointment URL
    public static void updateURL(int appointmentID, String newURL) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET url = '" + newURL + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update Appointment start
    public static void updateStart(int appointmentID, String newStart) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET start = '" + newStart + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
     //Update Appointment end
    public static void updateEnd(int appointmentID, String newEnd) throws SQLException, Exception {

        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE appointment SET end = '" + newEnd + "' WHERE appointmentId = " + appointmentID;

            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }   
    
    
    //Delete Appointment
    public static void deleteAppointment(int appointmentID) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( appointmentID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "DELETE FROM appointment WHERE appointmentId = " + appointmentID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Create Appointment
    public static void createAppointment(int customerId, int userId, String title, String description, String location, String contact, String type, String URL, Timestamp start, Timestamp end) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, URL, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)VALUES('"+customerId+"', '"+userId+"', '"+title+"', '"+description+"', '"+location+"', '"+contact+"', '"+type+"', '"+URL+"', '"+start+"', '"+end+"',  NOW(), 'test', NOW(), 'test');";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
    }
    
    
    //Create Appointment (overloaded for SQLite)
    public static void createAppointment(int customerId, int userId, String title, String description, String location, String contact, String type, String URL, String start, String end) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, URL, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)VALUES('"+customerId+"', '"+userId+"', '"+title+"', '"+description+"', '"+location+"', '"+contact+"', '"+type+"', '"+URL+"', '"+start+"', '"+end+"',  CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test');";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
    }
    
    
    //Get AppointmentID from title
    public static int getAppointmentID(String title) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT appointmentId FROM appointment WHERE title  = '" + title + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        // Returns appointmentID if exists, returns -1 if it doesn't exist
        //exists
        if ( result.next() ) {
            int ID =  result.getInt("appointmentId");
            return ID;
        }
        //doesn't exist
        else {
            return -1;
        }   
    }
}