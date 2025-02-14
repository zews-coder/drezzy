package shoppingservice.utils.dtos.clothes;

import lombok.Data;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeClothes;

import java.io.Serializable;

@Data
public class CreateClothesDto implements Serializable {
    private String title;
    private String description;
    private Double price;
    private Integer discount;
    private Sex sex;
    private SubtypeClothes subtype_clothes;
    private String size;
}
