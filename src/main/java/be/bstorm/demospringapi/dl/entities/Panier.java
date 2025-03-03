package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "paniers")
@Getter
@Setter
@NoArgsConstructor
public class Panier {

    @Id
    @GeneratedValue
    private UUID Id_Panier;

    @Column(nullable = false)
    private int prixTotal;

    @Column(nullable = false)
    private String statut;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<ProduitPanier> produits;

    public Panier(User user, int prixTotal, String statut) {
        this.user = user;
        this.prixTotal = prixTotal;
        this.statut = statut;
    }

}