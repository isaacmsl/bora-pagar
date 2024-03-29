package ufrn.imd.boraPagar.exceptions.handler;

import java.time.LocalDateTime;

import org.hibernate.QueryException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mongodb.MongoException;

import jakarta.validation.ValidationException;
import ufrn.imd.boraPagar.core.ApplicationConstants;
import ufrn.imd.boraPagar.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAll(Exception ex) {
        ExceptionResponse response = new ExceptionResponse(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR,
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

    @ExceptionHandler(DataAccessException.class)
    public  ResponseEntity<ExceptionResponse> handleDataAccessException(DataAccessException ex) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.INTERNAL_ERROR_MESSAGE);
        response.getErrors().add(ex.getMessage());

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(MongoException.class)
    public  ResponseEntity<ExceptionResponse> handleMongoException(MongoException ex) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.INTERNAL_ERROR_MONGO_MESSAGE);
        response.getErrors().add(ex.getMessage());

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public  ResponseEntity<ExceptionResponse> handleValidationException(ValidationException ex) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ApplicationConstants.VALIDATION_ERROR_MONGO_MESSAGE);
        response.getErrors().add(ex.getMessage());

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(QueryException.class)
    public  ResponseEntity<ExceptionResponse> handleQueryException(QueryException ex) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.QUERY_EXCEPTION_MESSAGE);
        response.getErrors().add(ex.getMessage());

        return new ResponseEntity<>(response, response.getStatus());
    }
}
