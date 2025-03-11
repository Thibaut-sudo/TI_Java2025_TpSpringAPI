package be.bstorm.demospringapi.bll.exceptions.product;

import be.bstorm.demospringapi.bll.exceptions.MotherException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends MotherException {

    public ProductNotFoundException(HttpStatus status, Object error) {
        super(status, error);
    }
}