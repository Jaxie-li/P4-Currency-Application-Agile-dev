package currencyConverter.controller;


import currencyConverter.components.PopularCurrency;
import currencyConverter.ultils.CSV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PopularCurrencyController{

    @FXML
    private TableView<PopularCurrency> popular_currency_table;

    @FXML
    private TableColumn<PopularCurrency, String> fromTc, aTc, bTc, cTc, dTc;

    private final ObservableList<PopularCurrency> popularCurrency_list = FXCollections.observableArrayList();
    private final List<PopularCurrency> currencyList = new ArrayList<>();
    private final Path path = Paths.get("Popular.txt");
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize()throws IOException{
        fromTc.setCellValueFactory (new PropertyValueFactory<>("from"));
        aTc.setCellValueFactory (new PropertyValueFactory<>("currencyA"));
        bTc.setCellValueFactory (new PropertyValueFactory<>("currencyB"));
        cTc.setCellValueFactory (new PropertyValueFactory<>("currencyC"));
        dTc.setCellValueFactory (new PropertyValueFactory<>("currencyD"));

        popular_currency_table.setItems(popularCurrency_list);
        popular_currency_table.setEditable(true);

        List<String> lines = Files.readAllLines(path);
        String[] titles = new String[4];
        int count = 0;
        for (String line :lines.subList(1,lines.size())){
            String[] tokens = line.split(",");
            titles[count] = tokens[0];
            PopularCurrency temp = new PopularCurrency(tokens);
            System.out.println(temp);
            popularCurrency_list.add(temp);
            count++;
        }
        aTc.setText(titles[0]);
        bTc.setText(titles[1]);
        cTc.setText(titles[2]);
        dTc.setText(titles[3]);

    }

    public void backToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        mainController.setStage(stage);
        CSV csv = new CSV("Book1.csv");
        mainController.setCsv(csv);
        stage.setScene(scene);
        stage.show();
    }




}