package it.contrader.authenticationservice.customException;


import feign.FeignException;
import feign.Request;
import it.contrader.authenticationservice.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.Map;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage() ,HttpStatus.NOT_FOUND);
    }
}
