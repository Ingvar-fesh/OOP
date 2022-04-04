import java.util.Random;

public class Customer implements Runnable{
    private final NewQueue<Order> ordersQueue; // list of orders
    private boolean isRunning; // status flag
    private final Random random;
    private final int MAX_DELIVERY_TIME = 200; // maximum possible time of delivering order
    private final int MAX_SLEEP_TIME = 100; // maximum possible time between producing new orders

    Customer(NewQueue<Order> orders) {
        this.ordersQueue = orders;
        this.isRunning = true;
        this.random = new Random();
    }

    /**
     * Create new orders.
     */
    @Override
    public void run() {
        for (int i = 0; isRunning; ++i) {
            Order order = new Order(i, random.nextInt(this.MAX_DELIVERY_TIME));
            this.ordersQueue.add(order);
            order.printState();
            this.ordersQueue.notifyEmpty();
            try {
                Thread.sleep(this.random.nextInt(MAX_SLEEP_TIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops producing new orders.
     */
    public void stop() {
        this.isRunning = false;
    }
}
