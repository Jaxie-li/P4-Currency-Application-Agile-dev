package currencyConverter;

import currencyConverter.controller.MainController;
import currencyConverter.ultils.CSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginAdmin {

    public LoginAdmin(){}
    Stage stage;
    private Scene scene;

    @FXML private javafx.scene.control.Button loginButton;
    @FXML private javafx.scene.control.Label wrongMessage;
    @FXML private TextField uname; //text field's username
    @FXML private PasswordField pw; // password field's password

    //return button => main page
    public void returnToMainPage(ActionEvent actionEvent) throws IOException {
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

    public void userLogin(ActionEvent actionEvent) throws IOException{
        checkLogin();}

    //Check if username and password match, when match => click login => admin page.
    //if not match => click login =>error message, No permission to access the admin page.
    private void  checkLogin() throws IOException{
        //the first one username with password.
        if(uname.getText().toString().equals("admin") && pw.getText().toString().equals("123")){
            wrongMessage.setText("Success! Welcome back admin.");
            //change scene to adminUser page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/AdminUser.fxml"));
            Parent root = loader.load();
            stage = (Stage) (loginButton.getScene().getWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        //the second one username with password.
        else if(uname.getText().toString().equals("admin1") && pw.getText().toString().equals("666")){
            wrongMessage.setText("Success! Welcome back admin1.");
            //change scene to adminUser page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/currencyConverter/AdminUser.fxml"));
            Parent root = loader.load();
            stage = (Stage) (loginButton.getScene().getWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        //without fill out password or username
        else if (uname.getText().isEmpty() || pw.getText().isEmpty()){
            wrongMessage.setText("Please enter your name or password");
        } else{
            wrongMessage.setText("Wrong username or password");
        }
    }

}
