package currencyConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Csv {
    public List<List<String>> records = new ArrayList<>();
    
    public int indexOf(String target) {
        for (int i = 0; i < this.currencies.length; i++) {
            if (this.currencies[i].equalsIgnoreCase(target)) return i;
        }
        return -1;
    }

    public String[] currencies;

    public Csv(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                records.add(Arrays.asList(values));
            }

            this.currencies = new String[records.size()-1];
            for (int i = 0; i < records.size() - 1; i++) {
                currencies[i] = records.get(i+1).get(1);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);}
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(JavaFXCSVTableView.class.getName())
//                .log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(JavaFXCSVTableView.class.getName())
//                .log(Level.SEVERE, null, ex);
//    }
        
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

    public List<String> csvOutput = new ArrayList<String>();
    public List<String> readCsv(String filePath) throws IOException {
        File fileName = new File(filePath);

        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                csvOutput.add(line);

        }
        return csvOutput;
    }

}

