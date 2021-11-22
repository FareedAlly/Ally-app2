/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Fareed Ally
 */

package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckInfoTest
{
    @Test
    void checkNameTest()
    {
        CheckInfo test = new CheckInfo();

        String name = "b";
        String name2 = "aaa";

        assertNotEquals(true, test.checkName(name));
        assertEquals(true, test.checkName(name2));
    }

    @Test
    void checkValue()
    {
        CheckInfo test = new CheckInfo();

        String value = "-1234";
        String value2 = "12345.67";

        assertNotEquals(true, test.checkValue(value));
        assertEquals(true, test.checkValue(value2));
    }

    @Test
    void checkSerialNumber()
    {
        CheckInfo test = new CheckInfo();

        String serialNumber = "a-123-456-789";
        String serial2 = "1-234-567-890";

        assertNotEquals(false, test.checkSerialNumber(serialNumber));
        assertEquals(false, test.checkSerialNumber(serial2));
    }


}