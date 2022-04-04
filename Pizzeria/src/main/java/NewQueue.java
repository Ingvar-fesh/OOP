import java.util.LinkedList;
import java.util.Queue;

public class NewQueue<T> {
    private final Queue<T> queue;
    private final int capacityQueue;
    private final Object EMPTY = new Object(); // for locking on empty queue
    private final Object FULL = new Object(); // for locking on full queue

    NewQueue(int capacityQueue) {
        this.queue = new LinkedList<>();
        this.capacityQueue = capacityQueue;
    }

    /**
     * Check if queue is empty
     * @return true if queue contains no elements or false otherwise.
     */
    public boolean isEmpty() {
        synchronized (this.queue) {
            return this.queue.isEmpty();
        }
    }

    /**
     * Checks if queue is full.
     * @return true if queue contains maxSize elements or false otherwise.
     */
    public boolean isFull() {
        synchronized (this.queue) {
            return this.queue.size() >= this.capacityQueue;
        }
    }

    /**
     * Add new element - data in queue
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
     */
    public void waitEmpty() throws InterruptedException {
        synchronized (this.EMPTY) {
            this.EMPTY.wait();
        }
    }

    /**
     * Locks when queue is full
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
