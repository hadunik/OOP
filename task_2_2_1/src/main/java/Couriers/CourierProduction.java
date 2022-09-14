package Couriers;

import JSON.PizzeriaConfiguration;
import Orders.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class CourierProduction{
    public static void createCouriers(
            LinkedBlockingQueue<Order> warehouse,
            PizzeriaConfiguration.CouriersConfiguration config
    ){
        List<Integer> backpackCaps = new ArrayList<>(Arrays.asList(config.getBackpackCap()).subList(0, config.getCount()));
        backpackCaps.forEach((it) -> {
            new Courier(warehouse, it).start();
        });
    }
}
