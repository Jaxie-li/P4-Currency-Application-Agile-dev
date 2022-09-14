package currencyConverter.controller;
import currencyConverter.ultils.CSV;
import currencyConverter.ultils.ReadDate;
import currencyConverter.ultils.TXT;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;

public class AdminUserController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private javafx.scene.control.Button logoutButton;
    @FXML
    private Button dailyUpdate;

    @FXML
    private Button updateChanges;


    public void returnMainPage(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/Main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        mainController.setStage(stage);
        CSV csv = new CSV("Book1.csv");
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

    public void switchToObtainPopularTable(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ObtainPopularTable.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ObtainPopularTableController controller = loader.getController();
        controller.setStage(stage);
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
        CSV csv = new CSV("Book1.csv");
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
        CSV csv = new CSV("Book1.csv");
        controller.setCsv(csv);
        stage.setScene(scene);
        stage.show();

    }


    public void setDailyUpdate(ActionEvent event) throws IOException, ParseException {
        TXT dailyUpdateWriter = new TXT();
        dailyUpdateWriter.updateCsv2("Book2.txt");
        ReadDate todayDate = new ReadDate();
        String todayDay = todayDate.readCSV("Book1.csv");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(todayDay));
        c.add(Calendar.DATE, 1);
        todayDay = sdf.format(c.getTime());
        System.out.println(todayDay);

        CSV csvReader = new CSV("Book1.csv");
        List<String> csvOutput = csvReader.readCSV("Book1.csv");
        TXT writer = new TXT();

        List<String> newCsv = new ArrayList<>();

        for (int i = 0; i < csvOutput.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();

            if (i > 0) {
                String[] values = csvOutput.get(i).split(",");
                values[0] = todayDay;
                stringBuilder.append(values[0]).append(",");

                for (int j = 1; j < values.length; j++) {
                    if (j == values.length - 1) {
                        stringBuilder.append(values[j]);
                    } else {
                        stringBuilder.append(values[j]).append(",");
                    }
                }

                newCsv.add(String.valueOf(stringBuilder));
            } else {
                newCsv.add(csvOutput.get(i));
            }
        }

        for (int i = 0; i < newCsv.size(); i++) {
            if (i == 0) {
                writer.writeFile2("Book1.csv", newCsv.get(i));
            } else {
                writer.writeFile("Book1.csv", newCsv.get(i));
            }
        }

    }





}



