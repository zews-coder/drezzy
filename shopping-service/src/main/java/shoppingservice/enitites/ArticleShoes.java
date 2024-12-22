package shoppingservice.enitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shoppingservice.enitites.enums.SubtypeShoes;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleShoes extends Article {
    @Enumerated
    private SubtypeShoes subtype;
    private Integer size;
}
