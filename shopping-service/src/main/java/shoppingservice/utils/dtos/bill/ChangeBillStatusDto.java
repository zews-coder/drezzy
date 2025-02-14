package shoppingservice.utils.dtos.bill;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChangeBillStatusDto implements Serializable {
    private Long billId;
    private String status;
}
