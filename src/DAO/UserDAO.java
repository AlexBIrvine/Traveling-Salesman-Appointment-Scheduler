package DAO;

import Model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {
    //Get All Users
    public static ObservableList<User> getAllUsers() throws SQLException, Exception {
        //Declare return & SQL statement variables
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM user";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Loop through resultSet, get users from database
        while( result.next() ) {
            //get variables from resultSet
            int userId = result.getInt("userId");
            String userName = result.getString("userName");
            String password = result.getString("password");
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
            User resultUser = new User(userId, userName, password, active, createDate, createdBy, lastUpdate, lastUpdateBy);
            allUsers.add(resultUser);
        }
        //Return list of all cities
        return allUsers;
    }

    
    //Get User
    public static User getUser(int ID) throws SQLException, Exception {
        
        //Declare SQL statement variable
        String sqlStatement = "SELECT * FROM user WHERE userId = '" + ID + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        //Access ResultSet, get User data from database
        while( result.next() ) {
            //get variables from resultSet
            int userId = result.getInt("userId");
            String userName = result.getString("userName");
            String password = result.getString("password");
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
            
            //Create city & return it
            User resultUser = new User(userId, userName, password, active, createDate, createdBy, lastUpdate, lastUpdateBy);
            return resultUser;
        }
        
        //If no result found, return null
        return null;
    }

    
    //Update userName
    public static void updateUserName(int userID, String newUserName) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( userID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE user SET userName = '" + newUserName + "' WHERE userId = " + userID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    
    
    //Update password
    public static void updatePassword(int userID, String newPassword) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( userID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "UPDATE user SET password = '" + newPassword + "' WHERE userId = " + userID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);
        }
    }
    

    //Delete User
    public static void deleteUser(int userID) throws SQLException, Exception {
        
        //Only run if ID is positive number.  If 0 or negative, record doesn't exist.
        if( userID > 0 ) {
            //Declare SQL statement
            String sqlStatement = "DELETE FROM user WHERE userId = " + userID;
            
            //Make SQL query
            Query.makeQuery(sqlStatement);

        }
    }

    
    //Create User
    public static void createUser(String userName, String password) throws SQLException, Exception {
        //Declare SQL statement
        String sqlStatement = "INSERT INTO user (userName, password, active, createDate, createdBy, lastUpdate, lastUpdateBy)VALUES('"+userName+"', '"+password+"', 1, NOW(), 'test', NOW(), 'test');";
            
        //Make SQL query
        Query.makeQuery(sqlStatement);

    }
    
    
    //Get UserId
    public static int getUserID(String userName) throws SQLException, Exception {
        //Declare SQL statement variable
        String sqlStatement = "SELECT userId FROM user WHERE userName  = '" + userName + "'";
        
        //Make SQL query
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        // Returns appointmentID if exists, returns -1 if it doesn't exist
        //exists
        if ( result.next() ) {
            int ID =  result.getInt("userId");
            return ID;
        }
        //doesn't exist
        else {
            return -1;
        }   
    }
}


