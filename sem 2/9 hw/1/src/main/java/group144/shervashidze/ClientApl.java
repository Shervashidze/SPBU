package group144.shervashidze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApl extends Application {
    private ClientController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = loader.load();
            controller = loader.getController();
            primaryStage.setTitle("Tic Tac Toe. Client");
            primaryStage.setScene(new Scene(root, 600, 600));
            primaryStage.setMinHeight(350);
            primaryStage.setMinWidth(350);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (controller.getAreWeExiting()) {
            controller.sendExitMessage();
        } else {
            controller.closeConnection();
        }
        try {
            super.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
