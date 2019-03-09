package group244.shervashidze;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

class ExitWindows {
    /**
     * Creating exitWindow with some information
     * @param state of the endgame(win or lose)
     * @param name - name of the player
     */
    static void exitWindow(String state, String name) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, null, ButtonType.CLOSE);
        alert.setHeaderText(null);
        alert.setTitle("Exit");;
        if (name.contains("Server")) {
            alert.setContentText("Server " + state);
        } else {
            alert.setContentText("Client " + state);
        }
        alert.setOnCloseRequest(event -> System.exit(0));
        alert.show();
    }

    /**
     * Exit window after pressed ESCAPE
     */
    static void escapeeWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, null, ButtonType.CLOSE);
        alert.setHeaderText(null);
        alert.setTitle("Exit");
        alert.setContentText("Player pressed the escape");
        alert.setOnCloseRequest(event -> System.exit(0));
        alert.show();
    }
}
