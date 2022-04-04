public class Baker implements Runnable {
    private final NewQueue<Order> orders;
    private final NewQueue<Order> pizzas;
    private boolean isRunning;
    private final int cookingTime;

    Baker(NewQueue<Order> orders, NewQueue<Order> pizzas, int cookingTime) {
        this.orders = orders;
        this.pizzas = pizzas;
        this.cookingTime = cookingTime;
        this.isRunning = true;
    }

    /**
     * Produces new pizzas.
     */
    @Override
    public void run() {
        while(this.isRunning) {
            while (this.orders.isEmpty()) {
                try {
                    this.orders.waitEmpty();
                } catch (InterruptedException e) {
                    if(!this.isRunning) {
                        break;
                    }
                }
            }

            if(!this.isRunning) {
                break;
            }
            Order order = this.orders.remove();
            if(order == null) {
                continue;
            }
            order.updateState();
            order.printState();
            this.orders.notifyFull();

            try {
                Thread.sleep(this.cookingTime);
            } catch (InterruptedException ignored) {}

            while(this.pizzas.isFull()) {
                try {
                    this.pizzas.waitFull();
                } catch (InterruptedException e) {
                    if(!this.isRunning) {
                        break;
                    }
                }
            }

            if(!this.isRunning) {
                break;
            }

            this.pizzas.add(order);
            order.updateState();
            order.printState();
            this.pizzas.notifyEmpty();
        }
    }

    /**
     * Stops producing new pizzas.
     */
    public void stop() {
        this.isRunning = false;
        this.orders.notifyEmpty();
        this.pizzas.notifyFull();
    }
}
