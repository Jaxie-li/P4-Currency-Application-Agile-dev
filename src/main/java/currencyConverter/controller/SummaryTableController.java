package currencyConverter.controller;

import currencyConverter.ultils.CSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SummaryTableController {
    private Stage stage;
    private Scene scene;
    @FXML
    private ChoiceBox<String> firstBox;
    @FXML
    private ChoiceBox<String> secondBox;
    @FXML
    private TextField startingDate;
    @FXML
    private TextField endingDate;
    @FXML
    private TextField tmpCurrency;
    @FXML
    private TextField targetCurrency;
    @FXML
    private TextField median;
    @FXML
    private TextField mmax;
    @FXML
    private TextField mmin;
    @FXML
    private TextField mean;
    @FXML
    private TextField sd;
    @FXML
    private TextField allRates;


    public void setCsv(CSV csv) {
        this.csv = csv;
        int length = csv.records.size();
        for (int i = 1; i < length; i++) {
            firstBox.getItems().add(csv.records.get(i).get(0));
            secondBox.getItems().add(csv.records.get(i).get(0));
        }
    }
    public CSV csv;

    public void switchToObtainSummaryTable(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ObtainSummaryTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ObtainSummaryController controller = loader.getController();
        controller.setStage(stage);
        CSV csv = new CSV("Book1.csv");
        controller.setCSV(csv);
        stage.setScene(scene);
        stage.show();
    }

    public void displayAllValues(String starting, String ending,
                                 String tmp, String target,
                                 String mMedian, String max,
                                 String min, String mMean,
                                 String standard, String all) {
        startingDate.setText(starting);
        endingDate.setText(ending);
        tmpCurrency.setText(tmp);
        targetCurrency.setText(target);
        median.setText(mMedian);
        mmax.setText(max);
        mmin.setText(min);
        mean.setText(mMean);
        sd.setText(standard);
        allRates.setText(all);
    }



}