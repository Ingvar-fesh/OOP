package feshchenko;

import java.util.*;

public class generator{
    private static boolean[] matrix = new boolean[10000000];
    private static List<Integer> primes;
    private int count;
    private Random random;
    private List<Integer> list;

    static {
        Arrays.fill(matrix, true);
        for(int i = 2; i< matrix.length / 2;) {
            for (int j = 2; j * i < matrix.length; j++)
                matrix[i * j] = false;
            for(int k = i + 1; k < matrix.length; k++)
                if(matrix[k]){ i = k; break; }
        }
        matrix[0]=false; matrix[1]=false;

        primes = new LinkedList<>();
        for(int i = 0; i< matrix.length; i++)
            if(matrix[i]) primes.add(i);
    }

    generator(int count){ this.count = count; this.random = new Random(count); list = setList();}

    private List<Integer> setList(){
        List<Integer> res = new LinkedList<>();
        ArrayList<Integer> prost1 = new ArrayList<>(primes);
        for(int i = 0; i< count; i++) res.add(prost1.get(random.nextInt(prost1.size())));
        return res;
    }

    public List<Integer> getList(){ return list; }
}
