package shoppingservice.utils.dtos;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeClothes;

import java.io.Serializable;
import java.util.Date;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CreateClothesDto implements Serializable {
    private String title;
    private String description;
    private Double price;
    private Integer discount;
    private Sex sex;
    private SubtypeClothes subtype_clothes;
    private String size;
}
