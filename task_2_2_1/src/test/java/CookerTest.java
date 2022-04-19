import Cookers.Cooker;
import Orders.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Orders.Order;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class CookerTest {
    @Test
    public void CookTest() throws IOException, InterruptedException {
        LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Order> warehouse = new LinkedBlockingQueue<>(10);

        orders.add(new Order(1,1));
        orders.add(new Order(2,1));

        Assertions.assertFalse(orders.isEmpty());
        var cooker = new Cooker(1, orders, 3, warehouse);
        cooker.start();
        Thread.sleep(10);
        cooker.stop();
        Assertions.assertTrue(orders.isEmpty());

        var order = warehouse.poll();
        Assertions.assertEquals(order.getId(), 1);
        Assertions.assertEquals(order.getDeliveryTime(), 1);
        Assertions.assertEquals(order.getStatus(), OrderStatus.wait_delivery);

        order = warehouse.poll();
        Assertions.assertEquals(order.getId(), 2);
        Assertions.assertEquals(order.getDeliveryTime(), 1);
        Assertions.assertEquals(order.getStatus(), OrderStatus.wait_delivery);
    }

}
