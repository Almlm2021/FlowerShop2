package MiraMaro.com.GUI;

import java.io.IOException;
import MiraMaro.com.DTO.CustomerCreationDTO;
import MiraMaro.com.DTO.CustomerDTO;
import MiraMaro.com.Services.CustomerService;
import Repository.CustomerRepository;
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

public class KundenRegistrierungController {

    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnRegister;

    @FXML
    private ToggleGroup rbToggle;

    private CustomerService customerService = CustomerService.getInstance();
    private CustomerRepository customerRepository = CustomerRepository.getInstance();
    
    @FXML
    private void registerCustomer(ActionEvent event) throws IOException {
    	if (isValid()) {
        CustomerCreationDTO customerCreationDTO = new CustomerCreationDTO();
        customerCreationDTO.setName(tfUser.getText());
        customerCreationDTO.setEmail(tfEmail.getText());
        customerCreationDTO.setPassword(tfPassword.getText());

        CustomerDTO newCustomer = customerService.register(customerCreationDTO);

        if (customerRepository.findById(newCustomer.getId()) == null) {
        	Utilities.showError("Fehler", "User nicht erstellt");
            return;
        }
    	Utilities.showInfo("Erfolgreich", "User wurde erfolgreich erstellt");

        tfUser.clear();
        tfEmail.clear();
        tfPassword.clear();
    	}
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    

    private boolean isValid() {
        if (tfUser.getText().isEmpty()) {
            Utilities.showError("Regestrierung Kunde", "Bitte Namen eingeben");
            return false;
        } else if (tfEmail.getText().isEmpty()) {
            Utilities.showError("Regestrierung Kunde", "Bitte email eingeben");
            return false;
        } else if (tfPassword.getText().isEmpty()) {
            Utilities.showError("Regestrierung Kunde", "Bitte Passwort eingeben");
            return false;
        } else {
            return true;
        }
    }
    
    
    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
