package group144.shervashidze;

import group144.shervashidze.HashTable.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    static private Scanner in = new Scanner(System.in);
    static private PrintStream out = System.out;

    public static void main(String[] args) {
        boolean isEnd = false;
        HashTable table;
        table = new HashTable(new SumHash(42));

        while (!isEnd) {
            Commands command = workWithCommands.take();
            switch (command) {
                case addString: {
                    out.print("Value to add: ");
                    table.add(in.nextLine());
                    break;
                }
                case removeString: {
                    out.print("Value to remove: ");
                    table.remove(in.nextLine());
                    break;
                }
                case findString: {
                    out.print("Value to find: ");
                    int i = table.find(in.nextLine());
                    if (i == -1)
                        out.print("Value not found.\n");
                    else {
                        out.print("Value is in the ");
                        out.print(String.valueOf(i));
                        out.print(" row.\n");
                    }
                    break;
                }
                case info: {
                    out.print(table.getStatistics());
                    break;
                }
                case print: {
                    out.print(table.getTable());
                    break;
                }
                case takeFromFile: {
                    addFromFile(table);
                    break;
                }
                case changeHash: {
                    takeHash(table);
                    break;
                }
                case help: {
                    printHelp();
                    break;
                }
                case end: {
                    isEnd = true;
                    break;
                }
                default:
                    out.print("Wrong command, try again.\n");
            }
        }
    }

    private static void printHelp() {
        out.print("List of available commands:\n");
        out.print("add\n");
        out.print("remove\n");
        out.print("contains\n");
        out.print("print\n");
        out.print("take from file\n");
        out.print("change hash\n");
        out.print("end\n");
        out.print("help\n");
    }

    private static void takeHash(HashTable table) {
        while (true) {
            out.print("Choose type of hash: \n");
            out.print("- sum hash.\n");
            out.print("- polynomial hash.\n");
            String hash = in.nextLine();
            switch (hash) {
                case "sum hash": {
                    out.print("mod: ");
                    int mod = Integer.valueOf(in.nextLine());
                    try {
                        table.switchHash(new SumHash(mod));
                        return;
                    } catch (IllegalArgumentException exception) {
                        out.print("Wrong mod. Try again.\n");
                    }
                    break;
                }
                case "polynomial hash": {
                    out.print("power: ");
                    int power = Integer.valueOf(in.nextLine());
                    out.print("mod: ");
                    int mod = Integer.valueOf(in.nextLine());
                    try {
                        table.switchHash(new PolynomialHash(power, mod));
                        return;
                    } catch (IllegalArgumentException exception) {
                        out.print("\nWrong mod or power. Try again.\n");
                    }
                    break;
                }
                default:
                    out.print("Invalid key, try again.\n");
                    break;
            }
        }
    }

    private static void addFromFile(HashTable table) {
        while (true) {
            out.print("Add file name: ");
            String fileName = in.nextLine();

            try {
                FileReader fileReader = new FileReader(fileName);
                Scanner fileScanner = new Scanner(fileReader);
                while (fileScanner.hasNext()) {
                    String value = fileScanner.next();
                    table.add(value);
                }
                fileReader.close();
                return;
            } catch (FileNotFoundException e) {
                out.print(String.format("File \"%s\" was not found.\n", fileName));
            } catch (IOException e) {
                out.print(String.format("Couldn't close file \"%s\"", fileName));
            }
        }
    }
}
