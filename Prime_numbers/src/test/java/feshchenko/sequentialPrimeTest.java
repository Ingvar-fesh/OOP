package feshchenko;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class sequentialPrimeTest {
    @Test
    public void allNotPrimeNumbers() {
        List<Integer> array = List.of(new Integer[]{4, 8, 15, 14, 18});
        sequentialPrime seq = new sequentialPrime(array);
        assertEquals(false, seq.checkArrayPrime());
    }

    @Test
    public void someNumbersNotPrime() {
        List<Integer> array = List.of(new Integer[]{8, 8, 7, 13, 17, 26, 5});
        sequentialPrime seq = new sequentialPrime(array);
        assertEquals(false, seq.checkArrayPrime());
    }

    @Test
    public void TestOnlyPrimeNumbers(){
        List<Integer> array = List.of(new Integer[]{3, 5, 7, 13, 17});
        sequentialPrime seq = new sequentialPrime(array);
        assertEquals(true, seq.checkArrayPrime());
    }

    @Test
    public void checkTime() {
        generator seq = new generator(100000);
        List<Integer> array = seq.getList();
        sequentialPrime sequential = new sequentialPrime(array);
        assertEquals(true, sequential.checkArrayPrime());
        System.out.format("%d\n", sequential.getTime());
    }
}