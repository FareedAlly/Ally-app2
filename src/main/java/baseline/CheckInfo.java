package baseline;

// Purpose of this class is to validate all the info the user enters for a new item
public class CheckInfo
{
    public boolean checkName(String name)
    {
        // Check if length of name is between 2 and 256
        return name.length() >= 2 && name.length() <= 256;
    }

    public boolean checkValue(String value)
    {
        float cost = Float.parseFloat(value);
        // Check if value is greater than or equal to 0
        return cost >= 0;
    }

    public boolean checkSerialNumber(String serialNumber)
    {
        // First check to see if it has the correct length
        char [] temp = serialNumber.toCharArray();

        if(temp.length != 13)
        {
            return false;
        }

        // Check to see if they have the correct dashes and letters/numbers
        for(int i=0; i<13; i++) {
            if (Character.isDigit(temp[0])) {
                // Checks if first index is a letter
                return false;
            }

            // Checks if dashes are in correct location
            if ((i == 1 || i == 5 || i == 9) && temp[i] != '-') {
                return false;
            }

        }

        // Check to see if special characters are present
        return true;
    }

}
