package shoppingservice.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.articles.Article;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Set<String> articleIds = new HashSet<>();
    @ManyToMany(cascade = CascadeType.DETACH)
    private Collection<Article> articles;
}
