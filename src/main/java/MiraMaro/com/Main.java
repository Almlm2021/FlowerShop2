package MiraMaro.com;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene welcomeScene, ownerScene, customerScene;

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;

        // Welcome scene
        Button btnOwner = new Button("Owner");
        Button btnCustomer = new Button("Customer");
        btnOwner.setMaxWidth(Double.MAX_VALUE);
        btnCustomer.setMaxWidth(Double.MAX_VALUE);
        btnOwner.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnCustomer.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        VBox welcomeVBox = new VBox(10, btnOwner, btnCustomer);
        welcomeVBox.setAlignment(Pos.CENTER);
        welcomeScene = new Scene(welcomeVBox, 300, 200);
        welcomeScene.setFill(Color.LIGHTBLUE);

        // Owner login scene
        VBox ownerVBox = createLoginForm(false);
        ownerScene = new Scene(ownerVBox, 300, 200);
        ownerScene.setFill(Color.LIGHTBLUE);
        btnOwner.setOnAction(e -> window.setScene(ownerScene));

        // Customer login scene
        VBox customerVBox = createLoginForm(true);
        customerScene = new Scene(customerVBox, 300, 200);
        customerScene.setFill(Color.LIGHTBLUE);
        btnCustomer.setOnAction(e -> window.setScene(customerScene));

        window.setScene(welcomeScene);
        window.setTitle("Welcome Page");
        window.show();
    }

    private VBox createLoginForm(boolean isCustomer) {
        Label labelTitle = new Label("Login");
        labelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        HBox usernameBox = new HBox();
        Label labelUsername = new Label("Username:");
        TextField fieldUsername = new TextField();
        fieldUsername.setStyle("-fx-background-color: white;");
        fieldUsername.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(fieldUsername, Priority.ALWAYS);
        usernameBox.getChildren().addAll(labelUsername, fieldUsername);

        HBox passwordBox = new HBox();
        Label labelPassword = new Label("Password:");
        PasswordField fieldPassword = new PasswordField();
        fieldPassword.setStyle("-fx-background-color: white;");
        fieldPassword.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(fieldPassword, Priority.ALWAYS);
        passwordBox.getChildren().addAll(labelPassword, fieldPassword);

        Button buttonLogin = new Button("Login");
        buttonLogin.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        buttonLogin.setMaxWidth(Double.MAX_VALUE);
        buttonLogin.setOnAction(e -> window.setScene(welcomeScene)); // Back to welcome scene after login

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(labelTitle, usernameBox, passwordBox, buttonLogin);

        if (isCustomer) {
            HBox emailBox = new HBox();
            Label labelEmail = new Label("Email:");
            TextField fieldEmail = new TextField();
            fieldEmail.setStyle("-fx-background-color: white;");
            fieldEmail.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(fieldEmail, Priority.ALWAYS);
            emailBox.getChildren().addAll(labelEmail, fieldEmail);

            vBox.getChildren().add(2, emailBox); // Insert the email box at the third position
        }

        return vBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
