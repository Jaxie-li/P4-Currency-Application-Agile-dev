package currencyConverter.controller;
import currencyConverter.ultils.CSV;
import currencyConverter.ultils.ReadDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class ObtainSummaryController {
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setCSV(CSV csv) {
        this.csv = csv;
        int length = this.csv.records.size();
        for (int i = 1; i < length; i++) {
            firstBox.getItems().add(this.csv.records.get(i).get(1));
            secondBox.getItems().add(this.csv.records.get(i).get(1));
        }
    }
    public CSV csv;

    @FXML
    private ChoiceBox<String> firstBox;

    @FXML
    private ChoiceBox<String> secondBox;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    public void backToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        mainController.setStage(stage);
        CSV csv = new CSV("Book1.csv");
        mainController.setCsv(csv);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSummaryTable(ActionEvent event) throws IOException, ParseException {
        String starting = String.valueOf(startDate.getValue());
        String ending = String.valueOf(endDate.getValue());

        String tmpCur = String.valueOf(firstBox.getValue());
        String targetCur = String.valueOf(secondBox.getValue());

        List<String> rateList = generateSummaryData(starting, ending, tmpCur, targetCur);

        String mmax = Collections.max(rateList);
        String mmin = Collections.min(rateList);

        ArrayList<Double> doubleRateList = new ArrayList<>();
        double sum = 0;
        for (String s: rateList) {
            doubleRateList.add(Double.parseDouble(s));
            sum += Double.parseDouble(s);
        }

        double doubleMean = (double) (sum / rateList.size());
        String avg = String.format("%.2f", doubleMean);

        String median = Double.toString(Median(doubleRateList));

        Double doubleSD = calculateSD(doubleRateList);
        String sd = String.format("%.4f", doubleSD);

        String allRates = rateList.toString();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/SummaryTable.fxml"));
        root = loader.load();

        SummaryTableController summaryTableController = loader.getController();
        summaryTableController.displayAllValues(starting, ending, tmpCur, targetCur,
                median, mmax, mmin, avg, sd, allRates);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize (){

    }

    public void setCSV(SortEvent<TableView> tableViewSortEvent) {
    }

    public void switchToObtainSummaryTable(ActionEvent actionEvent) throws IOException, ParseException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/SummaryTable.fxml"));
        root = loader.load();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public List<String> getAllRates(List<String> output, Date start, Date end,
                                    Integer targetIndex, String tmpCur) throws ParseException {

        List<String> rateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (String s : output) {
            String[] line = s.split(",");

            if (!line[0].equals("DATE")) {
                Date tmp = sdf.parse(line[0]);
                if ((tmp.after(start) && tmp.before(end)) || tmp.equals(start) || tmp.equals(end)) {

                    if (line[1].equals(tmpCur)) {
                        System.out.println(line[targetIndex]);
                        rateList.add(line[targetIndex]);
                    }
                }
            }
        }
        return rateList;
    }

    public List<String> generateSummaryData(String starting, String ending, String tmpCur, String targetCur) throws IOException, ParseException {
        CSV readCsv1 = new CSV("Book1.csv");
        CSV readCsv2 = new CSV("Book2.txt");
        List<String> output1 = new ArrayList<>();
        List<String> output2 = new ArrayList<>();

        output1 = readCsv1.grabDataSet("Book1.csv");
        output2 = readCsv2.grabDataSet("Book2.txt");

        ReadDate converter = new ReadDate();
        String validStarting = converter.convertDate(starting);
        String validEnding = converter.convertDate(ending);

        // get the target currency index
        int targetIndex = 0;
        String[] firstLine = output1.get(0).split(",");
        for (int i = 0; i < firstLine.length; i++) {
            if (firstLine[i].equals(targetCur)) {
                targetIndex = i;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date start = sdf.parse(validStarting);
        Date end = sdf.parse(validEnding);

        List<String> rateList1 = new ArrayList<>();
        List<String> rateList2 = new ArrayList<>();
        // searching the book2.txt file

        rateList2 = getAllRates(output2, start, end, targetIndex, tmpCur);
        rateList1 = getAllRates(output1, start, end, targetIndex, tmpCur);

        List<String> rateList = new ArrayList<>();
        rateList.addAll(rateList2);
        rateList.addAll(rateList1);

        return rateList;
    }

    // method from : http://www.java2s.com/example/java-utility-method/median/median-arraylist-double-values-82543.html
    public static double Median(ArrayList<Double> doubleRateList) {
        Collections.sort(doubleRateList);

        if (doubleRateList.size() % 2 == 1)
            return doubleRateList.get((doubleRateList.size() + 1) / 2 - 1);

        else {

            double lower = doubleRateList.get(doubleRateList.size() / 2 - 1);
            double upper = doubleRateList.get(doubleRateList.size() / 2);

            return (lower + upper) / 2.0;
        }
    }

    // method from : https://www.programiz.com/java-programming/examples/standard-deviation
    public static double calculateSD(ArrayList<Double> numArray)
    {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.size();

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }

}