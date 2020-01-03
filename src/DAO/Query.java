package DAO;

import static DAO.DataBaseConnection.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    //Create classwide variables
    private static String query;
    private static Statement statement;
    private static ResultSet result;
    
    public static void makeQuery (String inputQuery) throws SQLException {
        query = inputQuery.toLowerCase();
        
        //Create a Statement obect
        statement = conn.createStatement();

        //Executes select statements
        if ( query.startsWith("select") ){
            result = statement.executeQuery(inputQuery);
        }

        //Executes delete, insert, update statements
        else if (query.startsWith("delete") || 
            query.startsWith("insert") || 
            query.startsWith("update")
           )
            statement.executeUpdate(inputQuery);

        //Prints to console when unexpected statement is tried
        else 
            System.out.println("Invalid SQL statement");  
    }
    
    //returns ResultSet of query
    public static ResultSet getResult() {
        return result;
    }
}
