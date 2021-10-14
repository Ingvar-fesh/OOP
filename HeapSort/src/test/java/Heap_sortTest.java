import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Heap_sortTest {

    @Test
    /**
     * This test checks how method "heap_sort" sorts usual array
     */
    void usual_test() {
        int arr[] = {8, 4, 2, 5, 0, -1};
        int arr_test[] = {-1, 0, 2, 4, 5, 8};
        Heap_sort p = new Heap_sort();
        p.heap_sort(arr);
        assertArrayEquals(arr_test, arr);
    }

    @Test
    /**
     * This test checks how method "heap_sort" sorts array which contains all negative numbers
     */
    void negative_test() {
        int arr_2[] = {-6, 0, -4, -18, -65};
        int arr_2_test[] = {-65, -18, -6, -4, 0};
        Heap_sort l = new Heap_sort();
        l.heap_sort(arr_2);
        assertArrayEquals(arr_2_test, arr_2);
    }

    @Test
    /**
     * This test checks how method "heap_sort" sorts array which contains same numbers
     */
    void same_test() {
        int arr_3[] = {1, 0, 1, 7, 3, 1, 15};
        int arr_3_test[] = {0, 1, 1, 1, 3, 7, 15};
        Heap_sort t = new Heap_sort();
        t.heap_sort(arr_3);
        assertArrayEquals(arr_3_test, arr_3);
    }

    @Test
    /**
     * This test checks how method "heap_sort" sorts empty array
     */
    void empty_test() {
        int arr_3[] = {};
        int arr_3_test[] = {};
        Heap_sort t = new Heap_sort();
        t.heap_sort(arr_3);
        assertArrayEquals(arr_3_test, arr_3);
    }
}