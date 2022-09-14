package currencyConverter;

import currencyConverter.controller.MainController;
import currencyConverter.ultils.CSV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

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
        CSV csv = new CSV("Book1.csv");
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
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}