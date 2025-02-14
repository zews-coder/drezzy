package shoppingservice.utils.dtos.bill;

import lombok.*;
import shoppingservice.enitites.embedded.Address;
import shoppingservice.enitites.embedded.CardInfo;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillDto implements Serializable {
    private List<String> articleIds;
    private Address address;
    private CardInfo cardInfo;
}
