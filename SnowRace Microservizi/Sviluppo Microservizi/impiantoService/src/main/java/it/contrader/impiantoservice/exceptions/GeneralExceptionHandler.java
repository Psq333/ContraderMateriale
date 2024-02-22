package it.contrader.impiantoservice.exceptions;

import io.jsonwebtoken.lang.Maps;
import it.contrader.impiantoservice.dto.ImpiantoDTO;
import it.contrader.impiantoservice.feignClient.UserFeignClient;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler({NotExistAdminException.class, NotFoundImpiantoException.class})
    public ResponseEntity<String> handlerNotFoundImpiantoException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>  handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( (error) ->{
            String field = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(field,errorMessage);
            }
        );
        return ResponseEntity.badRequest().body(errors);
    }

}
