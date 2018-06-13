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
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerController class for the server part of 2-players TicTacToe game.
 * Application for the some amount of TicTacToe games with only one opponent. May crash if there are more connections.
 */
public class ServerController {
    private static final int port = 12345;
    /* size of the gameField */
    private int size;
    /* MainField */
    private MainField field;

    /* inputStream to client
     * Command that we can get:
     *   0-8 - opponent's turns.
     *   9 - opponent pressed new Game.
     *   'e' - opponent exited game.
     */
    private InputStream in;

    /* outputStream to client - check (inputStream in) to get info about commands */
    private PrintStream out;
    /* connection to client - second player */
    private Socket client;
    /* boolean to get message about another player pressed new game */
    private boolean isNewGame = false;
    /* boolean to check who ended the game */
    private boolean areWeExiting = true;

    /**
     * Initialization method
     * Create new Mainfield, take size of this field.
     * Waiting for first connection.
     * Be careful with the server port window, if it closed u cant get a server port.
     */
    public void initialize() {
        field = new MainField();
        size = field.getSize();
        buttons = new Button[size * size];
        for (int i = 0; i < size * size; i++) {
            buttons[i] = (Button) parent.getChildren().get(i);
        }
        setDisable(true);

        try {
            ServerSocket socket = new ServerSocket(port);
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setContentText("Click ok to start waiting for connection.");
            message.showAndWait();

            client = socket.accept();
            out = new PrintStream(client.getOutputStream());
            in = client.getInputStream();

            status.setText("Your turn");
            setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

                        if (field.isEnd() == MainField.GameState.PLAYING) {
                            waitForOppenentTurn();
                        }

                        status.setText("Opponent's turn");
                        setDisable(true);

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
        isNewGame = true;
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
        if (!isNewGame) {
            out.print(9);
            out.flush();
        }
        field = new MainField();
        for (int i = 0; i < size * size; i++) {
            buttons[i].setText("");
        }
        status.setText("Your turn");
        setDisable(false);
        isNewGame = false;
    }

    /* true if we should send exit message to another player, false otherwise */
    public boolean getAreWeExiting() {
        return areWeExiting;
    }

    /* sending exit message to another player */
    public void SendExitMessage() {
        out.print('e');
        out.flush();
    }

    /* close connection with client */
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
