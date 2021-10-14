public class Heap_sort {

    /**
     * This method sort array
     * @param arr - unsorted array
     */
    public void heap_sort(int arr[]){
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; --i)
            siftdown(arr, n, i);
        for (int i = n - 1; i >= 0; --i)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            siftdown(arr, i, 0);
        }
    }

    /**
     * Sifting through the heap - forming the heap
     * @param arr - current array
     * @param n - number elements of array
     * @param root - current value which compare with yours sons
     */
    void siftdown(int arr[], int n, int root) {
        int max = root;
        int l = 2 * root + 1;
        int r = 2 * root + 2;

        if (l < n && arr[l] > arr[max])
            max = l;
        if (r < n && arr[r] > arr[max])
            max = r;
        if (max != root) {
            int temp = arr[root];
            arr[root] = arr[max];
            arr[max] = temp;

            siftdown(arr, n, max);
        }
    }
}
