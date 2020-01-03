package ViewControllers;

import static Utilities.CustomExceptions.confirmAction;
import static Utilities.CustomExceptions.errorMessage;
import DAO.AddressDAO;
import DAO.CityDAO;
import DAO.CountryDAO;
import DAO.CustomerDAO;
import Model.Address;
import Model.City;
import Model.Country;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class SelectCustomerRecordController implements Initializable {
    
    @FXML private ListView<Customer> customerList;
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private Customer selectedCustomer;
    private Address selectedAddress;
    private City selectedCity;
    private Country selectedCountry;

    
    //Returns to MainSchedule Screen if cancel button pressed
    @FXML void CancelButtonPressed(ActionEvent e) throws Exception {
        if ( confirmAction("Cancel") )
            ReturnToMainScreen(e);
    }
    
    
    //If a customer is selected, show EditCustomer screen
    @FXML private void editButtonPressed(ActionEvent e) throws IOException, Exception {
        
        //If a customer is selected, display associated customer
        if (customerList.getSelectionModel().getSelectedItem() != null) {
            
            selectedCustomer = customerList.getSelectionModel().getSelectedItem();
            selectedAddress = AddressDAO.getAddress(selectedCustomer.getAddressID());
            selectedCity = CityDAO.getCity(selectedAddress.getCityID());
            selectedCountry = CountryDAO.getCountry(selectedCity.getCountryID());
           
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/DetailedCustomerRecord.fxml"));
            Parent editCustomerParent = loader.load();
            Scene editCustomerScene = new Scene(editCustomerParent);
        
            //access the controller and call a method
            DetailedCustomerRecordController controller = loader.getController();
            controller.LoadData(selectedCustomer, selectedAddress, selectedCity, selectedCountry);
        
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(editCustomerScene);
            window.show();
        }
        
        //No customer is selcted, warn user
        else
            errorMessage("Please select a customer to edit!");
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
            //populate list of customers
            customers.addAll(CustomerDAO.getAllCustomers());
            customerList.setItems(customers);
            customerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } 
        
        //Displays error message if an exception is caught
        catch (Exception ex) {
            errorMessage(ex.getMessage());
        }
    }    
    
    
}