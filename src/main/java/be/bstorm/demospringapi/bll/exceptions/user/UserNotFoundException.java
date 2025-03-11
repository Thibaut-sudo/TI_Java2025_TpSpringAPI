package be.bstorm.demospringapi.bll.exceptions.user;

import be.bstorm.demospringapi.bll.exceptions.MotherException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends MotherException {

    public UserNotFoundException(HttpStatus status, Object error) {
        super(status, error);
    }
}