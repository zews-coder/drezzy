package shoppingservice.enitites.articles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.Bag;
import shoppingservice.enitites.enums.Sex;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date creationDate;
    private Double price;
    private Integer discount = 0;
    @Enumerated
    private Sex sex;
    private Boolean visible = true;
    @Lob
    private byte[] image;
    private Integer visited = 0;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, optional = false)
    private Bag ak;

    public Bag getAk() {
        return ak;
    }

    public void setAk(Bag ak) {
        this.ak = ak;
    }
}
