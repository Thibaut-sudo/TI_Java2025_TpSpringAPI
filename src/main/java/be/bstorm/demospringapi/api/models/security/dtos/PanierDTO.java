package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.dl.entities.Panier;
import be.bstorm.demospringapi.dl.entities.ProduitPanier;
import be.bstorm.demospringapi.dl.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


public record PanierDTO(
    UUID idPanier,
    int prixTotal,
    String statut,
    UUID userId,
    List<UUID> produits

) {

}
