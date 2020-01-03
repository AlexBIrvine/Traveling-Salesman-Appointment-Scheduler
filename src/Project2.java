import DAO.DataBaseConnection;
import static Utilities.CustomExceptions.errorMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Project2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
        //Opens database connection
        DataBaseConnection.makeConnectionSQLite();
        
        //Load login screen
        Parent root = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        //Shows alert if exception found
        } catch(Exception ex) {
            errorMessage("Internet connection is needed to use this program\nPlease repair connection and run again\n\n");
        }
        
    }
    
    @Override
    public void stop() throws Exception {
        // Closes database connection
        DataBaseConnection.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
