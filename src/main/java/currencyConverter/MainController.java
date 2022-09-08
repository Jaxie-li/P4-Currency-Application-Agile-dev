package currencyConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class MainController {


    private Stage stage;
    private Scene scene;
    private Parent root;

    // Define events
    public void onclick() {
        System.out.println("button clicked!");
    }

    public void switchToObtainSummaryTable(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/ObtainSummaryTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        ObtainSummaryController controller = loader.getController();
//        controller.setStage(stage);
//        // todo: 读取relative path 的csv
//        Csv csv = new Csv("Book1.csv");
//        controller.setCsv(csv);
//
//        stage.setScene(scene);
//        stage.show();

    }

    public void switchToPopularCurrencyTable(javafx.event.ActionEvent actionEvent) throws IOException {
        //TODO: GUI - style the first column of the table for it to look like a header
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/PopularCurrencyTable.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void adminLogin(){
        System.out.println("Admin Login Page should be invoked!");
    }

    public void switchToAdminUser(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/AdminUser.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

