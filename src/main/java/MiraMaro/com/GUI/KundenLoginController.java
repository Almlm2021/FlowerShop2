package MiraMaro.com.GUI;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import MiraMaro.com.DTO.CustomerDTO;
import MiraMaro.com.Services.CustomerService;




public class KundenLoginController {
	
	
    static CustomerDTO currentUser;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnLogin;
    @FXML
    private ToggleGroup rbToggle;
	
    private CustomerService customerService = CustomerService.getInstance();
	
    @FXML
    private void login(ActionEvent event) throws IOException {
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        try {
            currentUser = customerService.login(password, email);
            ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenProdukte.fxml"), rb);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (RuntimeException e) {
            e.printStackTrace();
        	Utilities.showError("error", e.getMessage());
        }
    }

    @FXML
    private void registerCustomer(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenRegistrierung.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
    @FXML
    private void back(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/startscreen.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
