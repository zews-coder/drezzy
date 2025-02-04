package shoppingservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.enitites.enums.SubtypeClothes;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesWithImageDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Date creationDate;
    private Double price;
    private Integer discount;
    private Sex sex;
    private Boolean visible;
    private String subtype;
    private String size;
    private String imageUrl;
}
