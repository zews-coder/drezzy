package shoppingservice.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.articles.Article;
import shoppingservice.enitites.embedded.Address;
import shoppingservice.enitites.embedded.CardInfo;
import shoppingservice.enitites.enums.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bill_id;
    private Long customerId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "bill_article",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Article> articleList = new ArrayList<>();
    private Date date;
    private Status status;
    private Double price;
    @Embedded
    private Address address;
    @Embedded
    private CardInfo cardInfo;
}
