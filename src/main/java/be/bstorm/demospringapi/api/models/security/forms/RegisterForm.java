package be.bstorm.demospringapi.api.models.security.forms;

import be.bstorm.demospringapi.dl.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterForm(
        @NotBlank @Size(max = 150)
        String email,
        @NotBlank
        String password,
        @NotBlank @Size(max = 123)
        String firstName,
        @NotBlank @Size(max = 80)
        String lastName,
        //Todo beforeToday
        LocalDate birthDate
) {

    public User toUser() {
        return new User(
                this.email,
                this.password,
                this.firstName,
                this.lastName,
                this.birthDate
        );
    }
}
