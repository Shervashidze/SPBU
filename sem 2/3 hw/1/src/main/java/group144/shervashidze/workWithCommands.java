package group144.shervashidze;

import java.io.PrintStream;
import java.util.Scanner;

public class workWithCommands {
    static private Scanner in = new Scanner(System.in);
    static private PrintStream out = System.out;

    public static Commands take() {
        while (true) {
            out.print("Command: ");
            String command = in.nextLine();
            switch (command) {
                case "add":
                    return Commands.addString;
                case "remove":
                    return Commands.removeString;
                case "contains":
                    return Commands.findString;
                case "info":
                    return Commands.info;
                case "print":
                    return Commands.print;
                case "take from file":
                    return Commands.takeFromFile;
                case "change hash":
                    return Commands.changeHash;
                case "end":
                    return Commands.end;
                case "help":
                    return Commands.help;
                default:
                    out.print("Incorrect command. Type help to see list of the commands.\n");
            }
        }
    }
}
