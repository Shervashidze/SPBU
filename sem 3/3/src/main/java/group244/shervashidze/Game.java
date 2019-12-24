package group244.shervashidze;

import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class for connection with enemy
 */
public abstract class Game implements AutoCloseable {
    volatile PrintWriter out;
    volatile BufferedReader in;
    private final ExecutorService sender = Executors.newSingleThreadExecutor();

    /**
     * Method for one-time init
     */
    private void init() {
        if (in == null) {
            synchronized (this) {
                initialization();
            }
        }
    }

    /** initialization **/
    protected abstract void initialization();

    @Override
    public void close() {
        try {
            out.close();
            in.close();
            sender.shutdown();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Error, the connection is already closed");
            alert.showAndWait();
            System.exit(1);
        }
    }

    /**
     * sends commands
     * @param commands - list of the commands to send
     */
    public void send(List<KeyCode> commands) {
        if (commands.isEmpty()) {
            return;
        }
        List<KeyCode> commandsToSend = new LinkedList<>(commands);
        Collections.copy(commandsToSend, commands);
        sender.submit(() -> {
            init();
            for (KeyCode command : commandsToSend) {
                out.println(command);
            }
            out.flush();
        });
    }

    /**
     * receive enemy's commands
     */
    public KeyCode receive() {
        initialization();
        return in.lines().limit(1).findAny().map(KeyCode::valueOf).orElse(null);
    }
}
