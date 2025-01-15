package shoppingservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeShoes;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShoesDto implements Serializable {
    private String title;
    private String description;
    private Double price;
    private Integer discount;
    private Sex sex;
    private SubtypeShoes subtype;
    private Integer size;
}
