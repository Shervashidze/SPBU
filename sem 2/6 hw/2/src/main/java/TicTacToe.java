import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class TicTacToe {
    /** private size of scene */
    private static int PANESIZE = 600;
    /** private size of Main field */
    private int size;
    private GridPane pane;
    private MainField mainField;

    /**
     * create new Main field to work with.
     * set size = size of the Main field.
     * remove Start Button.
     * create new Grid Pane and add it in the scene.
     */
    public void startGame() {
        mainField = new MainField();
        size = mainField.getSize();
        parent.getChildren().remove(buttonStart);
        pane = createPane();
        parent.getChildren().add(pane);
    }

    /** Create Pane
     * creates Pane with buttons.
     * Grid pane is (size * size);
     * @return created GridPane.
     */
    private GridPane createPane() {
        GridPane newPane = new GridPane();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                Button button = new Button();
                button.setOnAction(this::pressButton);
                button.setPrefSize(PANESIZE / size, PANESIZE / size);
                newPane.add(button, row, column);
            }
        }

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setMinWidth(PANESIZE / size);
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(PANESIZE / size);
        newPane.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints, columnConstraints);
        newPane.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints, rowConstraints);

        return newPane;
    }

    /**
     * Make turn in MainField with address of pressed button.
     * ShowEndGame if game over.
     *
     * @param event to know which button was pressed.
     */
    private void pressButton(ActionEvent event) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button currentButton = (Button) pane.getChildren().get(i * size + j);
                if (event.getSource().equals(currentButton)) {
                    if (mainField.makeTurn(i, j)) {
                        currentButton.textProperty().setValue(mainField.takeSignLastTurnPlayer());
                    }
                }

                if (mainField.isEnd()) {
                    showEndGame();
                }
            }
        }
    }

    /**
     * Show End Game.
     * delete Gridpane and all buttons in it.
     * Add textField with "Game Over."
     */
    private void showEndGame() {
        parent.getChildren().remove(pane);
        TextField field = new TextField("Game Over.");
        int textSize = 150;
        field.setPrefSize(textSize, textSize);
        field.setEditable(false);
        parent.getChildren().add(field);
        field.setAlignment(Pos.CENTER);
        field.setLayoutX(PANESIZE / 2 - textSize / 2);
        field.setLayoutY(PANESIZE / 2 - textSize / 2);
    }

    /** private start button to start game */
    @FXML
    private Button buttonStart;

    /** private AnchorPane - scene to work with*/
    @FXML
    private AnchorPane parent;
}
