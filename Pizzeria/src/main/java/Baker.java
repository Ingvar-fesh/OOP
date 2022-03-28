public class Baker implements Runnable {
    private final SharedQueue<Order> ordersQueue; // list of orders
    private final SharedQueue<Order> deliveryQueue; // list of pizzas
    private boolean isRunning; // status flag
    private final int timeCook; //

    Baker(SharedQueue<Order> ordersQueue, SharedQueue<Order> deliveryQueue, int timeCook) {
        this.ordersQueue = ordersQueue;
        this.deliveryQueue = deliveryQueue;
        this.timeCook = timeCook;
        this.isRunning = true;
    }

    /**
     * Produces new pizzas.
     */
    @Override
    public void run() {
        while(this.isRunning) {
            while (this.ordersQueue.isEmpty()) {
                try {
                    this.ordersQueue.waitEmpty();
                } catch (InterruptedException e) {
                    if(!this.isRunning) {
                        break;
                    }
                }
            }
            if(!this.isRunning) {
                break;
            }
            Order order = this.ordersQueue.remove();
            if(order == null) {
                continue;
            }
            order.updateState();
            order.printState();
            this.ordersQueue.notifyFull();
            try {
                Thread.sleep(this.timeCook);
            } catch (InterruptedException ignored) {}
            while(this.deliveryQueue.isFull()) {
                try {
                    this.deliveryQueue.waitFull();
                } catch (InterruptedException e) {
                    if(!this.isRunning) {
                        break;
                    }
                }
            }
            if(!this.isRunning) {
                break;
            }
            this.deliveryQueue.add(order);
            order.updateState();
            order.printState();
            this.deliveryQueue.notifyEmpty();
        }
    }

    /**
     * Stops producing new pizzas.
     */
    public void stop() {
        this.isRunning = false;
        this.ordersQueue.notifyEmpty();
        this.deliveryQueue.notifyFull();
    }
}
