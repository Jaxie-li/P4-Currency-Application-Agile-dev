package currencyConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeExchangeRateController {
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private Stage stage;

    private Scene scene;

    private Parent root;

    public void setCsv(Csv csv) {
        this.csv = csv;
        int length = this.csv.records.size();
        for (int i = 1; i < length; i++) {
            OriginBox.getItems().add(this.csv.records.get(i).get(1));
            ChangedBox.getItems().add(this.csv.records.get(i).get(1));
        }
    }
    public Csv csv;

    @FXML
    private ChoiceBox<String> OriginBox;

    @FXML
    private ChoiceBox<String> ChangedBox;

    @FXML
    private void initialize (){
    }

    public void returnAdminPage(javafx.event.ActionEvent actionEvent) throws IOException {
        //In the exchange rate page can return the previous page(admin page)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/AdminUser.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField newRate;
    @FXML
    private Button update;

    public void setUpdate(ActionEvent event) throws IOException {
        String tmpRate = newRate.getText();
        String originalCurrency = OriginBox.getValue();
        String targetCurrency = ChangedBox.getValue();

        System.out.println(tmpRate);
        System.out.println(originalCurrency);
        System.out.println(targetCurrency);

        if (originalCurrency.isEmpty() || targetCurrency.isEmpty() || tmpRate.isEmpty()){
            System.out.println("There exists invalid input !");

        } else {
            readDate tmpDate = new readDate();
            String date = tmpDate.readCsv("Book1.csv");
            String add = "Modified";
            String content = date + "," + add + "," + originalCurrency + "," + targetCurrency + "," + tmpRate;

            String filePath = "changes.txt";
            Txt writer = new Txt();
            writer.writeFile(filePath, content);
        }
    }
}