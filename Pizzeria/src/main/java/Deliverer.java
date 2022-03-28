import java.util.*;

public class Deliverer implements Runnable{
    private SharedQueue<Order> deliveryQueue; // pizzas list
    private int maxBagCapacity; // maximum capacity of bag
    private boolean isRunning; // status flag
    private Random random;
    private int MAX_SLEEP_TIME = 300; // maximum possible time between delivering pizzas

    Deliverer(SharedQueue<Order> deliveryQueue, int maxBagCapacity) {
        this.deliveryQueue = deliveryQueue;
        this.maxBagCapacity = maxBagCapacity;
        this.random = new Random();
        this.isRunning = true;
    }

    /**
     * Deliver pizzas.
     */
    @Override
    public void run() {
        while(this.isRunning) {
            List<Order> orders = new ArrayList<>(this.maxBagCapacity);
            for(int i = 0; i < this.maxBagCapacity; i++) {
                while (this.deliveryQueue.isEmpty()) {
                    try {
                        this.deliveryQueue.waitEmpty();
                    } catch (InterruptedException e) {
                        if(!this.isRunning) {
                            break;
                        }
                    }
                }
                if (!this.isRunning) {
                    break;
                }
                Order order = this.deliveryQueue.remove();
                if(order == null) {
                    --i;
                    continue;
                }
                orders.add(order);
                this.deliveryQueue.notifyFull();
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
        this.deliveryQueue.notifyEmpty();
    }
}
