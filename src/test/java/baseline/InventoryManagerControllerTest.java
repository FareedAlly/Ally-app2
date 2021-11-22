/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Fareed Ally
 */

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

    @Test
    void sortValueTest()
    {
        InventoryManagerController test = new InventoryManagerController();

        test.list.add(new Item("aaa", "1", "a-123-456-789"));
        test.list.add(new Item("bbb", "2", "b-123-456-789"));
        test.list.add(new Item("ccc", "3", "c-123-456-789"));

        assertEquals("1", test.list.get(0).getValue());
    }

    @Test
    void clearListTest()
    {
        InventoryManagerController test = new InventoryManagerController();

        test.list.add(new Item("aaa", "1", "a-123-456-789"));
        test.list.add(new Item("bbb", "2", "b-123-456-789"));
        test.list.add(new Item("ccc", "3", "c-123-456-789"));

        test.list.clear();

        assertEquals(0, test.list.size());

    }

    @Test
    void removeOneItemTest()
    {
        InventoryManagerController test = new InventoryManagerController();

        test.list.add(new Item("aaa", "1", "a-123-456-789"));
        test.list.add(new Item("bbb", "2", "b-123-456-789"));
        test.list.add(new Item("ccc", "3", "c-123-456-789"));

        test.list.remove(2);

        assertEquals(2, test.list.size());

    }




}