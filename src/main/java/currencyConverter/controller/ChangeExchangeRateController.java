package currencyConverter.controller;
import currencyConverter.ultils.CSV;
import currencyConverter.ultils.ReadDate;
import currencyConverter.ultils.TXT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ChangeExchangeRateController {
    private Stage stage;
    private Scene scene;

    private final Path path = Paths.get("Popular.txt");
    private final Path historyPath = Paths.get("Book2.txt");
    @FXML
    private ChoiceBox<String> OriginBox;
    @FXML
    private ChoiceBox<String> ChangedBox;
    @FXML
    private TextField newRate;
    @FXML
    private Button update;

    /**
     * This function is used to set the current stage
     * @param stage: the stage which pass from previous page
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setCSV(CSV csv) {
        this.csv = csv;
        int length = this.csv.records.size();
        for (int i = 1; i < length; i++) {
            OriginBox.getItems().add(this.csv.records.get(i).get(1));
            ChangedBox.getItems().add(this.csv.records.get(i).get(1));
        }
    }
    public CSV csv;



    public void returnAdminPage(javafx.event.ActionEvent actionEvent) throws IOException {
        //In the exchange rate page can return the previous page(admin page)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/AdminUser.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setUpdate(ActionEvent event) throws IOException {
        String tmpRate = newRate.getText();
        String originalCurrency = OriginBox.getValue();
        String targetCurrency = ChangedBox.getValue();
        int csvOriginalIndex = csv.indexOf(originalCurrency);
        int csvTargetIndex = csv.indexOf(targetCurrency);

//        System.out.println(tmpRate);
//        System.out.println(originalCurrency);
//        System.out.println(targetCurrency);

        if (originalCurrency.isEmpty() || targetCurrency.isEmpty() || tmpRate.isEmpty()){
            System.out.println("There exists invalid input !");

        } else {
            ReadDate tmpReadDate = new ReadDate();
            String date = tmpReadDate.getDate("Book1.csv");
            String add = "Modified";
            String content = date + "," + add + "," + originalCurrency + "," + targetCurrency + "," + tmpRate;

            String filePath = "Changes.txt";
            TXT writer = new TXT();
            writer.appendFileMode(filePath, content);
            // 根据change.txt里的targetC 和 currentC 来改变 popularC的 趋势
            TXT updateCsv = new TXT();
            ReadDate todayReadDate = new ReadDate();
            String checkDate = todayReadDate.getDate("Book1.csv");
            updateCsv.appliedChanges("Changes.txt", "Book1.csv", checkDate);

            double reciprocal  = 1 / Double.parseDouble(tmpRate);
            String reciprocalString = String.format("%.4f", reciprocal);

            // read popular.txt
            List<String> lines = Files.readAllLines(path);

            int originalIndex = -1;
            int targetIndex = -1;

            String[] temp = lines.get(0).split(",");
            for (int i = 1; i < temp.length; i++) {
                if (temp[i].equalsIgnoreCase(originalCurrency)) {
                    originalIndex = i;
                }
                else if (temp[i].equalsIgnoreCase(targetCurrency)) {
                    targetIndex = i;
                }
            }

            if (originalIndex != -1 && targetIndex != -1) {

                List<String> historyLines = Files.readAllLines(historyPath);
                int finalIndex = -1;

                for (int i = 0; i < historyLines.size(); i++) {
                    if (historyLines.get(i).contains("DATE,From/To")) {
                        finalIndex = i;
                    }
                }

                TXT updatePopularTxt = new TXT();

                updatePopularTxt.overwriteFile("Popular.txt", lines.get(0));

                for (int i = 1; i < lines.size(); i++) {

                    String[] tokens = lines.get(i).split(",");
                    String s = tokens[0] + ",";

                    for (int j = 1; j < tokens.length; j++) {

                        if (i == originalIndex && j == targetIndex) {

                            String[] tmpToks = historyLines.get(csvOriginalIndex+finalIndex+1).split(",");
                            String suffix;
                            double original = Double.parseDouble(tmpToks[csvTargetIndex+2]);

                            if (original < Double.parseDouble(tmpRate)) {
                                suffix = ":I";
                            } else if (original > Double.parseDouble(tmpRate)) {
                                suffix = ":D";
                            } else {
                                suffix = ":E";
                            }
                            s += tmpRate + suffix;
                        } else if (j == originalIndex && i == targetIndex) {
                            // reversed tmpRate
                            String[] tmpToks = historyLines.get(csvTargetIndex+finalIndex+1).split(",");
                            String suffix;

                            double original = Double.parseDouble(tmpToks[csvOriginalIndex+2]);
                            if (original < Double.parseDouble(reciprocalString)) {
                                suffix = ":I";
                            } else if (original > Double.parseDouble(reciprocalString)) {
                                suffix = ":D";
                            } else {
                                suffix = ":E";
                            }

                            s += reciprocalString + suffix;
                        } else {
                            s += tokens[j];
                        }
                        if (j < tokens.length-1) {
                            s += ",";
                        }
                    }
                    updatePopularTxt.appendFileMode("Popular.txt",s);
                }
            }
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ExchangeRate.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ChangeExchangeRateController controller = loader.getController();
        controller.setStage(stage);
        CSV csv = new CSV("Book1.csv");
        controller.setCSV(csv);
        stage.setScene(scene);
        stage.show();
    }
}