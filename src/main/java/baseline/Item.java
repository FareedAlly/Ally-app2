package baseline;

import java.util.ArrayList;
import java.util.List;

public class Item
{
    private String name;
    private String value;
    private String serialNumber;

    // Create check object to validate info
    private final CheckInfo check = new CheckInfo();

    // Create list to hold all items
    private static final List<Item> inventory = new ArrayList<>();

    // Constructor
    public Item(String name, String value, String serialNumber)
    {
        this.name = name;
        this.value = value;

        // Check serialNumber then assign it
        if(check.checkSerialNumber(serialNumber))
        {
            this.serialNumber = serialNumber;
        }
        else if(serialNumber.equals("") || !check.checkSerialNumber(serialNumber))
        {
            this.serialNumber = "Enter a valid serial number";
        }

    }

    public void setName(String name)
    {
        // Check name then assign it
        if(check.checkName(name))
        {
            this.name = name;
        }
    }

    public void setValue(String value)
    {
        // Check value then assign it
        if(check.checkValue(value))
        {
            this.value = value;
        }
    }

    public void setSerialNumber(String serialNumber)
    {
        // Check serial number then assign it
        if(check.checkSerialNumber(serialNumber))
        {
            this.serialNumber = serialNumber;
        }
    }

    public String getName()
    {
        return name;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public String getValue()
    {
        return value;
    }

    public static List<Item> getInventory()
    {
        return inventory;
    }
}
