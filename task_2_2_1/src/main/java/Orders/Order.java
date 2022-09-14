package Orders;

import lombok.Getter;
@Getter
public class Order{
    int id;
    OrderStatus status;
    int deliveryTime;

    public Order(int id, int deliveryTime){
        this.id = id;
        this.deliveryTime = deliveryTime;
        this.status = OrderStatus.wait_cook;
        System.out.printf("%d order created\n", id);
    }

    public void setStatus(OrderStatus status){
        var OldStatus = this.status;
        this.status = status;
        System.out.printf("%d status changed from %s to %s\n", id, OldStatus, status);
    }
}


