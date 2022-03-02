package feshchenko;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class parallelCheckTest {
    @Test
    public void allNotPrimeNumbers() {
        List<Integer> array = List.of(new Integer[]{4, 8, 15, 14, 18});
        parallelCheck parallel = new parallelCheck(3, array);
        assertEquals(false, parallel.checkParallelArray());
    }

    @Test
    public void someNumbersNotPrime() {
        List<Integer> array = List.of(new Integer[]{8, 8, 7, 13, 17, 26, 5});
        parallelCheck parallel = new parallelCheck(5, array);
        assertEquals(false, parallel.checkParallelArray());
    }

    @Test
    public void TestOnlyPrimeNumbers(){
        List<Integer> array = List.of(new Integer[]{3, 5, 7, 13, 17});
        parallelCheck parallel = new parallelCheck(8, array);
        assertEquals(true, parallel.checkParallelArray());
    }

    @Test
    public void checkTime() {
        generator seq = new generator(100000);
        List<Integer> array = seq.getList();
        parallelCheck parallel = new parallelCheck(2, array);
        parallel.checkParallelArray();
        System.out.format("%d\n", parallel.getTime());
    }
}