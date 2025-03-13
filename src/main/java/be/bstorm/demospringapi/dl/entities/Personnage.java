package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "character")
@Getter
@Setter
@NoArgsConstructor
public class Personnage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String rarity;

    public Personnage(Long id, String name, String rarity) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
    }
}