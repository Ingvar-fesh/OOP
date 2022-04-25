import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerTest {
    private Customer customers;  // for generating orders
    private NewQueue<Order> orderQueue;  // list of orders
    private final static int SLEEP = 1000;  // time for testing


    private void setUp() {
        orderQueue = new NewQueue<>(0);
        customers = new Customer(orderQueue);
    }

    @Test
    void customers_test() {
        setUp();
        Assertions.assertTrue(orderQueue.isEmpty());
        Thread customersThread = new Thread(customers);
        customersThread.start();
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ignored) {}
        customers.stop();
        Assertions.assertFalse(orderQueue.isEmpty());
    }
}