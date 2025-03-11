package be.bstorm.demospringapi.bll.exceptions.warehouse;

import be.bstorm.demospringapi.bll.exceptions.MotherException;
import org.springframework.http.HttpStatus;

public class WarehouseNotFoundException extends MotherException {

    public WarehouseNotFoundException(HttpStatus status, Object error) {
        super(status, error);
    }
}