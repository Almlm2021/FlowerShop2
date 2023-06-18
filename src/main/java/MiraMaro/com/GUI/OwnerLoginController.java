package MiraMaro.com.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import MiraMaro.com.GUI.Utilities;
import MiraMaro.com.GUI.StartController;

public class OwnerLoginController {
	
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnBack;
    
    
    @FXML
    private void login(ActionEvent event) throws IOException {
        if (tfPassword.getText().isEmpty()) {
            Utilities.showError("Login", "Bitte Passwort eingeben!");
        } else {
        	if ("passwort".equals(tfPassword.getText())) {
                Utilities.showInfo("Login", "Willkommen Alma :)");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerMenu.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.show();
            }
        	else {
                Utilities.showInfo("Login", "Falsches Passwort!");
        		}
        	}
        }
    
    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startscreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
