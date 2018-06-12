package group144.shervashidze;

/**
 * Main field.
 * Field to play Tic-tac-toe.
 */
public class MainField {
    /** private Size of the main field */
    private static int SIZE = 3;

    /** enum to assign players */
    private enum Players {FIRST, SECOND}

    /** enum to assign game states */
    public enum GameState {PLAYING, DRAW, XWON, OWON}

    /** current player */
    private Players currentPlayer = Players.FIRST;

    /** enum to assign state of the game button */
    private enum State {X, O, NOTHING}

    /**
     * States of the elements SIZE * SIZE field.
     * Element address is (row * SIZE + column).
     */
    private State[] states = new State[SIZE * SIZE];

    /** Initializing method: sets all elements of states as NOTHING */
    public MainField() {
        for (int i = 0; i < SIZE * SIZE; i++) {
            states[i] = State.NOTHING;
        }
    }

    /**
     * getSize.
     * @return Size of the Main window.
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * is End.
     * @return XWON if first player won, OWON if second won, DRAW if no one won, and PLAYING if game is not end.
     */
    public GameState isEnd() {
        for (int row = 0; row < SIZE; row++) {
            if (states[row * SIZE] == states[row * SIZE + 1] &&
                    states[row * SIZE] == states[row * SIZE + 2] && states[row * SIZE] != State.NOTHING) {
                if (states[row * SIZE] == State.X) {
                    return GameState.XWON;
                } else {
                    return  GameState.OWON;
                }
            }
        }

        for (int column = 0; column < SIZE; column++) {
            if (states[column] == states[column + SIZE] && states[column] == states[column + SIZE * 2] &&
                    states[column] != State.NOTHING) {
                if (states[column] == State.X) {
                    return GameState.XWON;
                } else {
                    return  GameState.OWON;
                }
            }
        }

        int firstOfMainDiag = 0;
        boolean isMainDiagEnd = true;
        if (states[firstOfMainDiag] == State.NOTHING){
            isMainDiagEnd = false;
        }
        for (int i = 0; i < SIZE; i++) {
            isMainDiagEnd = isMainDiagEnd && (states[firstOfMainDiag] == states[i * SIZE + i]);
        }
        if (isMainDiagEnd) {
            if (states[firstOfMainDiag] == State.X) {
                return GameState.XWON;
            } else {
                return GameState.OWON;
            }
        }

        int firstOfSecondDiag = SIZE - 1;
        boolean isSecondDiagEnd = true;
        if (states[firstOfSecondDiag] == State.NOTHING){
            isSecondDiagEnd = false;
        }
        for (int i = SIZE - 1; i >= 0; i--) {
            isSecondDiagEnd = isSecondDiagEnd && states[firstOfSecondDiag] == states[(SIZE - 1 - i) * SIZE + i];
        }
        if (isSecondDiagEnd) {
            if (states[firstOfSecondDiag] == State.X) {
                return GameState.XWON;
            } else {
                return GameState.OWON;
            }
        }

        boolean answer = true;
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (states[i] == State.NOTHING) {
                answer = false;
            }
        }

        if (answer) {
            return GameState.DRAW;
        } else {
            return GameState.PLAYING;
        }
    }

    /**
     * Make turn.
     * @param row to make turn
     * @param column to make turn
     * @return true if turn have consequences, false otherwise.
     */
    public boolean makeTurn(int row, int column) {
        if (states[row * SIZE + column] != State.NOTHING) {
            return false;
        }

        if (currentPlayer == Players.FIRST) {
            states[row * SIZE + column] = State.X;
            currentPlayer = Players.SECOND;
        } else {
            states[row * SIZE + column] = State.O;
            currentPlayer = Players.FIRST;
        }

        return true;
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
