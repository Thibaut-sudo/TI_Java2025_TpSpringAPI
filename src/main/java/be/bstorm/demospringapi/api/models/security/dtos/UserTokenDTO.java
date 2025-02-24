package be.bstorm.demospringapi.api.models.security.dtos;

public record UserTokenDTO(
        UserDTO user,
        String token
) {
}
