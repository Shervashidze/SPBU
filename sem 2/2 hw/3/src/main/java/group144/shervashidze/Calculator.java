package group144.shervashidze;

public class Calculator {
    private Stack<Integer> stack;

    public Calculator(Stack<Integer> newStack) {
        stack = newStack;
    }

    public int calculate(String expression) throws WrongExpressionException {
        String[] words = expression.split(" ");
        for (String word : words) {
            if (isNumber(word)) {
                stack.push(Integer.parseInt(word));
            } else {
                int secondValue;
                try {
                    secondValue = stack.pop();
                } catch (EmptyStackException e) {
                    throw new WrongExpressionException();
                }

                int firstValue;
                try {
                    firstValue = stack.pop();
                } catch (EmptyStackException e) {
                    throw new WrongExpressionException();
                }

                stack.push(count(firstValue, secondValue, word));
            }
        }

        int answer = 0;
        try {
            answer = stack.pop();
        } catch (EmptyStackException e) {
            throw new WrongExpressionException();
        }
        return answer;
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
        return string.matches("[-]?[0-9]+");
    }
}
