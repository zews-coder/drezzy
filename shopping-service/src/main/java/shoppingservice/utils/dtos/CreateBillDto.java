package shoppingservice.utils.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
public class CreateBillDto implements Serializable {
    private List<Long> articleIds;
}
