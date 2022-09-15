package currencyConverter.controller;

import currencyConverter.ultils.CSV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public Button summaryTable;
    private Stage stage;
    private Scene scene;

    public CSV csv;

    @FXML
    private ChoiceBox<String> current_currency_choicebox;

    @FXML
    private ChoiceBox<String> targret_choicebox;

    @FXML
    private TextArea numberTa;

    @FXML
    private Label ansLb;

    @FXML
    private void initialize (){
    }

    // Define events
    public void onclick() {
        String currentCurrency = current_currency_choicebox.getValue();
        String targetCurrency =  targret_choicebox.getValue();
        double num = Double.parseDouble(numberTa.getText());
        int currentIndex = csv.indexOf(currentCurrency);
        int targetIndex = csv.indexOf(targetCurrency);
        double rate = Double.parseDouble(csv.records.get(currentIndex+1).get(targetIndex+2));
        ansLb.setText(String.format("%.2f\n", num * rate));
//        ansLb.setText("");
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setCsv(CSV csv) {
        this.csv = csv;
        int length = this.csv.records.size();
        for (int i = 1 ; i < length; i++) {
//            System.out.println(this.csv.records.get(i).get(1));
            current_currency_choicebox.getItems().add(this.csv.records.get(i).get(1));
            targret_choicebox.getItems().add(this.csv.records.get(i).get(1));
        }
    }

    public void switchToObtainSummaryTable(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ObtainSummaryTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ObtainSummaryController controller = loader.getController();
        controller.setStage(stage);
        CSV csv = new CSV("Book1.csv");
        controller.setCsv(csv);
        stage.setScene(scene);
        stage.show();
    }
}

