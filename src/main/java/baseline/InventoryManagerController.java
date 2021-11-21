package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventoryManagerController
{

    @FXML
    private Button addItemButton;

    @FXML
    private TextField addedItemName;

    @FXML
    private TextField addedItemSerialNumber;

    @FXML
    private TextField addedItemValue;

    @FXML
    private Button editItemButton;

    @FXML
    private Button loadInventoryButton;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField nameLookup;

    @FXML
    private Button removeAllItemsButton;

    @FXML
    private Button removeItemButton;

    @FXML
    private Button saveInventoryButton;

    @FXML
    private Button searchItemNameButton;

    @FXML
    private Button searchItemSerialNumberButton;

    @FXML
    private TableColumn<?, ?> serialNumberColumn;

    @FXML
    private TextField serialNumberLookup;

    @FXML
    private Label statusField;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> valueColumn;

    @FXML
    void addItemButtonClicked(ActionEvent event) {

    }

    @FXML
    void editItemButtonClicked(ActionEvent event) {

    }

    @FXML
    void loadInventoryButtonClicked(ActionEvent event) {

    }

    @FXML
    void removeAllItemsButtonClicked(ActionEvent event) {

    }

    @FXML
    void removeItemButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveInventoryButtonClicked(ActionEvent event) {

    }

    @FXML
    void searchItemNameButtonClicked(ActionEvent event) {

    }

    @FXML
    void searchItemSerialNumberButtonClicked(ActionEvent event) {

    }

}
