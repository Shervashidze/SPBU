package group244.shervashidze;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * Class for work with client socket
 */
public class Client extends Game {

    private Socket client;
    private String ip;


    /**
     * initialization. Setting socket in and out.
     */
    @Override
    protected void initialization() {
        InetAddress inetAddress = null;
        if (client == null) {
            try {
                client = new Socket(inetAddress = InetAddress.getByName(ip), Server.PORT);
                out = new PrintWriter(client.getOutputStream());
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            } catch (UnknownHostException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, null, ButtonType.CLOSE);
                alert.setHeaderText(null);
                alert.setTitle("Exit");
                alert.setContentText("Unknown host: " + inetAddress.getHostAddress());
                System.exit(1);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, null, ButtonType.CLOSE);
                alert.setHeaderText(null);
                alert.setTitle("Exit");
                alert.setContentText("Fail to connect: " + inetAddress.getHostAddress());
                System.exit(1);
            }
        }
    }

    /**
     * Constructor
     */
    Client(String ip) {
        this.ip = Objects.requireNonNull(ip);
    }
}
