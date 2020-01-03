package ViewControllers;

import static Utilities.CustomExceptions.confirmAction;
import static Utilities.CustomExceptions.errorMessage;
import DAO.CustomerDAO;
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


public class DeleteCustomerRecordController implements Initializable {

    
    //Customer list variables
    @FXML private ListView<Customer> customerList;
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();

    
    //Returns to MainSchedule Screen if cancel button pressed
    @FXML void CancelButtonPressed(ActionEvent e) throws Exception {
        if ( confirmAction("Cancel") )
            ReturnToMainScreen(e);
    }

    
    //Deletes customer record when delete button is pressed
    @FXML void DeleteButtonPressed(ActionEvent e) {
        
        //If a customer is selected...
        if (customerList.getSelectionModel().getSelectedItem() != null) {
            
            //...and they confirm deletion...
            if ( confirmAction("Delete customer") ) {
                try {
                    //...delete the customer if they don't have an associated appointment, then return to MainSchedule screen
                    CustomerDAO.deleteCustomer(customerList.getSelectionModel().getSelectedItem().getCustomerID());
                    ReturnToMainScreen(e);    
                } 
                
                //Customer is attached to existing appointment, display error message
                catch (Exception ex) {
                   errorMessage("Cannot delete customer if they have an appointment.\nPlease remove them from appointments first!");
                }
            }
        }
        
        //No customer selected, display error message
        else
            errorMessage("Please select a customer to delete.");
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
            // Initialize customers
            customers.addAll(CustomerDAO.getAllCustomers());
            customerList.setItems(customers);
            customerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } 
        
        //Displays error if exception is caught
        catch (Exception ex) {
            errorMessage(ex.getMessage());
        }
    }    
}