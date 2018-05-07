package group144.shervashidze;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private Spinner<Integer> firstNumber;

    @FXML
    private ChoiceBox<String> operations;

    @FXML
    private Spinner<Integer> secondNumber;

    @FXML
    private TextField answer;

    /** Initialization method
     *  Setting 4 choices in operations ('+', '-', '*', '/')
     *  Setting launching choice as '+'.
     *
     *  Setting auto calculation after switching value of any choice box.
     *
     *  Setting range of possible int values as (-500;500).
     *  Setting launching value of answer as '0'.
     */
    public void initialize() {
        operations.getItems().addAll("+", "-", "*", "/");
        operations.valueProperty().setValue("+");

        operations.valueProperty().addListener((observable, oldValue, newValue) -> calculate());

        firstNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-500, 500, 0, 1));
        secondNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-500, 500, 0, 1));

        answer.textProperty().setValue("0");

        firstNumber.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
        secondNumber.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
    }

    /**
     * Calculate method.
     *
     * Print "Division by 0" in answer field if it is fits.
     * Print correct answer as double number in answer field.
     *
     */
    private void calculate() {
        double result = 0;
        int first = firstNumber.getValue();
        int second = secondNumber.getValue();

        if (second == 0 && operations.getValue().equals("/")) {
            answer.textProperty().setValue("Division by 0");
            return;
        }

        switch (operations.getValue()) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = (double) first / second;
                break;
        }

        answer.textProperty().setValue(Double.toString(result));
    }
}
