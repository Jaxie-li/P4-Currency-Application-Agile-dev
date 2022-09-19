package currencyConverter.ultils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {
    public List<List<String>> records = new ArrayList<>();
    public List<String> csvOutput = new ArrayList<>();
    public String[] currencies;

    public CSV(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {

            if (line.length() == 0) {
                break;
            }

            String[] values = line.split(",");

            records.add(Arrays.asList(values));
        }

        if (records.size() > 0) {
            this.currencies = new String[records.size() - 1];
            for (int i = 0; i < records.size() - 1; i++) {
                currencies[i] = records.get(i + 1).get(1);
            }
        }

    }

    public int indexOf(String target) {
        for (int i = 0; i < this.currencies.length; i++) {
            if (this.currencies[i].equalsIgnoreCase(target)) return i;
        }
        return -1;
    }

    /**
     * Author: ye
     * Modified date: 16/09/2022
     * Method outputDataset : read Book1.csv and transfer it into a List<String> type
     *
     * @param filePath Book1.csv || Book2.csv
     * @return List<String>
     * @throws IOException throws any IOException
     */
    public List<String> outputDataset(String filePath) throws IOException {
        File fileName = new File(filePath);

        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            csvOutput.add(line);
        }
        return csvOutput;
    }

}

