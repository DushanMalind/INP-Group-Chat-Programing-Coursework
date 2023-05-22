package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class LoginFromController {

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtLogin;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        if (txtLogin.getText().equalsIgnoreCase("client1")){
            /*Navigation.navigation(Routes.CLIENT1,root);*/
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/client1From.fxml"))));
            stage.setTitle("Client Chat 01");
            stage.show();
        }
        if (txtLogin.getText().equalsIgnoreCase("client2")){
            /*Navigation.navigation(Routes.CLIENT2,root);*/
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/client2From.fxml"))));
            stage.setTitle("Client Chat 02");
            stage.show();
        }
    }

}
