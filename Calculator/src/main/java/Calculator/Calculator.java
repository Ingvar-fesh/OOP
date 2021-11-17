package Calculator;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Predicate;

public class Calculator {

    /**
     * Complite operations and result adds in stack
     * @param numberArg - number of arguments
     * @param stack
     * @throws Exception
     */
    private void operation(int numberArg, DoubleBinaryOperator predicate, Stack<Double> stack) throws Exception{
        if (numberArg == 1) {
            double x;
            try {
                x = stack.pop();
            }
            catch (EmptyStackException e) {
                throw new Exception("Don't get number from stack");
            }
            stack.push(predicate.applyAsDouble(x, 1));
        }
        if (numberArg == 2) {
            double x;
            double y;
            try {
                x = stack.pop();
                y = stack.pop();
            }
            catch (EmptyStackException e) {
                throw new Exception("Don't get number from stack");
            }
            stack.push(predicate.applyAsDouble(x, y));
        }
    }

    /**
     * Parse string prefix expression
     * @param expression
     * @return - result prefix expression
     * @throws Exception
     */
    public double calculate(String expression) throws Exception {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");
        for (int i = tokens.length - 1; i >= 0; --i) {
            try {
                stack.push(Double.parseDouble(tokens[i]));
            }
            catch (NumberFormatException e) {
                switch (tokens[i]) {
                    case "+" -> operation(2, (x, y) -> x + y, stack);
                    case "-" -> operation(2, (x, y) -> x - y, stack);
                    case "*" -> operation(2, (x, y) -> x * y, stack);
                    case "/" -> operation(2, (x, y) -> x / y, stack);
                    case "pow" -> operation(2, (x, y) -> Math.pow(x, y), stack);
                    case "sqrt" -> operation(1, (x, y) -> Math.sqrt(x), stack);
                    case "log" -> operation(1, (x, y) -> Math.log(x), stack);
                    case "sin" -> operation(1, (x, y) -> Math.sin(x), stack);
                    case "cos" -> operation(1, (x, y) -> Math.cos(x), stack);
                    default -> throw new Exception("Wrong expression");
                }
            }
        }
        double answer = stack.pop();
        if (!stack.isEmpty())
            throw new Exception("A lot of arguments in stack");
        else return answer;
    }
}

