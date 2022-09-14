package currencyConverter;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ObtainSummaryController {
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setCsv(Csv csv) {
        this.csv = csv;
        int length = this.csv.records.size();
        for (int i = 1; i < length; i++) {
            firstBox.getItems().add(this.csv.records.get(i).get(1));
            secondBox.getItems().add(this.csv.records.get(i).get(1));
        }
    }
    public Csv csv;

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
        Csv csv = new Csv("Book1.csv");
        mainController.setCsv(csv);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSummaryTable(ActionEvent event) throws IOException, ParseException {
        String starting = String.valueOf(startDate.getValue());
        String ending = String.valueOf(endDate.getValue());

        String tmpCur = String.valueOf(firstBox.getValue());
        String targetCur = String.valueOf(secondBox.getValue());
        System.out.println("line70");
        generateSummaryData(starting, ending, tmpCur, targetCur);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/SummaryTable.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize (){

    }


    public void setCsv(SortEvent<TableView> tableViewSortEvent) {
    }

    public void switchToObtainSummaryTable(ActionEvent actionEvent) throws IOException, ParseException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/SummaryTable.fxml"));
        root = loader.load();

//        SummaryTableController summaryTableController = loader.getController();
//        summaryTableController.displayAllValues();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void getStartDate(ActionEvent event) throws IOException {
//
//        System.out.println(starting);
//    }
//
//    public void getEndDate(ActionEvent event) throws IOException {
//        String ending = String.valueOf(endDate.getValue());
//        System.out.println(ending);
//    }
    public void generateSummaryData(String starting, String ending, String tmpCur, String targetCur) throws IOException, ParseException {
        Csv readCsv1 = new Csv("Book1.csv");
        Csv readCsv2 = new Csv("Book2.txt");
        List<String> output1 = new ArrayList<>();
        List<String> output2 = new ArrayList<>();

        output1 = readCsv1.readCsv("Book1.csv");
        output2 = readCsv2.readCsv("Book2.txt");

        readDate converter = new readDate();
        String validStarting = converter.convertDate(starting);
        String validEnding = converter.convertDate(ending);
        System.out.println("line124");
        // get the target currency index
        int targetIndex = 0;
        String[] firstLine = output1.get(0).split(",");
        for (int i = 0; i < firstLine.length; i++) {
            if (firstLine[i].equals(targetCur)){
                targetIndex = i;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        Date start = sdf.parse(validStarting);
        Date end = sdf.parse(validEnding);

        List<String> rateList = new ArrayList<>();
        // searching the book2.txt file

        for (int i = 0; i < output2.size(); i++) {
            String[] line = output2.get(i).split(",");

            if (!line[0].equals("DATE")) {
                Date tmp = sdf.parse(line[0]);
                System.out.println("line 148");
                System.out.println(start);
//                System.out.println(tmp.after(start));
                if ((tmp.after(start) && tmp.before(end)) || tmp.equals(start) || tmp.equals(end)) {

                    if (line[1].equals(tmpCur)) {
                        System.out.println(line[targetIndex]);
                        rateList.add(line[targetIndex]);
                    }
                }

            }
        }

    }
}