package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table (name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product produit;
    private int quantiteDisponible;


    public Stock(int i, Product product) {
        this.quantiteDisponible = i;
        this.produit = product;
    }
}