package Cookers;

import JSON.PizzeriaConfiguration;
import Orders.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class CookProduction {
    public static void createCookers(
            LinkedBlockingQueue<Order> queueForKitchen,
            PizzeriaConfiguration.CooksConfiguration config,
            LinkedBlockingQueue<Order> queueForWarehouse,
            Integer cookingTime
    ) {
        List<Integer> experience = new ArrayList<>(Arrays.asList(config.getExperience()));
        experience.forEach((exp) -> {
            new Cooker(exp, queueForKitchen, cookingTime, queueForWarehouse).start();
        });
    }
}
