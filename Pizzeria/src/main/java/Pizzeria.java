import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pizzeria {
    private final Customer customers; // generating orders
    private final List<Baker> bakers; // generating pizzas
    private final List<Deliverer> deliverers; // generating delivering pizzas
    private final NewQueue<Order> ordersQueue; // list of orders
    private final NewQueue<Order> deliveryQueue; // list of pizzas
    private int MAX_SLEEP_TIME = 1000; // time for sleep before stopping
    private final Object BlockingDeliverers = new Object(); // for blocking deliverers

    Pizzeria(Configuration pizzeriaConfiguration) {
        this.ordersQueue = new NewQueue<>(0);
        this.deliveryQueue = new NewQueue<>(pizzeriaConfiguration.getMaxQueueCapacity());
        this.customers = new Customer(this.ordersQueue);
        this.bakers = Arrays.stream(pizzeriaConfiguration.getBakersCookTime()).mapToObj(bakersCookTime -> new Baker(this.ordersQueue, this.deliveryQueue, bakersCookTime)).collect(Collectors.toCollection(ArrayList::new));
        this.deliverers = Arrays.stream(pizzeriaConfiguration.getDeliverersCapacity()).mapToObj(delivererCapacity -> new Deliverer(this.deliveryQueue, delivererCapacity, this.BlockingDeliverers)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Starts all threads.
     */
    public void start() {
        Thread customersThread = new Thread(this.customers);
        customersThread.start();
        this.bakers.stream().map(Thread::new).forEach(Thread::start);
        this.deliverers.stream().map(Thread::new).forEach(Thread::start);
    }

    /**
     * Stops all threads.
     */
    public void stop() {
        this.customers.stop();
        try {
            Thread.sleep(this.MAX_SLEEP_TIME);
        } catch (InterruptedException ignored) {}
        this.bakers.forEach(Baker::stop);
        try {
            Thread.sleep(this.MAX_SLEEP_TIME);
        } catch (InterruptedException ignored) {}
        this.deliverers.forEach(Deliverer::stop);
        try {
            Thread.sleep(this.MAX_SLEEP_TIME);
        } catch (InterruptedException ignored) {}
    }
}
