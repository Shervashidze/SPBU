package group144.shervashidze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/* Controller of javaFX App Calculator*/
public class Controller {
    /** private field calculator to support app*/
    private Calculator calculator = new Calculator();

    /** private field buttons from 0 to 9, so i can process all this buttons together*/
    private Button[] buttons;

    /** private boolean to check is there a new value or user still are writing the old one*/
    private boolean isNewValue = true;

    /** initializing method: init buttons, set buffer empty and main field as "0", set all auxiliary buttons disable*/
    public void initialize() {
        buttons = new Button[]{button0, button1, button2, button3, button4, button5, button6, button7, button8, button9};
        textField.textProperty().setValue("0");
        textFieldBuff.textProperty().setValue("");
        setDisable(true);
    }

    /**
     * press Number button.
     *
     * Number constructor.
     * if isNewValue - true, empty textField and start to create new number.
     * otherwise, continue to create number.
     * @param actionEvent to understand what button was pressed.
     */
    public void numberPress(ActionEvent actionEvent) {
        if (isNewValue) {
            textField.textProperty().setValue("0");
            isNewValue = false;
        }
        for (int i = 0; i < 10; i++) {
            if (actionEvent.getSource().equals(buttons[i])) {
                String temp = "";
                if (!textField.getText().equals("0")) {
                    temp = textField.getText();
                }

                temp += Integer.toString(i);
                textField.textProperty().setValue(temp);
            }
        }
        setDisable(false);
    }

    /**
     * press Addition button.
     * if (isNewValue) just switch current operation to add.
     * if calculator is clear, initializing it with current value and operation.
     * Adding in buffer current value and operation.
     * Setting isNewValue as true.
     * Writing in textField current result.
     */
    public void pressAdd() {
        if (isNewValue) {
            String temp = textFieldBuff.getText();
            if (!temp.equals("")) {
                temp = temp.substring(0, temp.length() - 2) + "+ ";
                textFieldBuff.textProperty().setValue(temp);
            }
            calculator.setOperation(Commands.ADDITION);
            return;
        }
        showBuffer(" + ");
        if (calculator.isClear()) {
            calculator.setFirst(textField.getText(), Commands.ADDITION);
        } else {
            calculator.calculate(Commands.ADDITION, textField.getText());
        }

        textField.textProperty().setValue(String.valueOf(calculator.getValue()));
        isNewValue = true;
    }

    /**
     * press Subtraction button.
     * if (isNewValue) just switch current operation to sub.
     * if calculator is clear, initializing it with current value and operation.
     * Adding in buffer current value and operation.
     * Setting isNewValue as true.
     * Writing in textField current result.
     */
    public void pressSub() {
        if (isNewValue) {
            String temp = textFieldBuff.getText();
            if (!temp.equals("")) {
                temp = temp.substring(0, temp.length() - 2) + "- ";
                textFieldBuff.textProperty().setValue(temp);
            }
            calculator.setOperation(Commands.SUBTRACTION);
            return;
        }
        showBuffer(" - ");
        if (calculator.isClear()) {
            calculator.setFirst(textField.getText(), Commands.SUBTRACTION);
        } else {
            calculator.calculate(Commands.SUBTRACTION, textField.getText());
        }

        textField.textProperty().setValue(String.valueOf(calculator.getValue()));
        isNewValue = true;
    }

    /**
     * press Multiplication button.
     * if (isNewValue) just switch current operation to mul.
     * if calculator is clear, initializing it with current value and operation.
     * Adding in buffer current value and operation.
     * Setting isNewValue as true.
     * Writing in textField current result.
     */
    public void pressMul() {
        if (isNewValue) {
            String temp = textFieldBuff.getText();
            if (!temp.equals("")) {
                temp = temp.substring(0, temp.length() - 2) + "* ";
                textFieldBuff.textProperty().setValue(temp);
            }
            calculator.setOperation(Commands.MULTIPLICATION);
            return;
        }
        showBuffer(" * ");
        if (calculator.isClear()) {
            calculator.setFirst(textField.getText(), Commands.MULTIPLICATION);
        } else {
            calculator.calculate(Commands.MULTIPLICATION, textField.getText());
        }

        textField.textProperty().setValue(String.valueOf(calculator.getValue()));
        isNewValue = true;
    }

    /**
     * press Division button.
     * if (isNewValue) just switch current operation to div.
     * if calculator is clear, initializing it with current value and operation.
     * Adding in buffer current value and operation.
     * Setting isNewValue as true.
     * Writing in textField current result.
     */
    public void pressDiv() {
        if (isNewValue) {
            String temp = textFieldBuff.getText();
            if (!temp.equals("")) {
                temp = temp.substring(0, temp.length() - 2) + "/ ";
                textFieldBuff.textProperty().setValue(temp);
            }
            calculator.setOperation(Commands.DIVISION);
            return;
        }

        showBuffer(" / ");

        if (calculator.isClear()) {
            calculator.setFirst(textField.getText(), Commands.DIVISION);
        } else {
            calculator.calculate(Commands.DIVISION, textField.getText());
        }

        textField.textProperty().setValue(String.valueOf(calculator.getValue()));
        isNewValue = true;
    }

    /**
     * press Answer button.
     *
     * disable auxiliary buttons.
     * empty buffer.
     * if isNewValue do nothing.
     *
     * set isNewValue as true.
     * if calculator is clear do nothing.
     * else calculate with current value and write it in textField.
     * empty calculator.
     */
    public void pressAns() {
        setDisable(true);
        textFieldBuff.textProperty().setValue("");
        if (isNewValue) {
            return;
        }

        isNewValue = true;

        if (calculator.isClear()) {
            return;
        }

        calculator.calculate(Commands.ADDITION, textField.getText());
        textField.textProperty().setValue(String .valueOf(calculator.getValue()));
        calculator = new Calculator();
    }

    /**
     * Press Clear(CE) button.
     * To write a new current value.
     * Sets textField ass "0".
     */
    public void pressClear() {
        textField.textProperty().setValue("0");
    }

    /**
     * Press ClearAll(C) button.
     * To start calculations from the beginning.
     * set textField as 0, empty buffer.
     * Create new calculator and disable all auxiliary buttons.
     */
    public void pressClearAll() {
        textField.textProperty().setValue("0");
        textFieldBuff.textProperty().setValue("");
        calculator = new Calculator();
        setDisable(true);
    }

    /**
     * Updating buffer with current textField value and pressed operation.
     */
    private void showBuffer(String string) {
        textFieldBuff.textProperty().setValue(textFieldBuff.getText() + textField.getText() + string);
    }

    /**
     * set disable.
     * @param how - set all auxiliary buttons disabled or not according to boolean how(disabled if true,
     * false otherwise.
     */
    private void setDisable(boolean how) {
        buttonAdd.setDisable(how);
        buttonSub.setDisable(how);
        buttonMul.setDisable(how);
        buttonDiv.setDisable(how);
        buttonAns.setDisable(how);
        buttonClear.setDisable(how);
        buttonClearAll.setDisable(how);
    }

    /** private number button*/
    @FXML
    private Button button0;

    /** private number button*/
    @FXML
    private Button button1;

    /** private number button*/
    @FXML
    private Button button2;

    /** private number button*/
    @FXML
    private Button button3;

    /** private number button*/
    @FXML
    private Button button4;

    /** private number button*/
    @FXML
    private Button button5;

    /** private number button*/
    @FXML
    private Button button6;

    /** private number button*/
    @FXML
    private Button button7;

    /** private number button*/
    @FXML
    private Button button8;

    /** private number button*/
    @FXML
    private Button button9;

    /** private addition button*/
    @FXML
    private Button buttonAdd;

    /** private subtraction button*/
    @FXML
    private Button buttonSub;

    /** private multiplication button*/
    @FXML
    private Button buttonMul;

    /** private division button*/
    @FXML
    private Button buttonDiv;

    /** private answer button*/
    @FXML
    private Button buttonAns;

    /** private clear(CE) button*/
    @FXML
    private Button buttonClear;

    /** private clearAll(C) button*/
    @FXML
    private Button buttonClearAll;

    /** private text Field to write results*/
    @FXML
    private TextField textField;

    /** private text Field - buffer to write previous calculations*/
    @FXML
    private TextField textFieldBuff;
}
