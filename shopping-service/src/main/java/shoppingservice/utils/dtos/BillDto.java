package shoppingservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.Address;
import shoppingservice.enitites.CardInfo;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto implements Serializable {
    private Long userId;
    private Long date;
    private Double price;
    private Address address;
    private CardInfo cardInfo;
    private List<ArticleForBillDto> articleForBillDtos;
}
