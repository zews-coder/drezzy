package shoppingservice.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shoppingservice.enitites.enums.Sex;
import shoppingservice.interfaces.MyEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Article implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long vendorId;
    private String title;
    private String description;
    private Date creationDate;
    private Double price;
    private Integer discount;
    @Enumerated
    private Sex sex;
    private Boolean visible = true;
//    @Column(nullable = false, unique = true)
//    private String uuid;
//
//    @PrePersist
//    public void prePersist() {
//        if (uuid == null) {
//            uuid = UUID.randomUUID().toString();
//        }
//    }
}
