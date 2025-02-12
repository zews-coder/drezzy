package shoppingservice.utils.dtos;

import lombok.*;
import shoppingservice.enitites.Address;
import shoppingservice.enitites.CardInfo;

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
