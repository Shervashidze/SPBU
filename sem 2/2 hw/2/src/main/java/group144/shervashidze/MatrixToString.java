package group144.shervashidze;

public class MatrixToString {
    public static String matrixToString(int[][] array) {
        if (array == null || array[0].length % 2 == 0) {
            return null;
        }

        String answer = "";
        int shift = 1;
        int line = array[0].length / 2;
        int column = array[0].length / 2;
        int changeDirection = 0;
        boolean moveLine = false;
        boolean moveLeftAndDown = false;

        while (column < array[0].length) {
            int counter = 0;
            if (moveLeftAndDown)
                counter = -1;
            else
                counter = 1;

            if (moveLine) {
                for (int i = 0; i < shift; i++) {
                    answer = answer + (array[line][column]) + " ";
                    line -= counter;
                }

                changeDirection++;
                moveLine = false;
            }
            else {
                for (int i = 0; i < shift; i++) {
                    answer = answer + (array[line][column]) + " ";
                    column += counter;
                }

                changeDirection++;
                moveLine = true;
            }

            if (changeDirection == 2) {
                shift++;
                changeDirection = 0;
                moveLeftAndDown = !moveLeftAndDown;
            }
        }

        return answer;
    }
}
