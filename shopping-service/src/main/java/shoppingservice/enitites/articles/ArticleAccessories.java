package shoppingservice.enitites.articles;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shoppingservice.enitites.enums.SubtypeAccessories;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAccessories extends Article {
    @Enumerated
    private SubtypeAccessories subtype;
}
