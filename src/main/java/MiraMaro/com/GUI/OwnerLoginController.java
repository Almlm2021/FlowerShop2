package MiraMaro.com.GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import MiraMaro.com.Entities.Admin;
import javafx.scene.control.TextField;

public class OwnerLoginController {


    @FXML
    private PasswordField tcPassword;
    @FXML
    private TextField tcEmail;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnBack;


    @FXML
    private void login(ActionEvent event) throws IOException {
        String email = tcEmail.getText();
        String password = tcPassword.getText();
        if(Admin.DEFAULT_ADMIN.getEmail().equals(email) && Admin.DEFAULT_ADMIN.getPassword().equals(password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OwnerMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Utilities.showError("Fehler!", "Email oder Passwort inkorrekt!");
        }
    }





    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/startscreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
