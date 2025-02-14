package shoppingservice.enitites.articles;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.*;
import shoppingservice.enitites.enums.SubtypeClothes;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleClothes extends Article {
    @Enumerated
    private SubtypeClothes subtype;
    private String size;
}
