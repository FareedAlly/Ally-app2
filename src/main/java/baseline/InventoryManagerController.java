/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Fareed Ally
 */

package baseline;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InventoryManagerController
{
    public int index = 0;
    CheckInfo check = new CheckInfo();
    public final ObservableList<Item> list = FXCollections.observableArrayList();

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
                statusField.setText("Name not updated. ");
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

                statusField.setText("Value updated. ");
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
            // If description is valid, then add it to the table
            if(check.checkSerialNumber(event.getNewValue()))
            {
                Item item = event.getRowValue();
                item.setSerialNumber(event.getNewValue());

                statusField.setText("Serial Number updated. ");
            }

            else
            {
                statusField.setText("Serial Number not updated. ");
                tableView.refresh();
            }
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
        String name;
        String serialNumber;
        String value;
        // Use filechooser
        FileChooser fileChooser = new FileChooser();
        Stage load = new Stage();

        fileChooser.setTitle("Load Inventory ");

        File file = fileChooser.showOpenDialog(load);

        // Clear current inventory
        list.clear();

        // Clear entire list of items
        Item.getInventory().clear();

        // Clear table view
        tableView.getItems().clear();

        // Reset index to 0
        index = 0;


        try
        {
            Scanner in = new Scanner(file);

            //Load from the 3 different file types
            if(file.getName().contains(".txt"))
            {
                in.nextLine();

                while(in.hasNext())
                {
                    // Copy the information over
                    serialNumber = in.next();
                    name = in.next();
                    value = in.next();

                    //Create item
                    addItemAndDisplay(name, value, serialNumber);
                }
            }
            else if(file.getName().contains(".json"))
            {
                JsonParser jsonParser = new JsonParser();

                FileReader fileReader = new FileReader(file);

                Object object = jsonParser.parse(fileReader);

                JsonObject jsonObject = (JsonObject) object;

                name = jsonObject.get("name").toString();
                value = jsonObject.get("value").toString();
                serialNumber = jsonObject.get("serialNumber").toString();

                addItemAndDisplay(name, value, serialNumber);


            }
            else if(file.getName().contains(".html"))
            {
                // The top of the html tile is not needed
                in.skip(Pattern.compile("""
                    <html>
                    <head>
                    <title>Inventory</title>
                    </head>
                    <body>
                    <table> 
                    <tr>
                    """));



                while(in.hasNextLine())
                {

                    serialNumber = in.nextLine();
                    name = in.nextLine();
                    value = in.nextLine();

                    // Remove html tags such as <td> and </td>
                    serialNumber = serialNumber.substring(4, serialNumber.length()-5);
                    name = name.substring(4, name.length()-5);
                    value = value.substring(4, value.length()-5);


                    addItemAndDisplay(name, value, serialNumber);

                    if(in.nextLine().contains("</table>"))
                    {
                        break;
                    }

                }

            }

        } catch (FileNotFoundException exception)
          {
            exception.printStackTrace();
          }


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
        // Use filechooser
        FileChooser fileChooser = new FileChooser();

        Stage save = new Stage();

        String [] filetypes = {".txt", ".json", ".html"};

        // Allow user to have 3 options for which the file will be saved as
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Save as ", filetypes);

        fileChooser.setTitle("Save current inventory ");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(save);

        //If user saves as txt file then let it be tsv
        if(file.getName().contains(filetypes[0]))
        {
            // Use filewriter
            try(FileWriter fileWriter = new FileWriter(file))
            {
                // Print titles for column
                fileWriter.write("Serial Number\tName\tValue\n");

                //Iterate through all items and write data to file
                for(Item items : list)
                {
                    //Print info with tab seperation
                    fileWriter.write(items.getSerialNumber() + "\t" + items.getName() + "\t" + items.getValue() + "\n");
                }

            } catch (IOException ioException)
              {
                ioException.printStackTrace();
              }

        }
        if(file.getName().contains(filetypes[1]))
        {
            Gson gson = new Gson();

            // Use filewriter
            try(FileWriter fileWriter = new FileWriter(file))
            {
                gson.toJson(list, fileWriter);
            } catch (IOException ioException)
              {
                ioException.printStackTrace();
              }
        }
        if(file.getName().contains(filetypes[2]))
        {
            // Print the static text at the top and bottom of every html file
            String topText = """
                    <html>
                    <head>
                    <title>Inventory</title>
                    </head>
                    <body>
                    <table>   
                    """;

            String bottomText = """
                    </table>
                    </body>
                    </html>
                    """;

            // Use filewriter
            try(FileWriter fileWriter = new FileWriter(file))
            {
                fileWriter.write(topText);

                // Iterate through items and write all their info
                for(Item items : list)
                {
                    fileWriter.write("<tr>\n<td>" + items.getSerialNumber() + "</td>\n<td>" + items.getName() + "</td>\n<td>" +  items.getValue() +"</td>\n</tr>");

                }

                fileWriter.write(bottomText);
            } catch (IOException ioException)
              {
                ioException.printStackTrace();
              }
        }



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
