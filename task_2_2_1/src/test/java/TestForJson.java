import Cookers.CookProduction;
import Couriers.Courier;
import Couriers.CourierProduction;
import JSON.PizzeriaConfiguration;
import Orders.Order;
import Orders.OrderCreator;
import Production.PizzeriaProduction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class TestForJson {
    @Test
    public void Test() throws IOException {
        PizzeriaConfiguration readed = MainPizzeria.loadConfig();
        PizzeriaConfiguration.CooksConfiguration cooksConfiguration = readed.getCooksConfiguration();
        Assertions.assertEquals(cooksConfiguration.getCount(), 5);
        Assertions.assertArrayEquals(cooksConfiguration.getExperience(), new Integer[]{10,22,33,50,20});

        PizzeriaConfiguration.CouriersConfiguration couriersConfiguration = readed.getCouriersConfiguration();
        Assertions.assertEquals(couriersConfiguration.getCount(), 2);
        Assertions.assertArrayEquals(couriersConfiguration.getBackpackCap(), new Integer[]{15,10});

        PizzeriaConfiguration.WarehouseConfiguration warehouse = readed.getWarehouseConfiguration();
        Assertions.assertEquals(warehouse.getCapacity(), 4);

        Integer cookingTime = readed.getCookingTime();
        Assertions.assertEquals(cookingTime, 100);
    }
}
