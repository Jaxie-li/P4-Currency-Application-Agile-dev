package currencyConverter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class CurrencyConverterApp extends Application {

//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//
//    public void setCsv(Csv csv) {
//        this.csv = csv;
//        int length = this.csv.records.size();
//        for (int i = 1; i < length; i++) {
//            current_currency_choicebox.getItems().add(this.csv.records.get(i).get(0));
//            targret_choicebox.getItems().add(this.csv.records.get(i).get(0));
//        }
//    }
//    public Csv csv;
//
//    @FXML
//    private ChoiceBox<String> current_currency_choicebox;
//
//   @FXML
//    private ChoiceBox<String> targret_choicebox;
//
//    @FXML
//    private void initialize (){
//    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("Main.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("Currency Converter");
        stage.setResizable(false);
        stage.show();

        mainController.setStage(stage);
        Csv csv = new Csv("Book1.csv");
        mainController.setCsv(csv);
        stage.setScene(scene);
        stage.show();


        //logout event
        stage.setOnCloseRequest(event->{
            event.consume();
            logout(stage);
        });
    }

    //The logout in main page
    public void logout(Stage stage){
        //build a window for stay in the page or logout.
        Alert alter = new Alert(Alert.AlertType.CONFIRMATION);
        alter.setTitle("Logout");
        alter.setHeaderText("About logout:(");
        alter.setContentText("Do you want to exiting?");
        // get a handle to the stage, close the page
        if(alter.showAndWait().get()== ButtonType.OK){
            System.out.println("you Successfully logged out.");
            stage.close();
        }}


    public static void main(String[] args) {
        launch(args);
    }
}