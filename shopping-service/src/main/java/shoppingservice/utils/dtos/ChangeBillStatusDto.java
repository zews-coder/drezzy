package shoppingservice.utils.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChangeBillStatusDto implements Serializable {
    private Long billId;
    private String status;
}
