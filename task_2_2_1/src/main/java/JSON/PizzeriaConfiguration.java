package JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PizzeriaConfiguration{
    @JsonProperty("cookers")
    CooksConfiguration cooksConfiguration;

    @JsonProperty("couriers")
    CouriersConfiguration couriersConfiguration;

    @JsonProperty("warehouse")
    WarehouseConfiguration warehouseConfiguration;

    @JsonProperty("cookingTime")
    Integer cookingTime = 0;


    public PizzeriaConfiguration(){
        cooksConfiguration = new CooksConfiguration();
        couriersConfiguration = new CouriersConfiguration();
        warehouseConfiguration = new WarehouseConfiguration();
    }

    @Getter
    @Setter
    public static class CooksConfiguration{
        Integer count = 0;
        Integer[] experience = new Integer[count];
    }

    @Getter
    @Setter
    public static class CouriersConfiguration{
        Integer count = 0;
        Integer[] backpackCap = new Integer[count];
    }

    @Getter
    @Setter
    public static class WarehouseConfiguration{
        Integer capacity = 0;
    }
}