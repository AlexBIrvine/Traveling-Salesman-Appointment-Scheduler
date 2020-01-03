package Utilities;

import DAO.AppointmentDAO;
import DAO.UserDAO;
import Model.Appointment;
import Model.Customer;
import Model.User;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Locale;
import javafx.collections.ObservableList;

public class ReportCreator {
    
    
    public static void AppointmentsPerMonthReport() throws IOException, Exception {
        
        //Gets appointments from DB, sets up calendar
        ObservableList<Appointment> Appointments = AppointmentDAO.getAllAppointments();
        Calendar cal = Calendar.getInstance();
        
        //Setup write file
        FileWriter fw = new FileWriter("reports/Appointments by month.txt", true);
        PrintWriter outputFile = new PrintWriter(fw);
        
        //Creates report header
        outputFile.println("Report of appointments per month\nCreated on: " + Calendar.getInstance().getTime());
        outputFile.println("---------------------------------------------------------------------------------\n");
        
        //prints out each month
        for (int month = 0 ; month < 12; month++) {
            cal.set(Calendar.MONTH, month);
            outputFile.println("\t\t\tAppointments for " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + "\n");
            
            //prints out appointments for the current month
            for (Appointment apt : Appointments) {
                if (apt.getStart().getMonthValue() == month+1)
                    outputFile.println(apt.toString());
            }
            outputFile.println("\n---------------------------------------------------------------------------------\n");
        }
        
        //Closes output file
        outputFile.close();
    }
    
   
    public static void ScheduleOfUserReport(User user) throws IOException, Exception {
        //Setup write file
        FileWriter fw = new FileWriter("reports/Schedule of User-"+user.getUserName()+".txt", true);
        PrintWriter outputFile = new PrintWriter(fw);
        
        //Creates report header
        int userID = user.getUserID();
        outputFile.println("User{"+user.getUserName()+"} Appointments\nCreated on: " + Calendar.getInstance().getTime());
        outputFile.println("---------------------------------------------------------------------------------\n");
        
        //Prints out appointments for the target user
        for (Appointment apt: AppointmentDAO.getAllAppointments()){
            if (apt.getUserID() == userID)
                outputFile.println(apt.toString());
        }
        
        //Creates new line (to seperate users), closes file
        outputFile.println("");
        outputFile.close();
    }
    
    
    public static void ScheduleOfCustomerReport(Customer cust) throws IOException, Exception {
        //Setup write file
        FileWriter fw = new FileWriter("reports/Schedule of Customer-"+cust.getCustomerName()+".txt", true);
        PrintWriter outputFile = new PrintWriter(fw);
        
        //Creates report header
        int custID = cust.getCustomerID();
        outputFile.println("User{ "+cust.getCustomerName()+" } Appointments\nCreated on: " + Calendar.getInstance().getTime());
        outputFile.println("---------------------------------------------------------------------------------\n");
        
        //prints out appointments for the current customer
        for (Appointment apt: AppointmentDAO.getAllAppointments()){
            if (apt.getCustomerID() == custID)
                outputFile.println(apt.toString());
        }
        
        //Creates new line (to seperate customer), closes file
        outputFile.println("");
        outputFile.close();
    }
      
    
    public static void makeLoginReport(String user) throws IOException, Exception {
        
        //Setup write file
        FileWriter fw = new FileWriter("reports/login info.txt", true);
        PrintWriter outputFile = new PrintWriter(fw);
        
        //Create strings for logging
        User selectedUser = UserDAO.getUser(UserDAO.getUserID(user));
        String userName = selectedUser.getUserName();
        String currentTime = java.util.Calendar.getInstance().getTime().toString();
        
        //write user login info, close connection
        System.out.println("Creating log");
        outputFile.println("User{"+userName+"} logged in at: " + currentTime);
        outputFile.close();
    }
}