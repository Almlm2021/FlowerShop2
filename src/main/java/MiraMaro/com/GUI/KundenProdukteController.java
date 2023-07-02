package MiraMaro.com.GUI;

import java.io.IOException;
import MiraMaro.com.Services.CustomerService;
import javafx.event.ActionEvent;
import MiraMaro.com.Services.CartService;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import MiraMaro.com.DTO.ProductDTO;
import MiraMaro.com.Services.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class KundenProdukteController implements Initializable {

    @FXML
    private TableView<ProductDTO> tcProducts;
    @FXML
    private TableColumn<ProductDTO, Integer> tcID;
    @FXML
    private TableColumn<ProductDTO, String> tcName;
    @FXML
    private TableColumn<ProductDTO, Double> tcPrice;
    @FXML
    private TableColumn<ProductDTO, String> tcColor;
    @FXML
    private TableColumn<ProductDTO, Integer> tcQuantity;
    @FXML
    private TableColumn<ProductDTO, String> tcType;
    @FXML
    private TextField tfID;

    private ProductService productService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productService = ProductService.getInstance();
        List<ProductDTO> products = productService.getAllProduct();
        ObservableList<ProductDTO> productObservableList = FXCollections.observableArrayList(products);
        tcID.setCellValueFactory(new PropertyValueFactory<ProductDTO, Integer>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("name"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<ProductDTO, Double>("price"));
        tcColor.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("color"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<ProductDTO, Integer>("quantity"));
        tcType.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("type"));
        tcProducts.setItems(productObservableList);
    }


    @FXML
    private void ausloggen(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenLogin.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToCart(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Einkaufswagen.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToOrders(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bestellungen.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToKonto(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Konto.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void addToCart(ActionEvent event) {
        ProductDTO selectedProduct = tcProducts.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Utilities.showError("error", "prdchoose");
            return;
        } else {
            int quantity = 1;
            if (selectedProduct.getQuantity() < quantity) {
                Utilities.showError("error", "noprd");
                return;
            } else {
                CartService.getInstance().addItemToCart(selectedProduct.getId(), KundenLoginController.currentUser.getCurrentCartId(), quantity);
                selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
                tcProducts.refresh();
                Utilities.getConfirmation("prdadd");
            }
        }
    }

    @FXML
    private void addToFav(ActionEvent event) {
        CustomerService.getInstance().addToFavorite(tcProducts.getSelectionModel().getSelectedItem().getId(), KundenLoginController.currentUser.getId());
    }

    @FXML
    private void goToFav(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Favoriten.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void colorSort(ActionEvent event) {
        List<ProductDTO> products = productService.getAllProduct();
        products.sort(Comparator.comparing(ProductDTO::getColor));
        ObservableList<ProductDTO> productObservableList = FXCollections.observableArrayList(products);
        tcProducts.setItems(productObservableList);
    }

    @FXML
    private void priceSort(ActionEvent event) {
        List<ProductDTO> products = productService.getAllProduct();
        products.sort(Comparator.comparing(ProductDTO::getPrice));
        ObservableList<ProductDTO> productObservableList = FXCollections.observableArrayList(products);
        tcProducts.setItems(productObservableList);
    }

    @FXML
    private void typeSort(ActionEvent event) {
        List<ProductDTO> products = productService.getAllProduct();
        products.sort(Comparator.comparing(ProductDTO::getType));
        ObservableList<ProductDTO> productObservableList = FXCollections.observableArrayList(products);
        tcProducts.setItems(productObservableList);
    }

    @FXML
    private void findID(ActionEvent event) {
        List<ProductDTO> products = productService.getAllProduct();
        products = products.stream().filter(p -> String.valueOf(p.getId()).equals(tfID.getText())).toList();
        ObservableList<ProductDTO> productObservableList = FXCollections.observableArrayList(products);
        tcProducts.setItems(productObservableList);
    }

}
