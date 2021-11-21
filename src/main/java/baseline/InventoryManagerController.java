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

    @FXML private Button addItemButton;
    @FXML private TextField addedItemName;
    @FXML private TextField addedItemSerialNumber;
    @FXML private TextField addedItemValue;
    @FXML private Button loadInventoryButton;
    @FXML private TableColumn<?, ?> nameColumn;
    @FXML private TextField nameLookup;
    @FXML private Button removeAllItemsButton;
    @FXML private Button removeItemButton;
    @FXML private Button saveInventoryButton;
    @FXML private Button searchItemNameButton;
    @FXML private Button searchItemSerialNumberButton;
    @FXML private TableColumn<?, ?> serialNumberColumn;
    @FXML private TextField serialNumberLookup;
    @FXML private Label statusField;
    @FXML private TableView<?> tableView;
    @FXML private TableColumn<?, ?> valueColumn;

    @FXML
    void addItemButtonClicked(ActionEvent event)
    {
        // Create Item based on name, value, and serial number entered
        // Call createItem method
        // Check to see that new item is not a duplicate
    }

    @FXML
    void loadInventoryButtonClicked(ActionEvent event)
    {
        // Use filechooser to create file object
        // Create new file
        // Send all the items to the list

    }

    @FXML
    void removeAllItemsButtonClicked(ActionEvent event)
    {
        // Clear entire list of items
        // Clear table view
        // Reset index to 0

    }

    @FXML
    void removeItemButtonClicked(ActionEvent event)
    {
        // Remove item from list
        // Remove from table

    }

    @FXML
    void saveInventoryButtonClicked(ActionEvent event)
    {
        // Use filechooser object and ask user where they want to save their file
        // Pass all info of current list into file
        // For each item in inventory, copy information into file

    }

    

}
