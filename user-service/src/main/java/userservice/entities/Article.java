package userservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import userservice.entities.enums.Sex;

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
    private Boolean visible = true;
    private Sex sex;
    @Lob
    private byte[] image;
    private Integer visited = 0;
}
