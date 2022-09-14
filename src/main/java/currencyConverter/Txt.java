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
//        System.out.println("Mode 2 overwrite the Book1.csv file the first line successfully!");
    }

    public void appliedChanges(String filePath, String date) throws  IOException {
        File fileName = new File(filePath);

        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";

        Csv csvReader = new Csv("Book1.csv");
        List<String> csvOutput = csvReader.readCsv("Book1.csv");

        while ((line = bufferedReader.readLine()) != null) {
            List<String> newCsv = new ArrayList<>();
            String[] values = line.split(",");

            String tmpCurrency = values[2];
            String targetCurrency = values[3];
            String tmpRate = values[4];

            int targetCurrencyIndex = 0;
            if (Objects.equals(values[0], date)){
                if (Objects.equals(values[1], "Add")) {
                    for (int i = 0; i < csvOutput.size(); i++) {
                        if (i == 0) {
                            String tmp = csvOutput.get(i) + "," + tmpCurrency;
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

                        if (i == targetCurrencyIndex - 1) {
                            double reciprocal  = 1 / Double.parseDouble(tmpRate);
                            String reciprocalString = String.format("%.4f", reciprocal);
                            lastLine.append(",").append(reciprocalString);

                        } else if (i == csvOutput.size() - 1) {
                            lastLine.append(",1");
                        } else {
                            lastLine.append(",-1");
                        }
                    }
                    newCsv.add(lastLine.toString());
//
//                    Txt writer = new Txt();
//                    for (int i = 0; i < newCsv.size(); i++) {
//                        if (i == 0) {
//                            writer.writeFile2("Book1.csv", newCsv.get(i));
//                        } else {
//                            writer.writeFile("Book1.csv", newCsv.get(i));
//                        }
//                    }
//
//                    System.out.println("Add update into Book1.csv file successfully!");
                } else if (Objects.equals(values[1], "Modified")) {

                    int indexTmpCurrency = 0;
                    int indexTargetCurrency = 0;
                    for (int i = 0; i < csvOutput.size(); i++) {

                        // get the tmpCurrency index and targetCurrency index
                        if (i == 0) {
                            String[] modifiedLine = csvOutput.get(i).split(",");
                            for (int j = 0; j < modifiedLine.length; j++) {
                                if (modifiedLine[j].equals(tmpCurrency)) {

                                    indexTmpCurrency = j;
                                } else if (modifiedLine[j].equals(targetCurrency)) {
                                    indexTargetCurrency = j;
                                }
                            }
                        }

                        if (i == indexTmpCurrency - 1) {
                            String[] modifiedLine = csvOutput.get(i).split(",");
                            modifiedLine[indexTargetCurrency] = tmpRate;

                            StringBuilder tmpLine = new StringBuilder();
                            for(int k = 0; k < modifiedLine.length; k++) {
                                if (k == modifiedLine.length - 1) {
                                    tmpLine.append(modifiedLine[k]);
                                } else {
                                    tmpLine.append(modifiedLine[k]).append(",");
                                }
                            }
                            newCsv.add(String.valueOf(tmpLine));
//                            System.out.println(i);
//                            System.out.println(Arrays.toString(modifiedLine));

                        } else if (i == indexTargetCurrency - 1) {
                            String[] modifiedLine = csvOutput.get(i).split(",");
                            double reciprocal  = 1 / Double.parseDouble(tmpRate);
                            String reciprocalString = String.format("%.4f", reciprocal);
                            modifiedLine[indexTmpCurrency] = reciprocalString;

                            StringBuilder tmpLine = new StringBuilder();
                            for(int k = 0; k < modifiedLine.length; k++) {
                                if (k == modifiedLine.length - 1) {
                                    tmpLine.append(modifiedLine[k]);
                                } else {
                                    tmpLine.append(modifiedLine[k]).append(",");
                                }
                            }
                            newCsv.add(String.valueOf(tmpLine));
//                            System.out.println(i);
//                            System.out.println(reciprocalString);

                        } else {
                            newCsv.add(csvOutput.get(i));
                        }
                    }

                }
                Txt writer = new Txt();
                for (int i = 0; i < newCsv.size(); i++) {
                    if (i == 0) {
                        writer.writeFile2("Book1.csv", newCsv.get(i));
                    } else {
                        writer.writeFile("Book1.csv", newCsv.get(i));
                    }
                }

                System.out.println(" Update Changes into Book1.csv file successfully!");
            }
        }
    }

    public void updateCsv2(String filePath) throws IOException {
        Csv csvReader = new Csv("Book1.csv");
        List<String> csvOutput = csvReader.readCsv("Book1.csv");
        Txt writer = new Txt();
        for (String s: csvOutput) {
            writer.writeFile(filePath, s);
        }

    }
}
