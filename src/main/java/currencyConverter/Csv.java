package currencyConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Csv {
    public List<List<String>> records = new ArrayList<>();;
    public Csv(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void display() {
        for (List<String> l:
                records) {
            for (String s:
                    l) {
                System.out.print(s);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

