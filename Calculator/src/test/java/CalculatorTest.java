import Calculator.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calc = new Calculator();
    @Test
    public void classicTest() throws Exception {
        assertEquals(0, calc.calculate("sin + - 1 2 1"));
        assertEquals(27, calc.calculate("+ * 3 4 15"));
        assertEquals(15 ,calc.calculate("+ cos - 11 11 + 13 1"));
    }

    @Test
    public void wrongExpression() {
        var e = assertThrows(Exception.class, () -> calc.calculate("sin1 + 1 2"));
        assertEquals("Wrong expression", e.getMessage());
    }

    @Test
    public void emptyStack() {
        var e = assertThrows(Exception.class, () -> calc.calculate("* - + 1 2 3"));
        assertEquals("Don't get number from stack", e.getMessage());
    }

    @Test
    public void manyArguments() {
        var e = assertThrows(Exception.class, () -> calc.calculate("* 3 4 1 5"));
        assertEquals("A lot of arguments in stack", e.getMessage());
    }
}