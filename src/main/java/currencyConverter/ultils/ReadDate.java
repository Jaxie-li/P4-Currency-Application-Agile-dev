/**
 * Author: Ye Yuan
 * Modified date: 16/09/2022
 */

package currencyConverter.ultils;

import java.io.*;

public class ReadDate {

    /**
     * This is for get today's date
     *
     * @param filePath book1.csv
     * @return String which is show the date >> "30/09/2022"
     * @throws IOException throws IOException
     */
    public String getDate(String filePath) throws IOException {
        File fileName = new File(filePath);

        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";

        int counter = 0;
        while ((line = bufferedReader.readLine()) != null) {
            counter++;
            if (counter == 2) {
                String[] values = line.split(",");
                return values[0];
            }
        }
        return "Get date failed";
    }

    /**
     * convert date format
     *
     * @param date String of original date: 2022-09-14 >> 14/09/2022
     * @return String of converted date
     */
    public String convertDate(String date) {
        // date picker format : 2022-09-14 >> 14/09/2022
        String[] dateSplit = date.split("-");
        String tmpYear = dateSplit[0];
        String tmpMonth = dateSplit[1];
        String tmpDay = dateSplit[2];

        return tmpDay + "/" + tmpMonth + "/" + tmpYear;
    }
}
