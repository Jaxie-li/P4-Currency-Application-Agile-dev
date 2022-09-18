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
    private TextField maxx;
    @FXML
    private TextField minn;
    @FXML
    private TextField mean;
    @FXML
    private TextField sd;
    @FXML
    private TextField allRates;

    public CSV csv;

    public void setCsv(CSV csv) {
        this.csv = csv;
        int length = csv.records.size();
        for (int i = 1; i < length; i++) {
            firstBox.getItems().add(csv.records.get(i).get(0));
            secondBox.getItems().add(csv.records.get(i).get(0));
        }
    }

    public void switchToObtainSummaryTable(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ObtainSummaryTable.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        ObtainSummaryController controller = loader.getController();
        controller.setStage(stage);
        CSV csv = new CSV("Book1.csv");
        controller.setCSV(csv);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * set text in the summary page
     *
     * @param starting starting date
     * @param ending   ending date
     * @param tmp      currency A
     * @param target   currency B
     * @param mMedian  median
     * @param max      max
     * @param min      min
     * @param mMean    mean
     * @param standard sd
     * @param all      all dates
     */
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
        maxx.setText(max);
        minn.setText(min);
        mean.setText(mMean);
        sd.setText(standard);
        allRates.setText(all);
    }
}