package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.dl.entities.Personnage;

public record CharacterDTO(Long id, String name, String rarity) {
    public static CharacterDTO fromCharacter(Personnage character) {
        return new CharacterDTO(character.getId(), character.getName(), character.getRarity());
    }
}