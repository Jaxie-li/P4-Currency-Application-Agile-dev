package currencyConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class SummaryTableController {

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setCsv(Csv csv) {
        this.csv = csv;
        int length = csv.records.size();
        for (int i = 1; i < length; i++) {
            firstBox.getItems().add(csv.records.get(i).get(0));
            secondBox.getItems().add(csv.records.get(i).get(0));
        }
    }

    public Csv csv;




    @FXML
    private ChoiceBox<String> firstBox;


    @FXML
    private ChoiceBox<String> secondBox;



    public void switchToSummaryTable(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/SummaryTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void back(ActionEvent actionEvent) throws IOException{
        csv.display();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize (){

    }
}