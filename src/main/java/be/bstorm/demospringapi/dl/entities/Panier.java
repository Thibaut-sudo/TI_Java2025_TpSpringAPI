package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private UUID idPanier;

    @Column(nullable = false)
    private int prixTotal;

    @Column(nullable = false)
    private String statut;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitPanier> produits = new ArrayList<>();

    public Panier(User user, int prixTotal, String statut) {
        this.user = user;
        this.prixTotal = prixTotal;
        this.statut = statut;
    }

}