package MiraMaro.com.GUI;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import MiraMaro.com.DTO.ProductCreationDTO;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import MiraMaro.com.Services.ProductService;
import MiraMaro.com.DTO.ProductDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OwnerMenuController implements Initializable {

    private ProductService ow = null;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfColor;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TableView<ProductDTO> tblProducts;
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
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private RadioButton rbID;
    @FXML
    private RadioButton rbType;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private ToggleGroup tgSort;


    private ProductService productService;


    public void initialize(URL url, ResourceBundle rb) {
        productService = ProductService.getInstance();
        List<ProductDTO> products = productService.getAllProduct();
        tblProducts.setItems(FXCollections.observableArrayList(products));
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    @FXML
    private void actionAdd(ActionEvent event) {
        if (validation()) {
            ProductCreationDTO product = new ProductCreationDTO();
            product.setName(tfName.getText());
            product.setPrice(Double.parseDouble(tfPrice.getText()));
            product.setColor(tfColor.getText());
            product.setType(tfType.getText());
            product.setQuantity(Integer.parseInt(tfQuantity.getText()));
            productService.addProduct(product);
            clearFields();
            Utilities.showInfo("success", "prdadd");
            initialize(null, null);
            return;
        }
        Utilities.showError("error", "prdnotadd");
    }


    @FXML
    private void actionDelete(ActionEvent event) {
        ProductDTO product = tblProducts.getSelectionModel().getSelectedItem();
        if (product == null) {
            Utilities.showError("error", "prdchoose");
            return;
        }
        productService.deleteProduct(product.getId());
        initialize(null, null);
        clearFields();
        Utilities.showInfo("success", "prddelete");
    }



    @FXML
    private void actionUpdate(ActionEvent event) {
        ProductDTO product = tblProducts.getSelectionModel().getSelectedItem();
        if (product == null) {
            Utilities.showError("error", "prdchoose");
            return;
        }
        ProductCreationDTO productUpdate = new ProductCreationDTO();
        productUpdate.setName(tfName.getText());
        productUpdate.setPrice(Double.parseDouble(tfPrice.getText()));
        productUpdate.setColor(tfColor.getText());
        productUpdate.setType(tfType.getText());
        productUpdate.setQuantity(Integer.parseInt(tfQuantity.getText()));
        productService.updateProduct(product.getId(), productUpdate);
        initialize(null, null);
        clearFields();
        Utilities.showInfo("success", "prdupdate");
    }

    @FXML
    private void actionPop(MouseEvent event) {
        ProductDTO product = tblProducts.getSelectionModel().getSelectedItem();
        if (product == null) {
            return;
        }
        tfName.setText(product.getName());
        tfPrice.setText(String.valueOf(product.getPrice()));
        tfColor.setText(product.getColor());
        tfType.setText(product.getType());
        tfQuantity.setText(String.valueOf(product.getQuantity()));
    }


    @FXML
    private void actionBack(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OwnerLogin.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
        stage.show();
    }

    private boolean validation() {
        if (tfName.getText().isEmpty()) {
            Utilities.showError("prdmanage", "iname");
            return false;
        } else if (tfPrice.getText().isEmpty()) {
            Utilities.showError("prdmanage", "iprice");
            return false;
        } else if (tfQuantity.getText().isEmpty()) {
            Utilities.showError("prdmanage", "iquantitiy");
            return false;
        } else if (tfColor.getText().isEmpty()) {
            Utilities.showError("prdmanage", "icolor");
            return false;
        } else if (tfType.getText().isEmpty()) {
            Utilities.showError("prdmanage", "itype");
            return false;
        } else if (!tfPrice.getText().matches("^[0-9]*\\.?[0-9]+$")) {
            Utilities.showError("prdmanage", "uprice");
            return false;
        } else if (!tfQuantity.getText().matches("\\d+")) {
            Utilities.showError("prdmanage", "uquantity");
            return false;
        } else if (tfPrice.getText().matches("^[0-9]*\\.?[0-9]+$") && Double.parseDouble(tfPrice.getText()) <= 0) {
            Utilities.showError("prdmanage", "uprice");
            return false;
        } else if (tfQuantity.getText().matches("\\d+") && Integer.parseInt(tfQuantity.getText()) < 1) {
            Utilities.showError("prdmanage", "uquantity");
            return false;
        }
        return true;
    }

    private void clearFields() {
        tfName.setText("");
        tfQuantity.setText("");
        tfType.setText("");
        tfPrice.setText("");
        tfColor.setText("");
    }


}
