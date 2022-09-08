package currencyConverter;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;












public class PopularCurrencyController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void generateExchangeRate() {
        // TODO: get the popular currencies, calculate values and put them in a table
        // TODO: link with table-view

    }
    public void backToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    String currency_csv_string = "/currencyConverter/Main.fxml";
    public Csv currency_Csv = new Csv(currency_csv_string);
    private final TableView currency_table = new TableView();

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load((getClass().getResource("/currencyConverter/PopularCurrencyTable.fxml")));
//        primaryStage.setTitle("Popular Currency");
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args){
//        launch(args);
//    }









}