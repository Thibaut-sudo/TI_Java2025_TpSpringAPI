package be.bstorm.demospringapi.api.controllers.advisor;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex){
        return ResponseEntity.status(500).body(ex.getMessage());
    }

}
