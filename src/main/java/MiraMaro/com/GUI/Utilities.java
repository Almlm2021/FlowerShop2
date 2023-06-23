package MiraMaro.com.GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Utilities {

    public static void showError(String title, String header) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(title);
        error.setHeaderText(header);
        error.showAndWait();
    }

    public static void showInfo(String title, String header) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(title);
        info.setHeaderText(header);
        info.showAndWait();
    }


    public static boolean getConfirmation(String header) {
        Alert alert = new Alert(AlertType.CONFIRMATION, header, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

}
