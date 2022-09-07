package currencyConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNewCurrency {

    @FXML
    private Button addCurrency;

    @FXML
    private TextField currencyRate;

    @FXML
    private TextField newCurrencyType;

    @FXML
    void addCurrency(ActionEvent event) throws IOException {

        String tmpCurrency = newCurrencyType.getText();
        String tmpRate = currencyRate.getText();

        System.out.println(tmpCurrency);
        System.out.println(tmpRate);

    }

    @FXML
    void returnAdminPage(ActionEvent event) {

    }

}
