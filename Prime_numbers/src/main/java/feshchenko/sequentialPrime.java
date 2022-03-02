package feshchenko;
import java.util.*;

public class sequentialPrime {
    private final List<Integer> array;
    private long start;
    private long finish;

    public sequentialPrime(List<Integer> array) {
        this.array = array;
    }

    public boolean checkArrayPrime() {
        start = System.currentTimeMillis();
        for (int number : this.array) {
            if (!checkNumber.isPrime(number)) {
                finish = System.currentTimeMillis();
                return false;
            }
        }
        finish = System.currentTimeMillis();
        return true;
    }

    public long getTime() {
        return finish - start;
    }
}
