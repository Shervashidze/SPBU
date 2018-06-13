package group144.shervashidze;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/** Client controller class for the client part of 2-players TicTacToe game.
 * Application can connect only with one server, u should know the server port before the game.
 */
public class ClientController {
    private static final int port = 12345;
    /* size of the gameField */
    private int size;
    /* MainField */
    private MainField field;
    /* connection to server */
    private Socket client;

    /* inputStream to server
    * Command that we can get:
    *   0-8 - opponent's turns.
    *   9 - opponent pressed new Game.
    *   'e' - opponent exited game.
    */
    private InputStream in;

    /* outputStream to server - check (inputStream in) to get info about commands */
    private PrintStream out;
    /* boolean to get message about second player pressed new game */
    private boolean isNewGame = false;
    /* boolean to check who ended the game */
    private boolean areWeExiting = true;

    /**
     * Initialization method
     * Create new Mainfield, take size of this field.
     * With the help of console trying to connect to server.
     * Disable all buttons and w8 for opponent's turn.
     */
    public void initialize() {
        field = new MainField();
        size = field.getSize();
        buttons = new Button[size * size];
        for (int i = 0; i < size * size; i++) {
            buttons[i] = (Button) parent.getChildren().get(i);
        }
        while (true) {
            try {
                client = new Socket("localhost", port);
                in = client.getInputStream();
                out = new PrintStream(client.getOutputStream());
                break;
            } catch (IOException e) {
                /* do nothing */
            }
        }

        setDisable(true);
        status.setText("Opponent's turn");
        waitForOppenentTurn();
    }

    /**
     * disable or enable all buttons.
     * @param status - boolean to get status of buttons.
     */
    private void setDisable(boolean status) {
        for (int i = 0; i < size * size; i++) {
            parent.getChildren().get(i).setDisable(status);
        }
        start.setDisable(status);
    }

    /**
     * Make turn in MainField with address of pressed button.
     * ShowEndGame with winner if game over.
     * Send your turn to the server; if game is not ended waiting for opponent's turn.
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

                        out.print(i * size + j);
                        out.flush();

                        status.setText("Opponent's turn");
                        setDisable(true);
                        if (field.isEnd() == MainField.GameState.PLAYING) {
                            waitForOppenentTurn();
                        }

                        isEnd();
                    }
                }
            }
        }
    }

    /**
     * Wait for Opponent Turn.
     * Create thread to actively wait for opponent tur.
     * if opponent closed his game when we are making turn we get an exception in socket,
     * set areWeExiting false in that case.
     */
    private void waitForOppenentTurn() {
        new Thread(() -> {
            int position = 'e';
            while (true) {
                try {
                    if (in.available() == 0) {
                        try {
                            position = in.read() - '0';
                        } catch(java.net.SocketException e) {
                            areWeExiting = false;
                        }
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            int finalPosition = position;
            Platform.runLater(() -> oppenentTurn(finalPosition));
        }).start();
    }

    /**
     * opponent turn to proceed opponent's actions.
     * if we received 9 - starting new game, 0-8 - making turn in this part of the field, 'e' - closing game.
     * @param position proceed opponent movement.
     */
    private void oppenentTurn(int position) {
        if (position == 9) {
            isNewGame = true;
            newGame();
        } else if (position >= 0 && position < 9) {
            field.makeTurn(position / 3, position % 3);
            buttons[position].textProperty().setValue(field.takeSignLastTurnPlayer());
            status.setText("Your turn");
            setDisable(false);
            isEnd();
        } else {
            Alert exitMessage = new Alert(Alert.AlertType.INFORMATION);
            exitMessage.setTitle("Game over");
            exitMessage.setContentText("Your opponent left the game.");
            exitMessage.showAndWait();
            areWeExiting = false;
            Platform.exit();
        }
    }

    /**
     * checking game state.
     * if game was ended show end Message.
     */
    private void isEnd() {
        if (field.isEnd() == MainField.GameState.DRAW) {
            showEndGame("Draw");
        } else if (field.isEnd() == MainField.GameState.XWON) {
            showEndGame("Player 1 won");
        } else if (field.isEnd() == MainField.GameState.OWON) {
            showEndGame("Player 2 won");
        }
    }

    /**
     * showEndGame.
     * show alert message about sho won.
     * @param string to identify who won.
     */
    private void showEndGame(String string) {
        Alert finishMessage = new Alert(Alert.AlertType.INFORMATION);
        finishMessage.setTitle("Game over");
        finishMessage.setHeaderText("Game over");
        finishMessage.setContentText(string);
        isNewGame = true;

        finishMessage.showAndWait();
        newGame();
    }

    /**
     * create new Main Field.
     * Set text of each button as empty string.
     * Waiting for opponents turn.
     */
    @FXML
    private void newGame() {
        if (!isNewGame) {
            out.print(9);
            out.flush();
        }
        field = new MainField();
        for (int i = 0; i < size * size; i++) {
            buttons[i].setText("");
        }

        status.setText("Opponent turn");
        isNewGame = false;
        setDisable(true);
        waitForOppenentTurn();
    }

    /* true if we should send exit message to another player, false otherwise */
    public boolean getAreWeExiting() {
        return areWeExiting;
    }

    /* sending exit message to another player */
    public void sendExitMessage() {
        out.print('e');
        out.flush();
    }

    /* close connection with server */
    public void closeConnection() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Button to start new game */
    @FXML
    private Button start;

    /* TextField to show status of the game */
    @FXML
    private TextField status;

    /* private array of buttons */
    @FXML
    private Button[] buttons;

    /** private AnchorPane - scene to work with*/
    @FXML
    private GridPane parent;
}
