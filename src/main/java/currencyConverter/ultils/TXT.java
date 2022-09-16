/**
 * Author: Ye Yuan
 * Modified date: 16/09/2022
 */

package currencyConverter.ultils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TXT {
    /**
     * Write file method which is used to put content into a file which already exits,
     * just write content after the file last line,
     * This would not overwrite original content
     *
     * @param filePath file wants to be written in
     * @param content  which want to put into the file
     * @throws IOException any exception, throws it
     */
    public void appendFileMode(String filePath, String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedWriter.write(content);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * Overwrite file method which is used to overwrite a file which already exits
     *
     * @param filePath file wants to be overwritten in
     * @param content  content wants to be put into the file >> always be the first line
     * @throws IOException any exception, throws it
     */
    public void overwriteFile(String filePath, String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.write(content);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * applied changes into dataset > Book1.csv
     *
     * @param filePath changes.txt
     * @param destFile Book1.csv
     * @param date     The date made changes
     * @throws IOException any exception, throws it
     */
    public void appliedChanges(String filePath, String destFile, String date) throws IOException {
        File fileName = new File(filePath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";

        CSV csvReader = new CSV(destFile);
        List<String> csvOutput = csvReader.outputDataset(destFile);

        while ((line = bufferedReader.readLine()) != null) {
            List<String> newCsv = new ArrayList<>();
            String[] values = line.split(",");

            String tmpCurrency = values[2];
            String targetCurrency = values[3];
            String tmpRate = values[4];

            int targetCurrencyIndex = 0;

            if (Objects.equals(values[0], date)) {

                // applied "add" changes into files
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

                    for (int i = 0; i < csvOutput.size(); i++) {

                        if (i == targetCurrencyIndex - 1) {
                            double reciprocal = 1 / Double.parseDouble(tmpRate);
                            String reciprocalString = String.format("%.4f", reciprocal);
                            lastLine.append(",").append(reciprocalString);

                        } else if (i == csvOutput.size() - 1) {
                            lastLine.append(",1");
                        } else {
                            lastLine.append(",-1");
                        }
                    }
                    newCsv.add(lastLine.toString());


                }

                // applied "modified" changes to file
                else if (Objects.equals(values[1], "Modified")) {

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

                        // change the tmpCurrency row
                        // tmpCurrency which means from tmpCurrency to target currency
                        if (i == indexTmpCurrency - 1) {
                            String[] modifiedLine = csvOutput.get(i).split(",");
                            searchTargetCurrency(newCsv, tmpRate, indexTargetCurrency, modifiedLine);

                        }
                        // change the target currency row
                        else if (i == indexTargetCurrency - 1) {
                            String[] modifiedLine = csvOutput.get(i).split(",");
                            double reciprocal = 1 / Double.parseDouble(tmpRate);
                            String reciprocalString = String.format("%.4f", reciprocal);
                            searchTargetCurrency(newCsv, reciprocalString, indexTmpCurrency, modifiedLine);

                        } else {
                            newCsv.add(csvOutput.get(i));
                        }
                    }
                }

                // write back to dataset
                TXT writer = new TXT();
                for (int i = 0; i < newCsv.size(); i++) {
                    if (i == 0) {
                        writer.overwriteFile(destFile, newCsv.get(i));
                    } else {
                        writer.appendFileMode(destFile, newCsv.get(i));
                    }
                }

                System.out.println(" Update Changes into Book1.csv file successfully!");
            }
        }
    }

    /**
     * search the target currency in the dataset
     *
     * @param newCsv              newCsv is a List<String> to store all data in the dataset, like tmp dataset
     * @param tmpRate             the date want to search
     * @param indexTargetCurrency index of the target currency in the dataset table
     * @param modifiedLine        any exception, throws it
     */
    private void searchTargetCurrency(List<String> newCsv, String tmpRate, int indexTargetCurrency, String[] modifiedLine) {
        modifiedLine[indexTargetCurrency] = tmpRate;

        StringBuilder tmpLine = new StringBuilder();
        for (int k = 0; k < modifiedLine.length; k++) {
            if (k == modifiedLine.length - 1) {
                tmpLine.append(modifiedLine[k]);
            } else {
                tmpLine.append(modifiedLine[k]).append(",");
            }
        }
        newCsv.add(String.valueOf(tmpLine));
    }

    /**
     * Copy book1 to book2, write after book2
     *
     * @param filePath book1
     * @param destFile book2
     * @throws IOException any exception, throws it
     */
    public void copyBook1ToBook2(String filePath, String destFile) throws IOException {
        CSV csvReader = new CSV(filePath);
        List<String> csvOutput = csvReader.outputDataset(filePath);
        TXT writer = new TXT();
        for (String s : csvOutput) {
            writer.appendFileMode(destFile, s);
        }

    }
}
