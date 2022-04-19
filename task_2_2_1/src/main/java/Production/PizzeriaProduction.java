package Production;

import Cookers.CookProduction;
import Couriers.CourierProduction;
import JSON.PizzeriaConfiguration;
import Orders.Order;
import Orders.OrderCreator;

import java.util.concurrent.LinkedBlockingQueue;

public class PizzeriaProduction {
    public static void startOfWorking(PizzeriaConfiguration config){
        LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();
        LinkedBlockingQueue<Order> warehouse = new LinkedBlockingQueue<Order>(config.getWarehouseConfiguration().getCapacity());
        var queueOfOrders = new OrderCreator(orders);

        CookProduction.createCookers(orders, config.getCooksConfiguration(), warehouse, config.getCookingTime());
        CourierProduction.createCouriers(warehouse, config.getCouriersConfiguration());
        queueOfOrders.start();
    }
}
