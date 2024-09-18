package co.pragra.learning.newproductmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String reviews;

    @ManyToOne
    private User user;

}
