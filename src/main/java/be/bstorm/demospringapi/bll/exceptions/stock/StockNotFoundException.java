package be.bstorm.demospringapi.bll.exceptions.stock;

import be.bstorm.demospringapi.bll.exceptions.MotherException;
import org.springframework.http.HttpStatus;

public class StockNotFoundException extends MotherException {

    public StockNotFoundException(HttpStatus status, Object error) {
        super(status, error);
    }
}
