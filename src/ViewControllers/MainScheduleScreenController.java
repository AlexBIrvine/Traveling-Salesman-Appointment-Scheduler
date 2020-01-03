package ViewControllers;

import static Utilities.CustomExceptions.confirmAction;
import static Utilities.CustomExceptions.errorMessage;
import static Utilities.CustomExceptions.fyiMessage;
import DAO.AddressDAO;
import DAO.AppointmentDAO;
import DAO.CityDAO;
import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.UserDAO;
import Model.Address;
import Model.Appointment;
import Model.City;
import Model.Country;
import Model.Customer;
import Model.User;
import Utilities.ReportCreator;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainScheduleScreenController implements Initializable {

    //GUI variables
    @FXML private ListView<Appointment> appointmentList;
    @FXML private DatePicker datePicker;
    @FXML private Label displayedDatesLabel;
    @FXML private Label nameLabel;
    @FXML private Label addressLabel;
    @FXML private Label address2Label;
    @FXML private Label cityLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label countryLabel;
    @FXML private Label phoneLabel;
    @FXML private VBox customerDetailsNode;
    
    //Radio Button GUI variables
    private ToggleGroup dateDisplayToggleGroup;
    @FXML private RadioButton weekRadioButton;
    @FXML private RadioButton monthRadioButton;
    
    //Model variables
    private Appointment selectedAppointment;
    private Customer selectedCustomer;
    private Address selectedAddress;
    private City selectedCity;
    private Country selectedCountry;
    
    //Appointment list & date variables
    private final ObservableList<Appointment> Appointments = FXCollections.observableArrayList();
    private LocalDate startDate;
    private LocalDate endDate;
    
    
    //Opens CreateAppointment Scene
    @FXML void createAppointmentPressed(ActionEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/CreateAppointmentRecord.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    
    //Opens CreateCustomer Scene
    @FXML void createCustomerPressed(ActionEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/CreateCustomerRecord.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    
    //Deletes selected appoinment
    @FXML void deleteAppointmentPressed(ActionEvent e) throws Exception {
        
        //If appointment is selected...
        if (appointmentList.getSelectionModel().getSelectedItem() != null) {
            
            //If user confirms deletion...
            if ( confirmAction("Delete appointment") ) {
                
                //Delete appointment, show confirmation message, update view
                AppointmentDAO.deleteAppointment(selectedAppointment.getAppointmentID());
                fyiMessage("Appointment deleted");
                updateAppointmentList();
            }
        }
        
        //No appointment selected, warn user
        else
            errorMessage("Please select an appointment to delete first.");
    }

    
    //Opens DeleteCustomer scene
    @FXML void deleteCustomerPressed(ActionEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/DeleteCustomerRecord.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    //Opens EditCustomer scene
    @FXML private void editAppointmentPressed(ActionEvent e) throws IOException {
        
        //If appointment is selected, open EditAppointment Scene
        if (appointmentList.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/DetailedAppointmentRecord.fxml"));
            Parent editAppointmentParent = loader.load();
            Scene editAppointmentScene = new Scene(editAppointmentParent);
            
            //access the controller and call a method
            DetailedAppointmentRecordController controller = loader.getController();
            controller.LoadData(selectedAppointment, selectedCustomer, selectedAddress, selectedCity, selectedCountry);
            
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(editAppointmentScene);
            window.show();
        }
        
        //No appointment selected, display error
        else
            errorMessage("Please select an appointment to edit.");
    }

    
    //Opens SelectCustomer scene
    @FXML private void editCustomerPressed(ActionEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/SelectCustomerRecord.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    
    //Creates report: Appointment by Customer
    @FXML void runAptByCustomerButtonPressed(ActionEvent e) throws Exception {
        //Create customers list, populate it from DB
        ObservableList<Customer> customers = CustomerDAO.getAllCustomers();
        
        //Create report based on each customoer
        for(Customer cust : customers) {
            
            //Clears existing report data
            PrintWriter pw = new PrintWriter("reports/Schedule of Customer-"+cust.getCustomerName()+".txt");
            pw.close();
            
            //Creates fresh report
            ReportCreator.ScheduleOfCustomerReport(cust);
        }
        
        //Alerts user that report has been created
        fyiMessage("Report(s) created!");
    }

    
    //Creates report: Appointment by Month
    @FXML void runAptByMonthButtonPressed(ActionEvent e) throws Exception {
        //Clears existing report data
        PrintWriter pw = new PrintWriter("reports/Appointments by month.txt");
        pw.close();
        
        //Creates fresh report
        ReportCreator.AppointmentsPerMonthReport();
        
        //Alerts user that report has been created
        fyiMessage("Report created!");
    }

    
    //Creates report: Appointment by User
    @FXML void runAptByUserButtonPressed(ActionEvent e) throws Exception {
        //Create users list, populate it from DB
        ObservableList<User> users = UserDAO.getAllUsers();
        
        //Create report based on each user
        for(User user : users) {
            //Clears existing report data
            PrintWriter pw = new PrintWriter("reports/Schedule of User-"+user.getUserName()+".txt");
            pw.close();
            
            //Creates fresh report
            ReportCreator.ScheduleOfUserReport(user);
        }
        
        //Alerts user that report has been created
        fyiMessage("Report(s) created!");
    }

    
    //Logs user out & returns to login screen
    @FXML private void logOutPressed(ActionEvent e) {
        
        //Confirms user action
        if ( confirmAction("Log out") ) {
            
            //If action is confirmed, change scene to LogIn
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
                Scene scene = new Scene(parent);
                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            
            //Displays an error message if any exception caught
            } catch (IOException ex) {
                errorMessage(ex.getMessage());
            }  
        } 
    }
    
    
    //Exits the program
    @FXML private void exitPressed(ActionEvent e) {
        //Confirms user action
        if ( confirmAction("Exit") ) {
            //Closes program
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    
    //Sets model & GUI info based on Appointment selected
    @FXML private void appoinmentSelected(MouseEvent e) {
        
        //If an appoinment is selected, display associated customer
        if (appointmentList.getSelectionModel().getSelectedItem() != null) {
            try {
            //populates models based on selectedAppointment
            selectedAppointment = appointmentList.getSelectionModel().getSelectedItem();
            selectedCustomer = CustomerDAO.getCustomer(selectedAppointment.getCustomerID());
            selectedAddress = AddressDAO.getAddress(selectedCustomer.getAddressID());
            selectedCity = CityDAO.getCity(selectedAddress.getCityID());
            selectedCountry = CountryDAO.getCountry(selectedCity.getCountryID());
            
            //Sets the text of customer information
            nameLabel.setText(selectedCustomer.getCustomerName());
            addressLabel.setText(selectedAddress.getAddress());
            address2Label.setText(selectedAddress.getAddress2());
            cityLabel.setText(selectedCity.getCity());
            postalCodeLabel.setText(selectedAddress.getPostalCode());
            countryLabel.setText(selectedCountry.getCountry());
            phoneLabel.setText(selectedAddress.getPhone());
            
            //Displays customer information
            customerDetailsNode.setVisible(true);
            
            //Displays an error message if any exception caught
            } catch (Exception ex) {
                errorMessage(ex.getMessage());
            }
        }
    }
    
    
    //If an update to the date picker is detected, update view
    @FXML void updatedDatePicker(ActionEvent event) {
        updateDateLabel();
        updateAppointmentList();
    }
    
    
    //If an update to the radio button is detected, update view
    public void radioButtonChanged(ActionEvent e) {
        updateDateLabel();
        updateAppointmentList();
    }
    
    
    //Updates date label and sets appropiate date range for selected appointments
    public void updateDateLabel() {
        
        LocalDate date = datePicker.getValue();
        
        //Month option
        if (this.dateDisplayToggleGroup.getSelectedToggle().equals(this.monthRadioButton)) {
            updateDateRange(date.withDayOfMonth(1), date.withDayOfMonth(date.lengthOfMonth()) );
            displayedDatesLabel.setText( date.getMonth().toString() + " " + date.getYear() );
            
        }
        //Week option
        if (this.dateDisplayToggleGroup.getSelectedToggle().equals(this.weekRadioButton)) {
            updateDateRange(date.with(DayOfWeek.MONDAY), date.with(DayOfWeek.SUNDAY));
            displayedDatesLabel.setText(startDate.getMonth().toString().substring(0, 3) + " " + startDate.getDayOfMonth() + " to " 
                                      + endDate.getMonth().toString().substring(0, 3) + " " + endDate.getDayOfMonth());
        }
    }
    
    
    //Updates global date variables for file
    public void updateDateRange(LocalDate start, LocalDate end) {
        startDate = start;
        endDate = end;
    }
    
    
    //Updates list of appointments
    public void updateAppointmentList() {
        
        try {
            //Resets appointment list
            Appointments.clear();
            
            //Populates appointment list
            Appointments.addAll(AppointmentDAO.getAllAppointments());
            appointmentList.setItems(getAppointmentsInRange(Appointments));
            appointmentList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            
            // Sets customer details to invisible
            customerDetailsNode.setVisible(false);
        } 
        
        //Displays an error message if any exception caught
        catch (Exception ex) {
            errorMessage(ex.getMessage());
        }
    }
    
    
    //Gets list of appointments within range of Date & Time selected
    public ObservableList<Appointment> getAppointmentsInRange(ObservableList<Appointment> appointments) {
        
        //creates blank appointment list to return
        ObservableList<Appointment> returnAppointments = FXCollections.observableArrayList();
        
        //loop through all appointments, get ones in range of date selection
        appointments.forEach((apt) -> {                                         //--------------------------------------------------------------//
            LocalDate startCal = apt.getStart().toLocalDate();                  //                     LAMBDA EXPLAINATION                      //
            LocalDate endCal = apt.getEnd().toLocalDate();                      //                                                              //
            if (startCal.isAfter(startDate) && endCal.isBefore(endDate))        //  Lambda used to replace for(Object obj: ObjectsList) syntax  //
                returnAppointments.add(apt);                                    //  for increased readability and shorter more efficient code   // 
        });                                                                     //--------------------------------------------------------------//

        //returns appointment list
        return returnAppointments;
    }
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Initialize toggle group and add radio buttons
        dateDisplayToggleGroup = new ToggleGroup();
        this.weekRadioButton.setToggleGroup(dateDisplayToggleGroup);
        this.monthRadioButton.setToggleGroup(dateDisplayToggleGroup);

        // Sets default datePicker value to today
        datePicker.setValue(LocalDate.now());
        updateDateLabel();
        
        //Initialize Appointment table
        updateAppointmentList();
    }    
}