package userservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleForBillDto implements Serializable {
    private String title;
    private String size;
    private Double price;
}
