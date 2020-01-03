package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    
    private static final String databaseName = "U05NCh";
    private static final String DB_URL = "jdbc:mysql://3.227.166.251/" + databaseName;
    private static final String DB_FILE = "jdbc:sqlite:scheduler.db";
    private static final String userName = "U05NCh";
    private static final String password = "53688550297";
    private static final String driver = "com.mysql.jdbc.Driver";
    
    public static Connection conn;
    
    //Connects to database using above credentials
    public static void makeConnection() throws ClassNotFoundException, SQLException, Exception {
        //Creates a connection object
        Class.forName(driver);
        conn = DriverManager.getConnection(DB_URL, userName, password);
        System.out.println("Connection established");
        
    }
    
    //Connects to database using SQLite file
    public static void makeConnectionSQLite() throws ClassNotFoundException, SQLException, Exception {
        conn = DriverManager.getConnection(DB_FILE);
        System.out.println("Connection established");
        System.out.println("Using file: " + DB_FILE);
    }
    
    //Closes database connection
    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception {
        conn.close();
        System.out.println("Connection terminated");
    }
}
