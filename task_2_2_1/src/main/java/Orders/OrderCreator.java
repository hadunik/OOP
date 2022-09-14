package Orders;

import java.util.concurrent.LinkedBlockingQueue;

public class OrderCreator extends Thread{
    private final LinkedBlockingQueue<Order> queue;

    public OrderCreator(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int id = 0;
        while (true) {
            try {
                Thread.sleep((long)(100 * (Math.random())));
                queue.add(new Order(id++, (int) (300 *(Math.random()))));
            } catch (InterruptedException e) {
                if (this.isAlive()) {
                    continue;
                }
            }
        }
    }
}
