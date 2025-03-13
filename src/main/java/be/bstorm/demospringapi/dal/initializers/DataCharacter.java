package be.bstorm.demospringapi.dal.initializers;

import be.bstorm.demospringapi.dal.repositories.CharacterRepository;
import be.bstorm.demospringapi.dl.entities.Personnage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataCharacter implements CommandLineRunner {
    private final CharacterRepository characterRepository;

    @Override
    public void run(String... args) {
        if (characterRepository.count() == 0) {
            List<Personnage> personnages = List.of(
                    new Personnage(null, "Sano", "Legendary"),
                    new Personnage(null, "Meredithe", "Common"),
                    new Personnage(null, "Hatori", "Common"),
                    new Personnage(null, "Noel", "Rare"),
                    new Personnage(null, "Jean", "Epic"),
                    new Personnage(null, "Hisae", "Common"),
                    new Personnage(null, "Shizuka", "Common"),
                    new Personnage(null, "Lucie", "Common"),
                    new Personnage(null, "Tazoumi", "Rare"),
                    new Personnage(null, "Matatsuko", "Epic")
            );
            characterRepository.saveAll(personnages);
            System.out.println("10 personnages communs ont été ajoutés à la base de données !");
        } else {
            System.out.println("Des personnages existent déjà en base, pas besoin d'en ajouter.");
        }
    }
}