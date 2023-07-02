package MiraMaro.com.GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.ResourceBundle;


public class Utilities {

    public static void showError(String titleKey, String headerKey) {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(rb.getString(titleKey));
        error.setHeaderText(rb.getString(headerKey));
        error.showAndWait();
    }

    public static void showInfo(String titleKey, String headerKey) {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(rb.getString(titleKey));
        info.setHeaderText(rb.getString(headerKey));
        info.showAndWait();
    }


    public static boolean getConfirmation(String headerKey) {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Info");
        info.setHeaderText(rb.getString(headerKey));
        info.showAndWait();
        return info.getResult() == ButtonType.YES;
    }

    public static String getMessage(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        return rb.getString(key);
    }

}

