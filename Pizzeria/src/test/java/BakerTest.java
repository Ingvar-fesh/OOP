import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BakerTest {
    private Random random;
    private List<Baker> bakers;  // for generating pizzas
    private NewQueue<Order> orderQueue;  // list of orders
    private NewQueue<Order> deliveryQueue;  // list of pizzas
    private int[] bakerCookingTime;  // cooking time for each baker
    private final int countBakers = 5;  // number of baker
    private final int maxCookingTime = 1000;  // maximum possible time between producing new pizzas
    private final int countOrders = 10;  // number of orders
    private final int maxQueueCapacity = 10;  // maximum capacity of the shared queue
    private final int maxDeliveryTime = 1000;  //maximum possible time of delivering order
    private final int SLEEP = 1000;  // time for testing

    private void setUp() {
        random = new Random();
        orderQueue = new NewQueue<>(0);
        deliveryQueue = new NewQueue<>(maxQueueCapacity);
        IntStream.range(0, countOrders).forEach(i -> orderQueue.add(new Order(i, random.nextInt(maxDeliveryTime))));
        bakerCookingTime = new int[countBakers];
        Arrays.setAll(bakerCookingTime, i -> random.nextInt(maxCookingTime ));
        bakers = Arrays.stream(bakerCookingTime).mapToObj(bakerCookTime -> new Baker(orderQueue, deliveryQueue, bakerCookTime)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    void bakerTest() {
        setUp();
        Assertions.assertFalse(orderQueue.isEmpty());
        Assertions.assertTrue(deliveryQueue.isEmpty());
        bakers.stream().map(Thread::new).forEach(Thread::start);
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ignored) {}
        bakers.forEach(Baker::stop);
        Assertions.assertTrue(orderQueue.isEmpty());
        Assertions.assertFalse(deliveryQueue.isEmpty());
    }
}