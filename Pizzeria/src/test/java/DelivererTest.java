import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class DelivererTest {
    private Random random;
    private List<Deliverer> deliverers;  // for delivering pizzas
    private NewQueue<Order> deliveryQueue;  // list of pizzas
    private int[] deliverersCapacity;  // bag capacity for each deliverer
    private final int countDeliverers = 10;  // number of deliverers
    private final int maxCapacityBag = 5;  // maximum possible capacity of deliverers bag
    private final int countOrders = 20;  // number of orders
    private final int maxQueueSize = 20;  // maximum capacity of the shared queue
    private final int maxDeliveringTime = 500;  //maximum possible time of delivering order
    private final int SLEEP_TIME = 1000;  // time for testing


    private void setUp() {
        random = new Random();
        deliveryQueue = new NewQueue<>(maxQueueSize);
        IntStream.range(0, countOrders ).forEach(i -> deliveryQueue.add(new Order(i, random.nextInt(maxDeliveringTime))));
        deliverersCapacity = new int[countDeliverers];
        Arrays.setAll(deliverersCapacity, i -> random.nextInt(maxCapacityBag));
        deliverers = Arrays.stream(deliverersCapacity).mapToObj(delivererCapacity -> new Deliverer(deliveryQueue, delivererCapacity)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    void deliverers_test() {
        setUp();
        Assertions.assertFalse(deliveryQueue.isEmpty());
        deliverers.stream().map(Thread::new).forEach(Thread::start);
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ignored) {}
        deliverers.forEach(Deliverer::stop);
        Assertions.assertTrue(deliveryQueue.isEmpty());
    }
}