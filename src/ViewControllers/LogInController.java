package ViewControllers;

import static Utilities.TimeAndPlace.alertIfAppointmentSoon;
import static Utilities.CustomExceptions.errorMessage;
import static Utilities.ReportCreator.makeLoginReport;
import static Utilities.TimeAndPlace.getCountry;
import static Utilities.TimeAndPlace.getLocale;
import DAO.UserDAO;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController implements Initializable {
    
    //GUI variables
    @FXML private TextField userNameField;
    @FXML private TextField passwordField;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label countryDisplayLabel;
    @FXML private Label countryLabel;
    
    //local variables
    User currentUser;
    String loginError;
    
    
    //Check if user exists in database
    private boolean CheckLoginUser() throws Exception {
        System.out.println("Attempting to get userID");
        int userID = UserDAO.getUserID(userNameField.getText());
        System.out.println("Got user ID");
        //If user exists, check if username & password match
        if (userID > 0) {
            System.out.println("Attempting to get user");
            User tempUser = UserDAO.getUser(userID);
            System.out.println("Got user");
            
            //username & password match, return true (okay to login as user)
            if (tempUser.getUserName().equals(userNameField.getText()) 
             && tempUser.getPassword().equals(passwordField.getText())) {
                currentUser = tempUser;
                return true;
            }
            //username & password don't match, return false
            else
                return false;
        }
        
        //User doesn't exist, return false
        else
            return false;
    }
    
    
    //Log in user after running checks
    @FXML void loginButtonPressed(ActionEvent e) {
        try {
            //If userName & password are correct, continue
            if (CheckLoginUser() == true){
                
                //Run logger & check if appoinents are soon
                System.out.println("login report start");
                makeLoginReport(userNameField.getText());
                System.out.println("check alert start");
                alertIfAppointmentSoon(currentUser);
                System.out.println("check alert end");
                
                //Load MainScheduleScreen
                System.out.println("Attempting to load main schedule screen");
                Parent parent = FXMLLoader.load(getClass().getResource("/View/MainScheduleScreen.fxml"));
                Scene scene = new Scene(parent);
                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            
            //If username & password is incorrect, tell user.  
            else
                errorMessage(loginError);
            
            //Exception caught if MySQL connection is not established.  
            } catch (Exception ex) {
                errorMessage("Error logging in - " + ex.getMessage());
            }
    }   
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Sets labels to appropiate language
        rb = ResourceBundle.getBundle("LoginFields", getLocale() );
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        countryDisplayLabel.setText(rb.getString("current_country"));
        loginError = rb.getString("login_error");
        countryLabel.setText( getCountry() );
    }       
}
