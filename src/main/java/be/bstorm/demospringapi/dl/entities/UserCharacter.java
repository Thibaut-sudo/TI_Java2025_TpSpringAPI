package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserCharacter")
@Getter
@Setter
@NoArgsConstructor
public class UserCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Personnage personnage;

    private LocalDateTime obtainedAt;
}