package MiraMaro.com.GUI;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import MiraMaro.com.DTO.ProductDTO;
import MiraMaro.com.Services.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class FavoritenController implements Initializable {

    @FXML
    private TableView<ProductDTO> tblFav;
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

    /**
     * Initializes controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<ProductDTO> favorites = CustomerService.getInstance().getFavorites(KundenLoginController.currentUser.getId());
        tblFav.getItems().addAll(favorites);
        tcID.setCellValueFactory(new PropertyValueFactory<ProductDTO, Integer>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("name"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<ProductDTO, Double>("price"));
        tcColor.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("color"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<ProductDTO, Integer>("quantity"));
        tcType.setCellValueFactory(new PropertyValueFactory<ProductDTO, String>("type"));

    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("messages", Main.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/KundenProdukte.fxml"), rb);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void removeFavourites(ActionEvent event) {
        CustomerService.getInstance().removeFromFavorite(tblFav.getSelectionModel().getSelectedItem().getId(), KundenLoginController.currentUser.getId());
        initialize(null, null);
        Utilities.getConfirmation("delpf");
    }

}
