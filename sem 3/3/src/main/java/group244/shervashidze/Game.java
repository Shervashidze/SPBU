package group244.shervashidze;

import javafx.scene.input.KeyCode;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class for two sockets client and server
 */
public abstract class Game implements AutoCloseable {
    volatile PrintWriter out;
    volatile BufferedReader in;
    private final ExecutorService sender = Executors.newSingleThreadExecutor();

    /**
     * Method for correct initialization()
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
            throw new RuntimeException(e);
        }
    }

    /**
     * sends command
     * @param commands your List from commands
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
     * returns the received command
     */
    public KeyCode receive() {
        initialization();
        return in.lines()
                .limit(1)
                .findAny()
                .map(KeyCode::valueOf)
                .orElse(null);
    }
}
