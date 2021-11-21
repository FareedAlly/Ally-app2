package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


import java.util.Locale;

public class InventoryManagerController
{
    private int index = 0;
    CheckInfo check = new CheckInfo();
    private final ObservableList<Item> list = FXCollections.observableArrayList();

    @FXML private Button addItemButton;
    @FXML private TextField addedItemName;
    @FXML private TextField addedItemSerialNumber;
    @FXML private TextField addedItemValue;
    @FXML private Button loadInventoryButton;
    @FXML private TableColumn<Item, String> nameColumn;
    @FXML private TextField nameLookup;
    @FXML private Button removeAllItemsButton;
    @FXML private Button removeItemButton;
    @FXML private Button saveInventoryButton;
    @FXML private Button searchItemNameButton;
    @FXML private TableColumn<Item, String> serialNumberColumn;
    @FXML private Label statusField;
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> valueColumn;

    @FXML
    void initialize()
    {
        // Required to initialize and add tasks to the table
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        nameColumn.setOnEditCommit(event ->
        {
            // If description is valid, then add it to the table
            if(check.checkName(event.getNewValue()))
            {
                Item item = event.getRowValue();
                item.setName(event.getNewValue());

                statusField.setText("Name updated. ");
            }

            else
            {
                statusField.setText("Task not updated. ");
                tableView.refresh();
            }
        });

        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        valueColumn.setOnEditCommit(event ->
        {
            // If description is valid, then add it to the table
            if(check.checkValue(event.getNewValue()))
            {
                Item item = event.getRowValue();
                item.setValue(event.getNewValue());

                statusField.setText("Completion Date updated. ");
            }

            else
            {
                statusField.setText("Completion Date not updated. ");
                tableView.refresh();
            }
        });

        serialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        serialNumberColumn.setOnEditCommit(event ->
        {
            Item item = event.getRowValue();
            item.setSerialNumber(event.getNewValue());

            tableView.refresh();
        });
    }

    public void addItemToInventory(String name, String value, String serialNumber)
    {
        // Add item to inventory based on parameters
        Item.getInventory().add(new Item(name, value, serialNumber));
        list.add(new Item(name, value, serialNumber));

    }

    public void clear()
    {
        addedItemName.clear();
        addedItemValue.clear();
        addedItemSerialNumber.clear();
    }

    public void display()
    {
        // Display the item and add it to the current index
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));

        // Update index
        tableView.getItems().add(Item.getInventory().get(index));
        index++;
    }

    public void addItemAndDisplay(String name, String value, String serialNumber)
    {
        if(!check.checkSerialNumber(serialNumber))
        {
            statusField.setText("Entered an invalid serial number ");
        }
        if(!check.checkName(name))
        {
            statusField.setText("Entered an invalid name ");
        }
        if(!check.checkValue(value))
        {
            statusField.setText("Entered an invalid value");
        }
        if(check.checkName(name) && check.checkValue(value) && check.checkSerialNumber(serialNumber))
        {
            // Item's information is acceptable, add it to inventory and display
            statusField.setText("Item added successfully ! ");
            addItemToInventory(name, value, serialNumber);
            display();
        }

        clear();

    }



    public void createItem()
    {
        //Check to see if user is entering a serial number that is already in inventory
        boolean copy = false;


        // Go through all the items in inventory, and if serial number present tell user

        for(Item item : Item.getInventory())
        {
            if(item.getName().equals(addedItemName.getText()))
            {
                statusField.setText("Item is already on the list ");

                copy = true;
            }
            if(item.getSerialNumber().equals(addedItemSerialNumber.getText()))
            {
                statusField.setText("Serial number is already on the list ");
                copy = true;
            }
        }

        if(!copy)
        {
            // If not a copy, then add the item to the inventory by calling a method
            addItemAndDisplay(addedItemName.getText(), addedItemValue.getText(), addedItemSerialNumber.getText());
        }

}



    @FXML
    void addItemButtonClicked(ActionEvent event)
    {
        // Create Item based on name, value, and serial number entered
        // Call createItem method
        createItem();


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
        Item.getInventory().clear();

        // Clear table view
        tableView.getItems().clear();

        // Reset index to 0
        index = 0;
        clear();
    }

    @FXML
    void removeItemButtonClicked(ActionEvent event)
    {
        Item item = tableView.getSelectionModel().getSelectedItem();

        if(item != null)
        {

            // Remove item from list
            index--;

            // Remove from table
            tableView.getItems().remove(item);
        }
    }

    @FXML
    void saveInventoryButtonClicked(ActionEvent event)
    {
        // Use filechooser object and ask user where they want to save their file
        // Pass all info of current list into file
        // For each item in inventory, copy information into file

    }

    @FXML
    void searchItemNameButtonClicked(ActionEvent event)
    {
        searchList();

    }

    public void searchList()
    {
        FilteredList<Item> filteredList = new FilteredList<>(list, b->true);

        nameLookup.textProperty().addListener((observable, oldValue, newValue)->{
            filteredList.setPredicate(Item -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String filter = newValue.toLowerCase(Locale.ROOT);

                if(Item.getName().toLowerCase(Locale.ROOT).indexOf(filter) != -1)
                {
                    return true;
                }
                else if(Item.getSerialNumber().toLowerCase(Locale.ROOT).indexOf(filter) != -1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            });
        });

        SortedList<Item> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedList);

    }



}
