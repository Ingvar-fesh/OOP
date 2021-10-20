import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void ClassicTest() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(15);
        stack.push(7);
        assertEquals(3, stack.count());
        assertEquals(7, stack.pop());
        assertEquals(15, stack.pop());
        assertEquals(2, stack.pop());
    }

    @Test
    public void PushStackTest() throws Exception{
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(15);
        stack.push(7);
        Stack<Integer> array = new Stack<>();
        array.push(3);
        array.push(12);
        stack.pushStack(array);
        assertEquals(5, stack.count());
        assertEquals(12, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(7, stack.pop());
    }

    @Test
    public void PopStackTest() throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(15);
        stack.push(7);
        stack.push(3);
        stack.push(12);
        Stack<Integer> compare;
        compare = stack.popStack(3);
        assertEquals(3, compare.count());
        Integer a = compare.pop();
        assertEquals(12, a);
    }

    @Test
    public void PopStackExeption() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(15);
        var e = assertThrows(IllegalArgumentException.class, () -> stack.popStack(124));
        assertEquals("size stack smaller than size popStack", e.getMessage());
    }

    @Test
    public void PopExeption() {
        Stack<Integer> stack = new Stack<>();
        Exception e = assertThrows(Exception.class, () -> stack.pop());
        assertEquals("empty stack", e.getMessage());
    }

    @Test
    public void StringTest() throws Exception{
        Stack<String> stack = new Stack<>();
        stack.push("Hello");
        stack.push("world");
        stack.push("!");
        assertEquals(3, stack.count());
        assertEquals("!", stack.pop());
        assertEquals("world", stack.pop());
        assertEquals("Hello", stack.pop());
    }
}
