package currencyConverter;


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
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PopularCurrencyController{
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
//
//    public class Record {
//        // every observation(row) has 5 columns
//
//        /*A JavaFX property is an observable container that facilitates data binding for automatic update*/
//        private SimpleStringProperty f1, f2, f3, f4, f5;
//
//        public Record(String f1, String f2, String f3, String f4, String f5) {
//            this.f1 = new SimpleStringProperty(f1);
//            this.f2 = new SimpleStringProperty(f2);
//            this.f3 = new SimpleStringProperty(f3);
//            this.f4 = new SimpleStringProperty(f4);
//            this.f5 = new SimpleStringProperty(f5);
//        }
//
//        public String getF1() {
//            return f1.get();
//        }
//
//        public String getF2() {
//            return f2.get();
//        }
//
//        public String getF3() {
//            return f3.get();
//        }
//
//        public String getF4() {
//            return f4.get();
//        }
//
//        public String getF5() {
//            return f5.get();
//        }
//    }
//
//    private final TableView<Record> tableView = new TableView<>();
//
//    private final ObservableList<Record> dataList = FXCollections.observableArrayList();
//
//    private void readCSV() throws IOException {
//
//        String path = "currencyConverter/book.csv";
//
//        BufferedReader br;
//
//        try {
//            br = new BufferedReader(new FileReader(path)){
//                String line;
//                while ((line = br.readLine()) != null) {
//                    String[] values = line.split(",", -1);
//                    Record record = new Record(values[0], values[1], values[2], values[3], values[4]);
//                    dataList.add(record);
//                }
//            }
//        } catch (IOException e) {
//                throw new RuntimeException(e);
//        }
//
//        TableColumn columnF1 = new TableColumn("f1");
//        columnF1.setCellValueFactory(
//                new PropertyValueFactory<>("f1"));
//
//        TableColumn columnF2 = new TableColumn("f2");
//        columnF2.setCellValueFactory(
//                new PropertyValueFactory<>("f2"));
//
//        TableColumn columnF3 = new TableColumn("f3");
//        columnF3.setCellValueFactory(
//                new PropertyValueFactory<>("f3"));
//
//        TableColumn columnF4 = new TableColumn("f4");
//        columnF4.setCellValueFactory(
//                new PropertyValueFactory<>("f4"));
//
//        TableColumn columnF5 = new TableColumn("f5");
//        columnF5.setCellValueFactory(
//                new PropertyValueFactory<>("f5"));
//
//        TableColumn columnF6 = new TableColumn("f6");
//        columnF6.setCellValueFactory(
//                new PropertyValueFactory<>("f6"));
//
//        tableView.setItems(dataList);
//        tableView.getColumns().addAll(columnF1, columnF2, columnF3, columnF4, columnF5, columnF6);
//
//        VBox vBox = new VBox();
//        vBox.setSpacing(10);
//        vBox.getChildren().add(tableView);
//
//        root.getChildren().add(vBox);
//
//        stage.setScene(new Scene(root, 700, 250));
//        stage.show();
//
//        readCSV();
//
//
//
//    }
}