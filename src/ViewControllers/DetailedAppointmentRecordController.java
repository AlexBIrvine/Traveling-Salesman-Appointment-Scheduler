package ViewControllers;

import static Utilities.CustomExceptions.confirmAction;
import static Utilities.CustomExceptions.errorMessage;
import static Utilities.TimeAndPlace.localToUTC;
import static Utilities.TimeAndPlace.utcToLocal;
import DAO.AppointmentDAO;
import DAO.CustomerDAO;
import Model.Address;
import Model.Appointment;
import Model.City;
import Model.Country;
import Model.Customer;
import Utilities.CustomExceptions;
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


public class DetailedAppointmentRecordController implements Initializable {
    
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
    
    //Model variables
    Appointment selectedAppointment;
    Appointment tempAppointment;
    Customer selectedCustomer;
    Address selectedAddress;
    City selectedCity;
    Country selectedCountry;

    
    //Returns to MainSchedule Screen if cancel button pressed
    @FXML void CancelButtonPressed(ActionEvent e) throws Exception {
        if ( confirmAction("Cancel") )
            ReturnToMainScreen(e);
    }

    
    //Updates appointment when save button is pressed
    @FXML void SaveButtonPressed(ActionEvent e) throws Exception {
        
        tempAppointment = selectedAppointment;
        
        //Displays error message if end time is before start time
        if ( Integer.parseInt(startHour.getValue())*100 + Integer.parseInt(startMinute.getValue()) > 
             Integer.parseInt(endHour.getValue())*100 + Integer.parseInt(endMinute.getValue()) )
                errorMessage("Please make sure that it doesn't end before it begins");
        
        else {
            //Updates type if it's different from original value
            if (!selectedAppointment.getType().equals(typeField.getText()))
                AppointmentDAO.updateType(selectedAppointment.getAppointmentID(), typeField.getText());

            //Updates start if it's different from original value
            if (startDate.getValue() != null && startHour.getValue() != null && startMinute.getValue() != null) {
                LocalDate startLocalDate = startDate.getValue();
                LocalTime startTime = LocalTime.of(Integer.parseInt(startHour.getValue()), Integer.parseInt(startMinute.getValue()));
                ZoneId localTimeZone = ZoneId.of(TimeZone.getDefault().getID());
                ZonedDateTime startLocalTime = ZonedDateTime.of(startLocalDate, startTime, localTimeZone);
                ZonedDateTime startUTC = localToUTC(startLocalTime);
                System.out.println(startUTC);
                tempAppointment.setStart(startUTC);
            }

            //Updates end if it's different from original value
            if (startDate.getValue() != null && endHour.getValue() != null && endMinute.getValue() != null) {
                LocalDate endLocalDate = startDate.getValue();
                LocalTime endTime = LocalTime.of(Integer.parseInt(endHour.getValue()), Integer.parseInt(endMinute.getValue()));
                ZoneId localTimeZone = ZoneId.of(TimeZone.getDefault().getID());
                ZonedDateTime endLocalTime = ZonedDateTime.of(endLocalDate, endTime, localTimeZone);
                ZonedDateTime endUTC = localToUTC(endLocalTime);
                System.out.println(endUTC);
                tempAppointment.setEnd(endUTC);
            }
        
            //Updates associated customer if it's different from original value
            if (customerList.getSelectionModel().getSelectedItem() != null)
                AppointmentDAO.updateCustomerId(selectedAppointment.getAppointmentID(), customerList.getSelectionModel().getSelectedItem().getCustomerID());

            //Checks if new start & end overlaps, updates if it doesn't & return to main screen 
            if (!CustomExceptions.checkIfAppointmentOverlaps(tempAppointment)) {

                AppointmentDAO.updateStart(selectedAppointment.getAppointmentID(), zonedDateTimeToString(tempAppointment.getStart()));
                AppointmentDAO.updateEnd(selectedAppointment.getAppointmentID(), zonedDateTimeToString(tempAppointment.getEnd()));
                ReturnToMainScreen(e);
            }

            //New start & end overlap existing record, display error
            else
                errorMessage("Not updating, overlapping records");
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


    //Gets model data and sets text field values
    public void LoadData(Appointment appointment, Customer customer, Address address, City city, Country country) {
        
        //Loads local model data
        selectedAppointment = appointment;
        selectedCustomer = customer;
        selectedAddress = address;
        selectedCity = city;
        selectedCountry = country;   
        
        //Sets GUI fields based on local models
        typeField.setText(selectedAppointment.getType());
        startDate.setValue(utcToLocal(selectedAppointment.getStart()).toLocalDate());
        startHour.setValue(String.valueOf(utcToLocal(selectedAppointment.getStart()).getHour()));
        startMinute.setValue(String.valueOf(utcToLocal(selectedAppointment.getStart()).getMinute()));
        endHour.setValue(String.valueOf(utcToLocal(selectedAppointment.getEnd()).getHour()));
        endMinute.setValue(String.valueOf(utcToLocal(selectedAppointment.getEnd()).getMinute()));
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
