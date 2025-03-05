package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product produit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Ajout de la relation avec User

    private int quantiteDisponible;

    public Stock(int quantiteDisponible, Product produit, User user) {
        this.quantiteDisponible = quantiteDisponible;
        this.produit = produit;
        this.user = user;
    }


}


