package currencyConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void backToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToObtainSummaryTable(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize () {
    }
}
