package shoppingservice.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemsSoldDto implements Serializable {
    private int clothesSold;
    private int shoesSold;
    private int ArticlesSold;
}
