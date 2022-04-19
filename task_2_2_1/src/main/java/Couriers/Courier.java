package Couriers;

import Orders.Order;
import Orders.OrderStatus;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@AllArgsConstructor
public class Courier extends Thread {
    private final LinkedBlockingQueue<Order> warehouse;
    private final Integer backpackCap;

    @Override
    public void run() {
        while (true) {
            List<Order> backpack = new ArrayList<>();
            if (!warehouse.isEmpty()) {
                int canTookNow = Math.min(warehouse.size(), backpackCap);
                for (int i = 0; i < backpackCap; i++) {
                    var order = warehouse.poll();
                    if (order != null) {
                        backpack.add(order);
                        order.setStatus(OrderStatus.delivering);
                    }
                    else{
                        break;
                    }
                }
                System.out.printf("Courier took %d orders\n", backpack.size());
            }
            backpack.forEach((ord) -> {
                try {
                    Thread.sleep(ord.getDeliveryTime());
                    ord.setStatus(OrderStatus.delivered);
                } catch (InterruptedException e) {
                }
            });
        }
    }
}
