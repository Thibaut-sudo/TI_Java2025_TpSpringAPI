package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode @ToString
public class Product extends ProduitPanier {
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

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Stock>stocks = new ArrayList<>();


    public Product(@NotBlank @Size(max = 30) String nom, @NotBlank @Size(max = 250) String description, @NotBlank String imageUrl, @NotBlank @Size(max = 30) BigDecimal prix) {
        this.nom = nom;
        this.description = description;
        this.imageUrl = imageUrl;
        this.prix = prix;
    }
}
