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

            if(line.length()==0){break;}

            String[] values = line.split(",");

            records.add(Arrays.asList(values));
        }

        this.currencies = new String[records.size()-1];
        for (int i = 0; i < records.size() - 1; i++) {
            currencies[i] = records.get(i+1).get(1);
        }

    }

    public int indexOf(String target) {
        for (int i = 0; i < this.currencies.length; i++) {
            if (this.currencies[i].equalsIgnoreCase(target)) return i;
        }
        return -1;
    }

    public List<String> grabDataSet(String filePath) throws IOException {
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

