package ViewControllers;

import static Utilities.CustomExceptions.confirmAction;
import static Utilities.CustomExceptions.errorMessage;
import DAO.AddressDAO;
import DAO.CityDAO;
import DAO.CountryDAO;
import DAO.CustomerDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCustomerRecordController implements Initializable {

    //GUI variables
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField address2Field;
    @FXML private TextField cityField;
    @FXML private TextField postalCodeField;
    @FXML private TextField countryField;
    @FXML private TextField phoneField;

    
    //Returns to MainSchedule Screen if cancel button pressed
    @FXML void CancelButtonPressed(ActionEvent e) throws Exception {
        if ( confirmAction("Cancel") )
            ReturnToMainScreen(e);
    }

    
    //Creates customer if save button is pressed, saves to DB
    @FXML void SaveButtonPressed(ActionEvent e) throws Exception {
        
        String name = nameField.getText();
        String address = addressField.getText();
        String address2 = address2Field.getText();      //This is the only optional field
        String city = cityField.getText();
        String postalCode = postalCodeField.getText();
        String country = countryField.getText();
        String phone = phoneField.getText();
        
        //If everything (minus Address2) isn't filled out, display warning
        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() || cityField.getText().isEmpty() || postalCodeField.getText().isEmpty() || countryField.getText().isEmpty() || phoneField.getText().isEmpty()) 
            errorMessage("Something is not filled out");
        
        //Everything is filled out:
        else {
            
            //Creates Country record in DB if it doesn't already exist
            if ( CountryDAO.getCountryID(country) < 0 )
                CountryDAO.createCountry(country);
                
            //Creates City record in DB if it doesn't already exist
            if ( CityDAO.getCityID(city) < 0 )
                CityDAO.createCity(city, CountryDAO.getCountryID(country));
                
            //Creates Address record in DB if it doesn't already exist
            if ( AddressDAO.getAddressID(address) < 0 )
                AddressDAO.createAddress(address, address2, CityDAO.getCityID(city), postalCode, phone);
            
            //Creates Customer record in DB, returns to MainSchedule screen
            CustomerDAO.createCustomer(name, AddressDAO.getAddressID(address));
            ReturnToMainScreen(e);
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
        // Nothing needs to be initialized
    }    
}
