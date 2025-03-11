package be.bstorm.demospringapi.bll.exceptions.Panier;

import be.bstorm.demospringapi.bll.exceptions.MotherException;
import org.springframework.http.HttpStatus;

public class PanierNotFoundException extends MotherException {

    public PanierNotFoundException(HttpStatus status, Object error) {
        super(status, error);
    }
}