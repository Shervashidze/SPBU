package group144.shervashidze;

import java.io.*;

import static group144.shervashidze.MatrixToString.matrixToString;

public class FileSpiralWriter {
    private FileOutputStream file = null;

    public FileSpiralWriter(String fileName) throws FileNotFoundException {
        file = new FileOutputStream(fileName);
    }

    public void print(int[][] array) {
        String string = matrixToString(array);
        if (string == null) {
            System.out.println("Wrong array length.");
        }
        else {
            try {
                byte[] buffer = string.getBytes();
                file.write(buffer);
            } catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }

    }
}
