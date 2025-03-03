package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode @ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false, length = 30)
    @Setter
    private String nom ;

    @Column(nullable = false,length = 250)
    @Setter
    private String description ;

    @Column(nullable = false)
    @Setter
    private String imageUrl;

    @Column(nullable = false)
    @Setter
    private BigDecimal prix ;

    @OneToMany
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
}
