package currencyConverter;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopularCurrencyController{

    @FXML
    private TableView<Currency> popular_currency_table;

    @FXML
    private TableColumn<Currency,String> From;

    @FXML
    private TableColumn<Currency,String> AUD;

    @FXML
    private TableColumn<Currency,String> CNY;

    @FXML
    private TableColumn<Currency,String> JPY;

    @FXML
    private TableColumn<Currency,String> USD;

    @FXML
    private TableColumn<Currency,String> GBP;

    @FXML
    private TableColumn<Currency,String> CAD;

    @FXML
    private TableColumn<Currency,String> Date;

    private final ObservableList<Currency> popularCurrency_list = FXCollections.observableArrayList();
    private final List<Currency> currencyList = new ArrayList<>();
    private final Path path = Paths.get("Book1.csv");
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize()throws IOException{
        From.setCellValueFactory (new PropertyValueFactory<Currency,String>("From"));
        AUD.setCellValueFactory (new PropertyValueFactory<Currency,String>("AUD"));
        CNY.setCellValueFactory (new PropertyValueFactory<Currency,String>("CNY"));
        JPY.setCellValueFactory (new PropertyValueFactory<Currency,String>("JPY"));
        USD.setCellValueFactory (new PropertyValueFactory<Currency,String>("USD"));
        GBP.setCellValueFactory (new PropertyValueFactory<Currency,String>("GBP"));
        CAD.setCellValueFactory (new PropertyValueFactory<Currency,String>("CAD"));
        Date.setCellValueFactory (new PropertyValueFactory<Currency,String>("Date"));

        popular_currency_table.setItems(popularCurrency_list);
        popular_currency_table.setEditable(true);

        List<String> lines = Files.readAllLines(path);
        for (String line :lines.subList(1,lines.size())){
            String[] tmp = line.split(",");
            System.out.println(tmp[0]);
            popularCurrency_list.add(new Currency(tmp[0],tmp[1],tmp[2],tmp[3],tmp[4],tmp[5],tmp[6],tmp[7]));
        }


    }
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




}