package currencyConverter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;



    public MainController() {
    }

    // Define events
    public void onclick() {
        System.out.println("button clicked!");
    }

    public void switchToObtainSummaryTable(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ObtainSummaryTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        SummaryTableController controller = loader.getController();
        controller.setStage(stage);
        // todo: 读取relative path 的csv
        Csv csv = new Csv("C:\\Users\\Louie Gan\\SOFT2412_ASM1_CC14_G5\\Book1.csv");
        controller.setCsv(csv);

        stage.setScene(scene);
        stage.show();
    }
}

