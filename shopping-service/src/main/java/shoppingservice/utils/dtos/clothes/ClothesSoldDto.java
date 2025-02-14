package shoppingservice.utils.dtos.clothes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClothesSoldDto implements Serializable {
    private int ordersPaid;
    private int ordersShipped;
    private int ordersDelivered;
}
