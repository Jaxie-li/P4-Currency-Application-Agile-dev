package currencyConverter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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

    public void switchToPopularCurrencyTable(javafx.event.ActionEvent actionEvent) throws IOException {
        //TODO: GUI - style the first column of the table for it to look like a header
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/PopularCurrencyTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void adminLogin(){
        System.out.println("Admin Login Page should be invoked!");
    }

    //click admin => login page
    public void SwitchToLogin(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/LoginWindow.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToObtainSummaryTable(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ObtainSummaryTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ObtainSummaryController controller = loader.getController();
        controller.setStage(stage);
        // todo: 读取relative path 的csv
        Csv csv = new Csv("Book1.csv");
        controller.setCsv(csv);
        stage.setScene(scene);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setCsv(Csv csv) {
        this.csv = csv;
        int length = this.csv.records.size();
        for (int i = 1 ; i < length; i++) {
            System.out.println(this.csv.records.get(i).get(1));
            current_currency_choicebox.getItems().add(this.csv.records.get(i).get(1));
            targret_choicebox.getItems().add(this.csv.records.get(i).get(1));
        }
    }
    public Csv csv;

    @FXML
    private ChoiceBox<String> current_currency_choicebox;

    @FXML
    private ChoiceBox<String> targret_choicebox;

    @FXML
    private void initialize (){
    }
}

