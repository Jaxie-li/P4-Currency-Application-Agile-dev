package currencyConverter.controller;

import currencyConverter.ultils.CSV;
import currencyConverter.ultils.TXT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ObtainPopularTableController {
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Path path = Paths.get("Popular.txt");
    private CSV csv = new CSV("book1.csv");

    @FXML
    private ChoiceBox<String> incomingChoiceBox;

    @FXML
    private ChoiceBox<String> leavingChoiceBox;

    @FXML
    private void initialize () throws IOException {
        List<String> lines = Files.readAllLines(path);
        for (String s :
                csv.currencies) {
            incomingChoiceBox.getItems().add(s);
        }
        String[] tokens = lines.get(0).split(",");
        for (int i = 1; i < tokens.length; i++) {
            leavingChoiceBox.getItems().add(tokens[i]);
        }

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


    public void applyChange(ActionEvent actionEvent) throws IOException {
        String in = incomingChoiceBox.getValue();
        String out = leavingChoiceBox.getValue();
        int inIndex = csv.indexOf(in);
//        int outIndex = csv.indexOf(out);

        List<String> lines = Files.readAllLines(path);
        
        TXT writer = new TXT();
        //write to Popular.txt
        writer.writeFile2("Popular.txt", lines.get(0).replace(out, in));

        int outPopularIndex = -1;
        String[] others = new String[4];
        String[] temp = lines.get(0).split(",");
        int count = 0;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i].equalsIgnoreCase(out)) {
                outPopularIndex = i;
                others[count++] = in;
            }
            else {
                others[count++] = temp[i];
            }
        }


        for (int i = 1; i < lines.size(); i++) {
            String[] tokens = lines.get(i).split(",");
            String s = tokens[0] + ",";
            if (i == outPopularIndex) {
                s = in + ",";
                System.out.println("executed");
                for (int j = 0; j < 4; j++) {
                    s += csv.records.get(inIndex+1).get(csv.indexOf(others[j])+2) + ":E";
                    if (j < others.length-1) {
                        s += ",";
                    }
                }

            }
            else {
                // j = 0, 1, 2, 3
                for (int j = 0; j < 4; j++) {
                    s += csv.records.get(csv.indexOf(others[i-1]) + 1).get(csv.indexOf(others[j]) + 2) + ":E";
                    if (j < others.length-1) {
                        s += ",";
                    }
                }

            }
            // TODO: write s to Popular.txt
            writer.writeFile("Popular.txt", s);
            System.out.print(s);
        }
    }
}
