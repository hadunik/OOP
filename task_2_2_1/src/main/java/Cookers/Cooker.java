package Cookers;

import Orders.Order;
import Orders.OrderStatus;
import lombok.AllArgsConstructor;

import java.util.concurrent.LinkedBlockingQueue;
@AllArgsConstructor
public class Cooker extends Thread {
    private Integer workExp;
    private LinkedBlockingQueue<Order> orders;
    private Integer cookingTime;
    private LinkedBlockingQueue<Order> warehouse;

    @Override
    public void run() {
        while (true) {
            try {
                var pizza = orders.poll();
                if (pizza != null) {
                    pizza.setStatus(OrderStatus.cooking);
                    Thread.sleep(cookingTime - workExp);
                    pizza.setStatus(OrderStatus.wait_delivery);
                    warehouse.put(pizza);
                }
            } catch (InterruptedException e) {}
        }
    }
}
