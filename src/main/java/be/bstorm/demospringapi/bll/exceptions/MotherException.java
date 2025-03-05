package be.bstorm.demospringapi.bll.exceptions;

import org.springframework.http.HttpStatus;

public class MotherException extends RuntimeException {
    private final HttpStatus status;
    private final Object error;

    public MotherException(HttpStatus status, Object error) {
        this.status = status;
        this.error = error;
    }

}
