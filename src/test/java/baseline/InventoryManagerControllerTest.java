package baseline;

import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerControllerTest
{
    @Test
    void searchListTest()
    {
        InventoryManagerController test = new InventoryManagerController();

        test.list.add(new Item("aaa", "1", "a-123-456-789"));
        test.list.add(new Item("bbb", "2", "b-123-456-789"));
        test.list.add(new Item("ccc", "3", "c-123-456-789"));

        String search = "g";

        assertFalse(test.list.contains(search));

    }

    @Test
    void searchSerialNumberTest()
    {
        InventoryManagerController test = new InventoryManagerController();

        test.list.add(new Item("aaa", "1", "a-123-456-789"));
        test.list.add(new Item("bbb", "2", "b-123-456-789"));
        test.list.add(new Item("ccc", "3", "c-123-456-789"));

        String search2 = "h";

        assertFalse(test.list.contains("h"), search2);
    }

    @Test
    void sortNameTest()
    {
        InventoryManagerController test = new InventoryManagerController();

        test.list.add(new Item("aaa", "1", "a-123-456-789"));
        test.list.add(new Item("bbb", "2", "b-123-456-789"));
        test.list.add(new Item("ccc", "3", "c-123-456-789"));

        assertEquals("aaa", test.list.get(0).getName());
    }

    @Test
    void sortSerialNumberTest()
    {
        InventoryManagerController test = new InventoryManagerController();

        test.list.add(new Item("aaa", "1", "a-123-456-789"));
        test.list.add(new Item("bbb", "2", "b-123-456-789"));
        test.list.add(new Item("ccc", "3", "c-123-456-789"));

        assertEquals("a-123-456-789", test.list.get(0).getSerialNumber());
    }


}