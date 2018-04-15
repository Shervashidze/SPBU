package group144.shervashidze;

public class Calculator {
    public int calculate(String expression) {
        String[] words = expression.split(" ");
        Stack<Integer> stack = new ListStack<Integer>();
        for (String word : words) {
            if (isNumber(word)) {
                stack.push(Integer.parseInt(word));
            } else {
                int secondValue = stack.pop();
                int firstValue = stack.pop();
                stack.push(count(firstValue, secondValue, word));
            }
        }

        return stack.pop();
    }

    private Integer count(int firstValue, int secondValue, String operator) {
        switch (operator) {
            case "+":
                return firstValue + secondValue;
            case "-":
                return firstValue - secondValue;
            case "*":
                return firstValue * secondValue;
            case "/":
                return firstValue / secondValue;
        }

        return -42;
    }

    private boolean isNumber(String string) {
        for (int i = 1; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }

        return Character.isDigit(string.charAt(0)) || (string.charAt(0) == '-' && string.length() > 1);
    }
}
