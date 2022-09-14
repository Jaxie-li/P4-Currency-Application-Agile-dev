package currencyConverter;

import java.io.*;

public class readDate {
    public String readCsv(String filePath) throws IOException {
        File fileName = new File(filePath);

        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";

        int counter = 0;
        while ((line = bufferedReader.readLine()) != null) {
            counter++;
//            System.out.println(line);
            if (counter == 2) {
                String[] values = line.split(",");
                return values[0];
//                break;
            }
        }
        return "Get date failed";
    }

    public String convertDate(String date) {
        // date picker format : 2022-09-14 >> 14/09/2022
        String[] dateSplit = date.split("-");
        String tmpYear = dateSplit[0];
        String tmpMonth = dateSplit[1];
        String tmpDay = dateSplit[2];

        return tmpDay + "/" + tmpMonth + "/" + tmpYear;
    }
}
