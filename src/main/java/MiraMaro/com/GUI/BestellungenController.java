package MiraMaro.com.GUI;


import MiraMaro.com.DTO.CartDTO;
import MiraMaro.com.Entities.CartStatus;
import MiraMaro.com.Services.CartService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class BestellungenController implements Initializable {

    @FXML
    private TableView<CartDTO> tblOrder;
    @FXML
    private TableColumn<CartDTO, Integer> tcID;
    @FXML
    private TableColumn<CartDTO, Double> tcPrice;
    @FXML
    private TableColumn<CartDTO, String> tcStatus;
    @FXML
    private TableColumn<CartDTO, String> tcBouq;
    @FXML
    private TextArea taDetails;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<CartDTO> orders = CartService.getInstance().viewCarts(KundenLoginController.currentUser.getId()).stream().filter(cartDTO -> cartDTO.getStatus().equals(CartStatus.PLACED)).toList();
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum()).asObject());
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcBouq.setCellValueFactory(new PropertyValueFactory<>("bouquet"));
        tblOrder.getItems().addAll(orders);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenProdukte.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void tblClick(MouseEvent mouseEvent) {
        CartDTO cartDTO = tblOrder.getSelectionModel().getSelectedItem();
        if (cartDTO == null) return;
        taDetails.setText("Bestellnummer: " + cartDTO.getId() + "\n" +
                "Preis: " + cartDTO.getItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum() + "\n" +
                "Status: " + cartDTO.getStatus() + "\n" +
                "Ist ein Blumenstrauß: " + (cartDTO.isBouquet() ? "ja" : "nein"));
    }


}


