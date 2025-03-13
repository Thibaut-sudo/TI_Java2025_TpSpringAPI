package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.api.models.security.dtos.CharacterDTO;
import be.bstorm.demospringapi.dl.entities.Personnage;

import java.util.List;

public interface CharacterService {
    void assignCharactersToUser(Long userId, double orderAmount);
    List<CharacterDTO> getUserCharacters(Long userId);

    Personnage getRandomPersonnage(Double totalAmount);
}