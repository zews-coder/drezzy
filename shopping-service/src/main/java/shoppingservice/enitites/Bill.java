package shoppingservice.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Long id;
    private Long customerId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Article> articleList = new ArrayList<>();
    private Date date;
    private Status status;
    private Double price;
    @Embedded
    private Address address;
    @Embedded
    private CardInfo cardInfo;
}
