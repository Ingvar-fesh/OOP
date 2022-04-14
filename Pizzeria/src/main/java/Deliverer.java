import java.util.*;

public class Deliverer implements Runnable{
    private final NewQueue<Order> deliveryQueue; // pizzas list
    private final int maxBagCapacity; // maximum capacity of bag
    private boolean isRunning; // status flag
    private final Random random;
    private int MAX_SLEEP_TIME = 300; // maximum possible time between delivering pizzas
    private final Object BlockingDeliverers; // for blocking deliverers

    Deliverer(NewQueue<Order> deliveryQueue, int maxBagCapacity, Object BlockingDeliverers) {
        this.deliveryQueue = deliveryQueue;
        this.maxBagCapacity = maxBagCapacity;
        this.random = new Random();
        this.BlockingDeliverers = BlockingDeliverers;
        this.isRunning = true;
    }

    /**
     * Deliver pizzas.
     */
    @Override
    public void run() {
        List<Order> orders = new ArrayList<>(this.maxBagCapacity);
        while(this.isRunning) {
            synchronized (this.BlockingDeliverers) {
                int countPizzasInBag = 0;
                while (!this.deliveryQueue.isEmpty()) {
                    if (countPizzasInBag < this.maxBagCapacity) {
                        Order order = this.deliveryQueue.remove();
                        orders.add(order);
                        ++countPizzasInBag;
                    }
                    else break;
                }
            }

            if(!this.isRunning) {
                break;
            }

            for(Order order : orders) {
                order.updateState();
                order.printState();
                try {
                    Thread.sleep(order.getDeliveryTime());
                } catch (InterruptedException ignored) {}
                order.updateState();
                order.printState();
            }
            orders.clear();

            try {
                Thread.sleep(this.random.nextInt(this.MAX_SLEEP_TIME));
            } catch (InterruptedException ignored) {}
        }
    }

    /**
     * Stops to deliver pizzas.
     */
    public void stop() {
        this.isRunning = false;
        this.deliveryQueue.notifyNotFull();
    }
}
