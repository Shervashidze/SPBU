import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TicTacToe {
    private int size;
    private MainField field;

    /** Initialization method */
    public void initialize() {
        field = new MainField();
        size = field.getSize();
        buttons = new Button[size * size];
        for (int i = 0; i < size * size; i++) {
            buttons[i] = (Button) parent.getChildren().get(i);
        }
    }

    /**
     * Make turn in MainField with address of pressed button.
     * ShowEndGame with winner if game over.
     *
     * @param event to know which button was pressed.
     */
    @FXML
    private void pressButton(ActionEvent event) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button currentButton = buttons[i * size + j];
                if (event.getSource().equals(currentButton)) {
                    if (field.makeTurn(i, j)) {
                        currentButton.textProperty().setValue(field.takeSignLastTurnPlayer());
                    }
                }

                if (field.isEnd() == MainField.GameState.DRAW) {
                    showEndGame("Draw");
                } else if (field.isEnd() == MainField.GameState.XWON) {
                    showEndGame("Player 1 won");
                } else if (field.isEnd() == MainField.GameState.OWON) {
                    showEndGame("Player 2 won");
                }
            }
        }
    }

    /**
     * showEndGame.
     * @param string to identify who won.
     */
    private void showEndGame(String string) {
        Alert finishMessage = new Alert(Alert.AlertType.INFORMATION);
        finishMessage.setTitle("Game over");
        finishMessage.setHeaderText("Game over");
        finishMessage.setContentText(string);

        finishMessage.showAndWait();
        newGame();
    }

    /**
     * create new Main Field.
     * Set text of each button as empty string.
     */
    @FXML
    private void newGame() {
        field = new MainField();
        for (int i = 0; i < size * size; i++) {
            buttons[i].setText("");
        }
    }

    /* private array of buttons */
    @FXML
    private Button[] buttons;

    /** private AnchorPane - scene to work with*/
    @FXML
    private GridPane parent;
}
