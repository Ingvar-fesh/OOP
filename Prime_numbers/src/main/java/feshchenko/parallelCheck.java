package feshchenko;
import java.util.*;

public class parallelCheck {
    private final List<Integer> array;
    private final int count_treads;
    private int simple_numbers;
    private long start;
    private long finish;

    parallelCheck(int count_treads, List<Integer> array) {
        this.count_treads = count_treads;
        this.array = array;
        this.simple_numbers = 0;
    }

    public class myTread extends Thread {
        private final List<Integer> positions;
        myTread(List<Integer> positions) {
            this.positions = positions;
        }

        @Override
        public void run() {
            for (Integer b: positions) {
                if (checkNumber.isPrime(b)) {
                    simple_numbers += 1;
                }
            }
        }
    }


    public boolean checkParallelArray() {
        start = System.currentTimeMillis();
        myTread[] threads = new myTread[count_treads];
        for (int i = 0;i < count_treads; ++i) {
            threads[i] = new myTread(array.subList(i * array.size() / count_treads, (i + 1) * array.size() / count_treads));
            threads[i].start();
        }
        for (int i = 0; i < count_treads; i++) {
            try{
                threads[i].join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        finish = System.currentTimeMillis();
        return simple_numbers == array.size();
    }

    public long getTime() {
        return finish - start;
    }
}
