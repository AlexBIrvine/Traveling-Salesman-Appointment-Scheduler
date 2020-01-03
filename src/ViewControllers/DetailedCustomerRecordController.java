package ViewControllers;

import static Utilities.CustomExceptions.confirmAction;
import DAO.AddressDAO;
import DAO.CityDAO;
import DAO.CountryDAO;
import DAO.CustomerDAO;
import Model.Address;
import Model.City;
import Model.Country;
import Model.Customer;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailedCustomerRecordController implements Initializable {
    
    //FXML variables
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField address2Field;
    @FXML private TextField cityField;
    @FXML private TextField postalCodeField;
    @FXML private TextField countryField;
    @FXML private TextField phoneField;
    
    //Model variables
    Customer selectedCustomer;
    Address selectedAddress;
    City selectedCity;
    Country selectedCountry;

    
    //Returns to MainSchedule Screen if cancel button pressed
    @FXML void CancelButtonPressed(ActionEvent e) throws Exception {
        if ( confirmAction("Cancel") )
            ReturnToMainScreen(e);
    }

    
    //Updates customer information then returns to MainSchedule Screen
    @FXML void SaveButtonPressed(ActionEvent e) throws Exception {
        
        //update CustomerName if change occured
        if (!selectedCustomer.getCustomerName().equals(nameField.getText())) 
            CustomerDAO.updateCustomerName(selectedCustomer.getCustomerID(), nameField.getText());

        //update Address if change occured
        if (!selectedAddress.getAddress().equals(addressField.getText())) 
            AddressDAO.updateAddress(selectedAddress.getAddressID(), addressField.getText());

        //update Address2 if change occured
        if (!selectedAddress.getAddress2().equals(address2Field.getText())) 
            AddressDAO.updateAddress2(selectedAddress.getAddressID(), address2Field.getText());

        //update City if change occured
        if (!selectedCity.getCity().equals(cityField.getText())) 
            CityDAO.updateCity(selectedCity.getCityID(), cityField.getText());

        //update PostalCode if change occured
        if (!selectedAddress.getPostalCode().equals(postalCodeField.getText())) 
            AddressDAO.updatePostalCode(selectedAddress.getAddressID(), postalCodeField.getText());

        //update Country if change occured
        if (!selectedCountry.getCountry().equals(countryField.getText())) 
            CountryDAO.updateCountry(selectedCountry.getCountryID(), countryField.getText());

        //update Phone if change occured
        if (!selectedAddress.getPhone().equals(phoneField.getText())) 
            AddressDAO.updatePhone(selectedCountry.getCountryID(), phoneField.getText());

        //Returns to main schedule screen
        ReturnToMainScreen(e);
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
    public void LoadData(Customer customer, Address address, City city, Country country) {
        
        //Loads local model data
        selectedCustomer = customer;
        selectedAddress = address;
        selectedCity = city;
        selectedCountry = country;
        
        //Sets text fields based on local models
        nameField.setText(String.valueOf(selectedCustomer.getCustomerName()));
        addressField.setText(String.valueOf(selectedAddress.getAddress()));
        address2Field.setText(String.valueOf(selectedAddress.getAddress2()));
        cityField.setText(String.valueOf(selectedCity.getCity()));
        postalCodeField.setText(String.valueOf(selectedAddress.getPostalCode()));
        countryField.setText(String.valueOf(selectedCountry.getCountry()));
        phoneField.setText(String.valueOf(selectedAddress.getPhone()));
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // No intilzation needed
    }    
    
    
}