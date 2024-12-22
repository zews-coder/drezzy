package shoppingservice.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.enums.Sex;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    @Id
    private Long id;
    private Long vendorId;
    private String title;
    private String description;
    private Date creationDate;
    private Double price;
    private Integer discount;
    @Enumerated
    private Sex sex;
    @ManyToMany()
    private List<Tag> tagList;
    private Boolean visible;
    private String uuid;
}
