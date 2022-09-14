import Couriers.Courier;
import JSON.PizzeriaConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Orders.Order;

import java.util.concurrent.LinkedBlockingQueue;

public class CourierTest {
    @Test
    public void deliverSomePizzasTest() throws InterruptedException {
        LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<>();
        orders.add(new Order(1, 1));
        orders.add(new Order(2, 1));
        orders.add(new Order(3, 1));

        var deliveryConfiguration = new PizzeriaConfiguration.CouriersConfiguration();
        deliveryConfiguration.setCount(1);
        deliveryConfiguration.setBackpackCap(new Integer[]{1});

        Assertions.assertFalse(orders.isEmpty());
        var courier = new Courier(orders, 2);
        courier.start();
        Thread.sleep(20);
        courier.stop();
        Assertions.assertTrue(orders.isEmpty());
    }
}
