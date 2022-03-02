package feshchenko;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class streamCheckTest {
    @Test
    public void allNotPrimeNumbers() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(4, 8, 15, 14, 18));
        streamCheck seq = new streamCheck(array);
        assertEquals(false, seq.checkStreamArray());
    }

    @Test
    public void someNumbersNotPrime() {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(8, 8, 7, 13, 17, 26, 5));
        streamCheck seq = new streamCheck(array);
        assertEquals(false, seq.checkStreamArray());
    }

    @Test
    public void TestOnlyPrimeNumbers(){
        List<Integer> array = List.of(new Integer[]{3, 5, 7, 13, 17});
        streamCheck stream = new streamCheck(array);
        assertEquals(true, stream.checkStreamArray());
    }


    @Test
    public void checkTime() {
        generator seq = new generator(1000000);
        List<Integer> array = seq.getList();
        streamCheck stream = new streamCheck(array);
        stream.checkStreamArray();
        System.out.format("%d\n", stream.getTime());
    }
}