package userservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import userservice.entities.embedded.Address;
import userservice.entities.embedded.CardInfo;

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
