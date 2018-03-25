package group144.shervashidze;

import static group144.shervashidze.MatrixToString.matrixToString;

public class ConsoleSpiralWriter implements SpiralWriter {
    @Override
    public void print(int[][] array) {
        String string = matrixToString(array);
        if (string == null) {
            System.out.println("Wrong array length.");
        }
        else {
            System.out.print("Elements: ");
            System.out.println(matrixToString(array));
        }
    }
}
