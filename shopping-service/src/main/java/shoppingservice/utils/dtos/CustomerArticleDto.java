package shoppingservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerArticleDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Integer discount;
    private String size;
    private String imageUrl;
}
