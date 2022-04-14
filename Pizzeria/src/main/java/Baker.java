public class Baker implements Runnable {
    private final NewQueue<Order> ordersQueue;
    private final NewQueue<Order> deliveryQueue;
    private boolean isRunning;
    private final int cookingTime;

    Baker(NewQueue<Order> ordersQueue, NewQueue<Order> deliveryQueue, int cookingTime) {
        this.ordersQueue = ordersQueue;
        this.deliveryQueue = deliveryQueue;
        this.cookingTime = cookingTime;
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

            try {
                Thread.sleep(this.cookingTime);
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
            this.deliveryQueue.notifyNotEmpty();
        }
    }

    /**
     * Stops producing new pizzas.
     */
    public void stop() {
        this.isRunning = false;
        this.ordersQueue.notifyNotEmpty();
        this.deliveryQueue.notifyNotFull();
    }
}
