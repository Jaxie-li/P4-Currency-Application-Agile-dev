package currencyConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Txt {

    public void writeFile(String filePath, String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedWriter.write(content);

        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
        // System.out.println("Success save the changes");
    }

    public void writeFile2(String filePath, String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.write(content);

        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
        System.out.println("Mode 2 overwrite the Book1.csv file the first line successfully!");
    }

    public void readFile(String filePath, String date) throws  IOException {
        File fileName = new File(filePath);

        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";

        Csv csvReader = new Csv("Book1.csv");
        List<String> csvOutput = csvReader.readCsv("Book1.csv");
        List<String> newCsv = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split(",");

            String tmpCurrency = values[2];
            String targetCurrency = values[3];
            String tmpRate = values[4];

            int targetCurrencyIndex = 0;
            if (Objects.equals(values[0], date)){
                if (Objects.equals(values[1], "Add")) {
                    for (int i = 0; i < csvOutput.size(); i++) {
                        if (i == 0) {
                            String tmp = csvOutput.get(i) + ", " + tmpCurrency;
                            newCsv.add(tmp);
                        } else {
                            String[] csvLine = csvOutput.get(i).split(",");
                            String tmp;
                            if (!Objects.equals(csvLine[1], targetCurrency)) {

                                tmp = csvOutput.get(i) + ",-1";
                            } else {
                                targetCurrencyIndex = i;
                                tmp = csvOutput.get(i) + "," + tmpRate;
                            }
                            newCsv.add(tmp);
                        }
                    }
                    StringBuilder lastLine = new StringBuilder(date + "," + tmpCurrency);
                    for (int i = 0; i < csvOutput.size(); i++){
                        if (i == targetCurrencyIndex) {
                            lastLine.append(",").append(tmpRate);
                        } else if (i == csvOutput.size() - 1) {
                            lastLine.append(",1");
                        } else {
                            lastLine.append(",-1");
                        }
                    }
                    newCsv.add(lastLine.toString());

                    Txt writer = new Txt();
                    for (int i = 0; i < newCsv.size(); i++) {
                        if (i == 0) {
                            writer.writeFile2("Book1.csv", newCsv.get(i));
                        } else {
                            writer.writeFile("Book1.csv", newCsv.get(i));
                        }
                    }

                    System.out.println("Add update into Book1.csv file successfully!");
                } else if (Objects.equals(values[1], "Modified")) {

                }
            }
        }
    }


}
