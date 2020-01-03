package ViewControllers;

import static Utilities.CustomExceptions.checkIfAppointmentOverlaps;
import static Utilities.CustomExceptions.confirmAction;
import static Utilities.CustomExceptions.errorMessage;
import static Utilities.TimeAndPlace.localToUTC;
import DAO.AppointmentDAO;
import DAO.CustomerDAO;
import Model.Customer;
import static Utilities.TimeAndPlace.zonedDateTimeToString;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAppointmentRecordController implements Initializable {
    
    //FXML variables
    @FXML private TextField typeField;
    @FXML private DatePicker startDate;
    @FXML private ComboBox<String> startHour;
    @FXML private ComboBox<String> startMinute;
    @FXML private ComboBox<String> endHour;
    @FXML private ComboBox<String> endMinute;
    @FXML private ListView<Customer> customerList;
    
    //List variables
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<String> hours = FXCollections.observableArrayList();
    private final ObservableList<String> minutes = FXCollections.observableArrayList();
    
    
    //Returns to MainSchedule Screen if cancel button pressed
    @FXML void CancelButtonPressed(ActionEvent e) throws Exception {
        if ( confirmAction("Cancel") )
            ReturnToMainScreen(e);
    }

    
    //Creates Appointment if save button is pressed
    @FXML void SaveButtonPressed(ActionEvent e) throws Exception {
        
        //Displays error message if all fields are not filled out
        if (typeField.getText().isEmpty() || startDate.getValue() == null || startHour.getValue() == null || startMinute.getValue() == null || endHour.getValue() == null || endMinute.getValue() == null || customerList.getSelectionModel().getSelectedItem() == null)
            errorMessage("Something is not filled out");

        //Displays error message if end time is before start time
        else if ( Integer.parseInt(startHour.getValue())*100 + Integer.parseInt(startMinute.getValue()) > 
                  Integer.parseInt(endHour.getValue())*100 + Integer.parseInt(endMinute.getValue()) )
                    errorMessage("Please make sure that it doesn't end before it begins");
        
        //Everything is filled out, attempt to create Appointment
        else {
            
            //Gets start date & local time zone
            LocalDate startLocalDate = startDate.getValue();
            ZoneId localTimeZone = ZoneId.of(TimeZone.getDefault().getID());
            
            //Gets start time info, converts it to UTC
            LocalTime startTime = LocalTime.of(Integer.parseInt(startHour.getValue()), Integer.parseInt(startMinute.getValue()));
            ZonedDateTime startLocalTime = ZonedDateTime.of(startLocalDate, startTime, localTimeZone);
            ZonedDateTime startUTC = localToUTC(startLocalTime);
            
            //Gets end time info, converts it to UTC
            LocalTime endTime = LocalTime.of(Integer.parseInt(endHour.getValue()), Integer.parseInt(endMinute.getValue()));
            ZonedDateTime endLocalTime = ZonedDateTime.of(startLocalDate, endTime, localTimeZone);
            ZonedDateTime endUTC = localToUTC(endLocalTime);
            
            //Checks if appointment overlaps existing appoinements...
            if ( !checkIfAppointmentOverlaps(startUTC, endUTC) ) {
                //Converts ZonedDateTime into Timestamp for MySQL entry
                //Timestamp startEntry = Timestamp.valueOf(startUTC.toLocalDateTime());
                //Timestamp endEntry = Timestamp.valueOf(endUTC.toLocalDateTime());
                String startEntry = zonedDateTimeToString(startUTC);
                String endEntry = zonedDateTimeToString(endUTC);
                
                
                //...and inserts new appointment into DB if no overlap exists.  Returns to Main Schedule Screen
                AppointmentDAO.createAppointment(customerList.getSelectionModel().getSelectedItem().getCustomerID(), 1, "not needed", "not needed", "not needed", "not needed", typeField.getText(), "not needed", startEntry, endEntry);
                ReturnToMainScreen(e);
            }
            
            //Appoinemtn overlaps, display warning message
            else
                errorMessage("This overlaps existing appointment time!");
        }        
    }
        
    
    //Return to MainSchedule Screen logic
    public void ReturnToMainScreen(ActionEvent e) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/View/MainScheduleScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            // Intialize Combo Boxes
            hours.addAll("08", "09", "10", "11", "12", "13", "14", "15", "16", "17");
            minutes.addAll("00", "15", "30", "45");
            startHour.setItems(hours);
            startMinute.setItems(minutes);
            endHour.setItems(hours);
            endMinute.setItems(minutes);
            
            // Initialize customers
            customers.addAll(CustomerDAO.getAllCustomers());
            customerList.setItems(customers);
            customerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } 
        
        //Displays error if exception is caught
        catch (Exception ex) {
            errorMessage("Message from Edit Appointment Initialize: " + ex.getMessage());
        }
    }    
    
    
}
