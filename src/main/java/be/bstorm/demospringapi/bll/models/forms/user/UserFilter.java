package be.bstorm.demospringapi.bll.models.forms.user;

public record UserFilter (
        String firstName,
        String lastName,
        Integer minAge,
        Integer maxAge
){
}
