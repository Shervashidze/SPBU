/**
 * Main field.
 * Field to play Tic-tac-toe.
 */
public class MainField {
    /** private Size of the main field */
    private static int SIZE = 3;

    private enum Players {FIRST, SECOND}

    private Players currentPlayer = Players.FIRST;

    private enum State {X, O, NOTHING}

    /**
     * States of the elements SIZE * SIZE field.
     * Element address is (row * SIZE + column).
     */
    private State[] states = new State[SIZE * SIZE];

    /** Initializing method: sets all elements of states as NOTHING */
    public MainField() {
        for (int i = 0; i < SIZE * SIZE; i++) states[i] = State.NOTHING;
    }

    /**
     * getSize.
     * @return Size of the Main window.
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * isEnd.
     * @return true if all elements is not nothing, if 3 X's or O's are on the column, on the rows or on the diagonals.
     */
    public boolean isEnd() {
        for (int row = 0; row < SIZE; row++) {
            if (states[row * SIZE] == states[row * SIZE + 1] && states[row * SIZE] == states[row * SIZE + 2] &&
                    states[row * SIZE] != State.NOTHING) {
                return true;
            }
        }
        for (int column = 0; column < SIZE; column++) {
            if (states[column] == states[column + SIZE] && states[column] == states[column + SIZE * 2] &&
                    states[column] != State.NOTHING) {
                return true;
            }
        }

        if (states[0] == states[4] && states[0] == states[8] && states[0] != State.NOTHING) return true;

        if (states[2] == states[4] && states[2] == states[6] && states[2] != State.NOTHING) return true;

        boolean answer = true;
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (states[i] == State.NOTHING) {
                answer = false;
            }
        }

        return answer;
    }

    /**
     * Make turn.
     * @param row to make turn
     * @param column to make turn
     */
    public void makeTurn(int row, int column) {
        if (states[row * SIZE + column] != State.NOTHING) {
            return;
        }

        if (currentPlayer == Players.FIRST) {
            states[row * SIZE + column] = State.X;
            currentPlayer = Players.SECOND;
        } else {
            states[row * SIZE + column] = State.O;
            currentPlayer = Players.FIRST;
        }
    }

    /**
     * @return "X" if current player is second player, "O" otherwise.
     */
    public String takeSignLastTurnPlayer() {
        if (currentPlayer == Players.FIRST) {
            return "O";
        } else {
            return "X";
        }
    }
}
