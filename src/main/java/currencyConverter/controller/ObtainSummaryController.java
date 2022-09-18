package currencyConverter.controller;

import currencyConverter.ultils.CSV;
import currencyConverter.ultils.ReadDate;
import currencyConverter.ultils.Calculator;
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

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class ObtainSummaryController {

    private Stage stage;

    private Scene scene;

    private Parent root;

    public CSV csv;

    @FXML
    private ChoiceBox<String> firstBox;

    @FXML
    private ChoiceBox<String> secondBox;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCSV(CSV csv) {
        this.csv = csv;
        int length = this.csv.records.size();
        for (int i = 1; i < length; i++) {
            firstBox.getItems().add(this.csv.records.get(i).get(1));
            secondBox.getItems().add(this.csv.records.get(i).get(1));
        }
    }

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

    @FXML
    private void initialize() {

    }

    public void switchToObtainSummaryTable(ActionEvent actionEvent) throws IOException, ParseException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/SummaryTable.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setCSV(SortEvent<TableView> tableViewSortEvent) {
    }

    /**
     * Go to summary table page
     *
     * @param event click button
     * @throws IOException    throws any IOException
     * @throws ParseException throws any ParseException
     */
    public void switchToSummaryTable(ActionEvent event) throws IOException, ParseException {

        // String type >> Starting date and Ending date
        String starting = String.valueOf(startDate.getValue());
        String ending = String.valueOf(endDate.getValue());

        // from currency A (tmpCur) to target currency B (targetCur)
        String tmpCur = String.valueOf(firstBox.getValue());
        String targetCur = String.valueOf(secondBox.getValue());

        // List of rates
        File book1 = new File("Book1.csv");
        File book2 = new File("Book2.txt");
        List<String> rateList = generateSummaryData(book1, book2, starting, ending, tmpCur, targetCur);

        // get the max and min value
        String mMax = Collections.max(rateList);
        String mMin = Collections.min(rateList);

        // get the average
        ArrayList<Double> doubleRateList = new ArrayList<>();
        double sum = 0;
        for (String s : rateList) {
            doubleRateList.add(Double.parseDouble(s));
            sum += Double.parseDouble(s);
        }
        double doubleMean = (double) (sum / rateList.size());
        String avg = String.format("%.2f", doubleMean);

        // get the median
        String median = Double.toString(Calculator.Median(doubleRateList));

        // get the SD
        Double doubleSD = Calculator.calculateSD(doubleRateList);
        String sd = String.format("%.4f", doubleSD);

        String allRates = rateList.toString();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/SummaryTable.fxml"));
        root = loader.load();

        SummaryTableController summaryTableController = loader.getController();
        summaryTableController.displayAllValues(starting, ending, tmpCur, targetCur,
                median, mMax, mMin, avg, sd, allRates);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Given two dates (from date A to date B), get all rates in the duration
     *
     * @param output      List<String>  dataset (Book1 + Book2)
     * @param start       Date Start date A
     * @param end         Date End date B
     * @param targetIndex The target currency index in the dataset
     * @param tmpCur      The currency index in the dataset
     * @return List<String> return the rates List contains all rates (String type) in the duration
     * @throws ParseException throws any ParseException
     */
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

    /**
     * Generate summary data which are used to write back summary table
     *
     * @param filePath1 Book1
     * @param filePath2 Book2
     * @param starting  starting date
     * @param ending    ending date
     * @param tmpCur    from currency A (tmpCur)
     * @param targetCur to target currency B (targetCur)
     * @return List<string> summary dataset (list type)
     * @throws IOException    throws IOException
     * @throws ParseException throws ParseException
     */
    public List<String> generateSummaryData(File filePath1, File filePath2,
                                            String starting, String ending,
                                            String tmpCur, String targetCur) throws IOException, ParseException {
        CSV readCsv1 = new CSV(filePath1.toString());
        CSV readCsv2 = new CSV(filePath2.toString());
        List<String> output1 = new ArrayList<>();
        List<String> output2 = new ArrayList<>();

        output1 = readCsv1.outputDataset(filePath1.toString());
        output2 = readCsv2.outputDataset(filePath2.toString());

        // convert date type to string type in order to compare easily
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

        // get date type
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

    public void switchToLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/LoginWindow.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPopularCurrencyTable(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/PopularCurrencyTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}