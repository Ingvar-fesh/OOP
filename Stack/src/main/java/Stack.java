import java.util.Arrays;

public class Stack<T> {
    private int lenght;
    private int capacity;
    private T[] elements;


    Stack(){
        lenght = 0;
        capacity = 1;
        elements = (T[]) new Object[capacity];
    }

    public void push(T elem) {
        if (lenght == capacity) {
            capacity *= 2;
            elements = Arrays.copyOf(elements, capacity);
        }
        elements[lenght] = elem;
        ++lenght;
    }

    public T pop() throws Exception {
        if (lenght == 0)
            throw new Exception("empty stack");
        T elem = elements[--lenght];
        return elem;
    }

    public int count() {
        return lenght;
    }

    public void pushStack( Stack<T> arr) throws Exception {
        int lenArray = arr.count();
        Stack<T> t = new Stack<>();
        for (int i = 0;i < lenArray; ++i)
            t.push(arr.pop());
        for (int i = 0;i < lenArray; ++i)
            push(t.pop());
    }

    public Stack<T> popStack(int count) {
        if (lenght - count < 0)
            throw new IllegalArgumentException("size stack smaller than size popStack");
        Stack<T> newStack = new Stack<>();
        for (int i = lenght - count; i < lenght; ++i)
            newStack.push(elements[i]);
        lenght -= count;
        return newStack;
    }
}