package ufrn.imd.boraPagar.exceptions.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ufrn.imd.boraPagar.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> hanleAll(Exception ex) {
        ExceptionResponse response = new ExceptionResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST,
            ex.getMessage());
        
        response.getErrors().add(ex.getLocalizedMessage());

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND,
            ex.getMessage());

        response.getErrors().add(ex.getMessage());

        return new ResponseEntity<>(response, response.getStatus());
    }
}
