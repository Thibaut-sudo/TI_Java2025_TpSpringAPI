package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true) // Rendre nullable pour éviter l'erreur de clé étrangère

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


    public Stock(@NotBlank @Size(max = 30) int quantiteDisponible) {
        this.quantiteDisponible= quantiteDisponible;
    }
}


