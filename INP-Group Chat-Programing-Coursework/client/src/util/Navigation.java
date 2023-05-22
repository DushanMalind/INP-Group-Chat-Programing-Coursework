package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane mealPackagesIdMain;
    public static void navigation(Routes routes,AnchorPane mealPackagesIdMain) throws IOException {
        Navigation.mealPackagesIdMain = mealPackagesIdMain;
        Navigation.mealPackagesIdMain.getChildren().clear();
        Stage window = (Stage) Navigation.mealPackagesIdMain.getScene().getWindow();

        switch (routes) {
            case CLIENT1:
                window.setTitle("Client1 From");
                initUI("client1From.fxml");
                break;
            case CLIENT2:
                window.setTitle("Client2 From");
                initUI("client2From.fxml");
                break;


        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.mealPackagesIdMain.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/"+location)));
    }
}
