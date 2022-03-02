package feshchenko;
import java.util.*;

public class streamCheck {
    private List<Integer> array;
    private long start;
    private long finish;

    streamCheck(List<Integer> array) {
        this.array = array;
    }

    public boolean checkStreamArray() {
        start = System.currentTimeMillis();
        long t = array.parallelStream().filter(x -> checkNumber.isPrime(x)).count();
        finish = System.currentTimeMillis();
        return array.size() == t;
    }

    public long getTime() {
        return finish - start;
    }
}
