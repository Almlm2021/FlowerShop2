package MiraMaro.com.GUI;



import MiraMaro.com.DTO.CartItemDTO;
import MiraMaro.com.Services.CartService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class EinkaufswagenController implements Initializable {

    @FXML
    private TextField tfTotalItems;
    @FXML
    private TextField tfTotalPrice;
    @FXML
    private TableView<CartItemDTO> tblItems;
    @FXML
    private TableColumn<CartItemDTO, Integer> tcID;
    @FXML
    private TableColumn<CartItemDTO, String> tcName;
    @FXML
    private TableColumn<CartItemDTO, Integer> tcQuantity;
    @FXML
    private TableColumn<CartItemDTO, Integer> tcPrice;
    @FXML
    private TextField tfQuantity;
    private List<CartItemDTO> cart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cart = CartService.getInstance().viewCart(KundenLoginController.currentUser.getCurrentCartId());
        if (cart == null)
            return;
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblItems.setItems(FXCollections.observableArrayList(cart));
        tfTotalItems.setText(String.valueOf(cart.size()));
        tfTotalPrice.setText(String.valueOf(cart.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum()));
    }


    @FXML
    private void removeFromCart(ActionEvent event) {
        CartItemDTO selectedItem = tblItems.getSelectionModel().getSelectedItem();
        cart.remove(selectedItem);
        CartService.getInstance().deleteItem(selectedItem.getId());
        initialize(null, null);
    }

    @FXML
    private void paceOrder(ActionEvent event) throws IOException {
        CartService.getInstance().placeOrder(KundenLoginController.currentUser.getCurrentCartId(), false);
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenProdukte.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void updateQuantity(ActionEvent event) {
        CartItemDTO cartItem = tblItems.getSelectionModel().getSelectedItem();
        if (cartItem == null)
            return;
        int quantity = Integer.parseInt(tfQuantity.getText());
        CartService.getInstance().updateQuantity(cartItem.getId(), quantity);
        initialize(null, null);
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
