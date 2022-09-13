package currencyConverter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class AdminUserController {
    @FXML
    private javafx.scene.control.Button logoutButton;
    Stage stage;
    private Scene scene;
    private Parent root;

    public void returnMainPage(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        mainController.setStage(stage);
        Csv csv = new Csv("Book1.csv");
        mainController.setCsv(csv);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logout(ActionEvent event) {
        //build a window for stay in the page or logout.
        Alert alter = new Alert(AlertType.CONFIRMATION);
        alter.setTitle("Logout");
        alter.setHeaderText("About logout :(");
        alter.setContentText("Do you want to exiting?");

        // get a handle to the stage, close the page
        if (alter.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            System.out.println("you Successfully logged out.");
            stage.close();
        }}
    public void returnAdminPage(javafx.event.ActionEvent actionEvent) throws IOException {
        //In the exchange rate page can return the previous page(admin page)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/AdminUser.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // first situation look the currency types => the summary pages
    public void currencyTypeToSummary(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/SummaryTable.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //second situation => exchange the rate page
    public void SwitchExchangeRate(javafx.event.ActionEvent actionEvent) throws IOException {
        //go to the ExchangeRate page, update the rate.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ExchangeRate.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ChangeExchangeRateController controller = loader.getController();
        controller.setStage(stage);
        Csv csv = new Csv("Book1.csv");
        controller.setCsv(csv);
        stage.setScene(scene);
        stage.show();
    }

    //third situation => switch to the currency history
    public void SwitchToRatesHistory(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/RatesHistory.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Fourth situation => add new currency type, include time, currency type


    public void SwitchToAddNewCurrencyType(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/AddNewTypes.fxml"));
        Parent root = loader.load();

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        AddNewCurrencyTypeController controller = loader.getController();
        controller.setStage(stage);
        Csv csv = new Csv("Book1.csv");
        controller.setCsv(csv);
        stage.setScene(scene);
        stage.show();

    }


}


