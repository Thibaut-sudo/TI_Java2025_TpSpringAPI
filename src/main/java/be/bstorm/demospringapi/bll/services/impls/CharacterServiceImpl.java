package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.dtos.CharacterDTO;
import be.bstorm.demospringapi.bll.exceptions.user.UserNotFoundException;
import be.bstorm.demospringapi.bll.services.CharacterService;
import be.bstorm.demospringapi.dal.repositories.CharacterRepository;
import be.bstorm.demospringapi.dal.repositories.UserCharacterRepository;
import be.bstorm.demospringapi.dal.repositories.UserRepository;
import be.bstorm.demospringapi.dl.entities.Personnage;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.dl.entities.UserCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository personnageRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final UserRepository userRepository;

    private final Random random = new Random();

    @Override
    public void assignCharactersToUser(Long userId, double orderAmount) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        int numCharacters = (int) (orderAmount / 20);

        for (int i = 0; i < numCharacters; i++) {
            Personnage personnage = getRandomPersonnage(orderAmount);
            UserCharacter userCharacter = new UserCharacter();
            userCharacter.setUser(user);
            userCharacter.setPersonnage(personnage);
            userCharacter.setObtainedAt(LocalDateTime.now());

            userCharacterRepository.save(userCharacter);
        }
    }

    @Override
    public List<CharacterDTO> getUserCharacters(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        return userCharacterRepository.findByUser(user).stream()
                .map(userCharacter -> CharacterDTO.fromCharacter(userCharacter.getPersonnage()))
                .collect(Collectors.toList());
    }

    public Personnage getRandomPersonnage(Double orderAmount) {
        double roll = random.nextDouble();
        if (orderAmount < 100) {
            return roll < 0.9 ? getRandomByRarity("Common") : getRandomByRarity("Rare");
        } else if (orderAmount < 200) {
            return roll < 0.7 ? getRandomByRarity("Common") : getRandomByRarity("Rare");
        } else {
            if (roll < 0.5) return getRandomByRarity("Common");
            else if (roll < 0.8) return getRandomByRarity("Rare");
            else if (roll < 0.95) return getRandomByRarity("Epic");
            else return getRandomByRarity("Legendary");
        }
    }

    private Personnage getRandomByRarity(String rarity) {
        List<Personnage> personnages = personnageRepository.findByRarity(rarity);
        if (personnages.isEmpty()) {
            throw new IllegalStateException("Aucun personnage trouvé pour la rareté : " + rarity);
        }
        return personnages.get(random.nextInt(personnages.size()));
    }
}