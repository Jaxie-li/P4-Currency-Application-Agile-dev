package currencyConverter;

import java.io.*;

public class Date {
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
}
