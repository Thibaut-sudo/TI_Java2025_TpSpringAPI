package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.UserDTO;
import be.bstorm.demospringapi.bll.services.UserService;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Utilisateur", description = "API pour gérer les utilisateurs") // Regroupe les endpoints dans Swagger
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Récupérer tous les utilisateurs",
            description = "Retourne une liste paginée et filtrée des utilisateurs en fonction des paramètres fournis."
    )
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @Parameter(description = "Filtres dynamiques sous forme de clé-valeur")
            @RequestParam Map<String, String> params,

            @Parameter(description = "Numéro de la page", example = "0")
            @RequestParam(required = false, defaultValue = "0") int page,

            @Parameter(description = "Nombre d'éléments par page", example = "10")
            @RequestParam(required = false, defaultValue = "10") int size,

            @Parameter(description = "Champ sur lequel trier les résultats", example = "lastName")
            @RequestParam(required = false, defaultValue = "lastName") String sort
    ) {
        List<SearchParam<User>> searchParams = SearchParam.create(params);
        List<UserDTO> users = userService.getUsers(
                        searchParams,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))
                ).stream()
                .map(UserDTO::fromUser)
                .toList();

        return ResponseEntity.ok(users);
    }
}
