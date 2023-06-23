package MiraMaro.com.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class StartController {
	
	
    private Label label;
    @FXML
    private Button btnOwner;
    @FXML
    private Button btnKunde;
    @FXML
    private Button btnExit;
    @FXML
    private ToggleGroup rbToggle;
	
    
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Click!");
        label.setText("Hello!");
    }
	
	
    @FXML
    private void kundenlogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ownerlogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OwnerLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
    
    
    
    
}
