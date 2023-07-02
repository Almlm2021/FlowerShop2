package MiraMaro.com.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
import MiraMaro.com.GUI.Main;


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

    private ResourceBundle rb;

    public void initialize(URL url, ResourceBundle rb) {
    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("Click!");
        label.setText("Hello!");
    }

    @FXML
    private void kundenlogin(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenLogin.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ownerlogin(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OwnerLogin.fxml"), rb);
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

    @FXML
    private void toenglish(ActionEvent event) {
        Main.setLocale(new Locale("en", "US"));
        restart();
    }

    @FXML
    private void todeutsch(ActionEvent event) {
        Main.setLocale(new Locale("de", "DE"));
        restart();
    }

    private void restart() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
        try {
            new Main().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
