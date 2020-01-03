package Utilities;

import static Utilities.TimeAndPlace.localToUTC;
import DAO.AppointmentDAO;
import Model.Appointment;
import java.time.ZonedDateTime;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class CustomExceptions {
    
    
    //Checks if appoinemtments overlaps, returns boolean
    public static boolean checkIfAppointmentOverlaps(Appointment apt) throws Exception {
        
        //Load times & ID from appointment in parameter
        ZonedDateTime passedStart = localToUTC(apt.getStart());
        ZonedDateTime passedEnd = localToUTC(apt.getEnd());
        int aptID = apt.getAppointmentID();
        
        //Check each appointment in database
        ObservableList<Appointment> appointments = AppointmentDAO.getAllAppointments();
        for (Appointment appointment: appointments) {
            
            //Load times & ID from each appointment in database
            ZonedDateTime currentStart = localToUTC(appointment.getStart());
            ZonedDateTime currentEnd = localToUTC(appointment.getEnd());
            int appointmentID = appointment.getAppointmentID();
            
            //If day matches...
            if (appointmentID != aptID && currentStart.toLocalDate().isEqual(passedStart.toLocalDate())) {
                
                //...and time overlaps, return true
                if ( currentStart.isBefore(passedEnd) && currentEnd.isAfter(passedStart) )
                    return true;
            }
        }
        
        //Else return false
        return false;
    }
    
    
    //Overloaded version of above, but takes ZonedDateTime inputs instead of Appointment
    public static boolean checkIfAppointmentOverlaps(ZonedDateTime start, ZonedDateTime end) throws Exception {
        
        //Check each appointment in database
        ObservableList<Appointment> appointments = AppointmentDAO.getAllAppointments();
        for (Appointment appointment: appointments) {
            
            //Load times & ID from each appointment in database
            ZonedDateTime currentStart = localToUTC(appointment.getStart());
            ZonedDateTime currentEnd = localToUTC(appointment.getEnd());
            
            //If day matches...
            if (currentStart.toLocalDate().isEqual(start.toLocalDate())) {
                //...and time overlaps, return true
                if (currentStart.isBefore(end) && currentEnd.isAfter(start))
                    return true;                
            }
        }
        
        //Else return false
        return false;
    }
    
    
    //Asks the user for confirmation on cancel/delete/exit options. 
    public static boolean confirmAction(String action) {
        //Setup alertbox.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm " + action);
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to " + action.toLowerCase() + "?");

        //Create Yes/No buttons.
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        //Group buttons and add them to alert box.
        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();
        
        //Returns true if yes button pressed.  
        return (result.get() == yesButton);
    }
    
    
    // --------------------------------------------------------------------------------------------------
    //                               LAMBDA EXPLAINATION
    //
    // Lambda functions were used with the following two methods for reduced code and greater efficiency.
    // An example of the "long way" to write an alert box can be found above in confirmAction()
    //
    // --------------------------------------------------------------------------------------------------
    
    
    public static void errorMessage(String message) {
        Platform.runLater(() -> {
            Alert dialog = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
            dialog.show();
        });
    }
    
    
    public static void fyiMessage(String message) {
        Platform.runLater(() -> {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            dialog.show();
        });
    }
}