import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue<T> {
    private final Queue<T> queue;
    private final int capacityQueue; // maximum possible capacity of the shared queue
    private final Object EMPTY = new Object(); // for locking on empty queue
    private final Object FULL = new Object(); // for locking on full queue

    SharedQueue(int capacityQueue) {
        this.queue = new LinkedList<>();
        this.capacityQueue = capacityQueue;
    }

    /**
     * Check if queue is empty
     * @return <code>true</code> if queue contains no elements or <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        synchronized (this.queue) {
            return this.queue.isEmpty();
        }
    }

    /**
     * Checks if queue is full.
     * @return <code>true</code> if queue contains <code>maxSize</code> elements or <code>false</code> otherwise.
     */
    public boolean isFull() {
        synchronized (this.queue) {
            return this.queue.size() >= this.capacityQueue;
        }
    }

    /**
     * Add new element - data in queue
     * @param data
     */
    public void add(T data) {
        synchronized (this.queue) {
            this.queue.add(data);
        }
    }

    /**
     * Removes data from queue.
     * @return Removed value.
     */
    public T remove() {
        synchronized (this.queue) {
            return this.queue.poll();
        }
    }

    /**
     * Locks when queue is empty.
     * @throws InterruptedException If waiting was interrupted unexpectedly.
     */
    public void waitEmpty() throws InterruptedException {
        synchronized (this.EMPTY) {
            this.EMPTY.wait();
        }
    }

    /**
     * Locks when queue is full
     * @throws InterruptedException If waiting was interrupted unexpectedly.
     */
    public void waitFull() throws InterruptedException {
        synchronized (this.FULL) {
            this.FULL.wait();
        }
    }

    /**
     * Releases lock when queue is not empty.
     */
    public void notifyEmpty() {
        synchronized(this.EMPTY) {
            this.EMPTY.notifyAll();
        }
    }

    /**
     * Releases lock when queue is not full.
     */
    public void notifyFull() {
        synchronized(this.FULL) {
            this.FULL.notifyAll();
        }
    }
}
