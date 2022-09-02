package currencyConverter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EventObject;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
        // change the path
        Csv csv = new Csv("Book1.csv");
        controller.setCsv(csv);

        stage.setScene(scene);
        stage.show();
    }

    public void switchToPopularCurrencyTable(javafx.event.ActionEvent actionEvent) throws IOException {
        //TODO: GUI - style the first column of the table for it to look like a header
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/PopularCurrencyTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

