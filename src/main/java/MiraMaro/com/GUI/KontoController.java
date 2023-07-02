package MiraMaro.com.GUI;

import java.io.IOException;
import java.util.ResourceBundle;

import MiraMaro.com.DTO.CustomerDTO;
import MiraMaro.com.Services.CustomerService;
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

public class KontoController {

    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnUpdate;

    @FXML
    private ToggleGroup rbToggle;

    private CustomerService customerService = CustomerService.getInstance();

    @FXML
    private void updateCustomer(ActionEvent event) throws IOException {
        if (isValid()) {
            CustomerDTO currentUser = KundenLoginController.currentUser;

            if (!currentUser.getEmail().equals(tfEmail.getText())) {
                customerService.changeEmail(currentUser.getId(), tfEmail.getText());
            }
            if (!currentUser.getName().equals(tfUser.getText())) {
                customerService.changeName(currentUser.getId(), tfUser.getText());
            }
            customerService.changePassword(currentUser.getId(), tfPassword.getText());

            Utilities.showInfo("success", "userupdate");
        } else
            Utilities.showError("error", "iall");
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenProdukte.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private boolean isValid() {
        if (tfUser.getText().isEmpty()) {
            Utilities.showError("cusreg", "iname");
            return false;
        } else if (tfEmail.getText().isEmpty()) {
            Utilities.showError("cusreg", "iemail");
            return false;
        } else if (tfPassword.getText().isEmpty()) {
            Utilities.showError("cusreg", "ipasswort");
            return false;
        } else {
            return true;
        }
    }


    @FXML
    private void back(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenProdukte.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
