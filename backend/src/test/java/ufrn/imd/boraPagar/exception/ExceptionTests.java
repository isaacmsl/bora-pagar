package ufrn.imd.boraPagar.exception;

import org.hibernate.QueryException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mongodb.MongoException;

import jakarta.validation.ValidationException;
import ufrn.imd.boraPagar.core.ApplicationConstants;
import ufrn.imd.boraPagar.exceptions.ResourceNotFoundException;
import ufrn.imd.boraPagar.exceptions.handler.ExceptionResponse;
import ufrn.imd.boraPagar.exceptions.handler.GlobalExceptionHandler;

public class ExceptionTests {

    @Autowired
    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    public void hanleAllTest() {
        String errorMsg = "Generic error";
        Exception dataAccessException = new DataAccessException(errorMsg) {};

        ResponseEntity<ExceptionResponse> responseEntity = exceptionHandler.handleAll(dataAccessException);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(ResponseEntity.class, responseEntity.getClass());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus().value());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Assert.assertEquals(errorMsg, responseEntity.getBody().getErrors().get(0));
    }

    @Test
    public void handleResourceNotFoundExceptionTest() {
        String errorMsg = "Not found error";
        ResourceNotFoundException dataAccessException = new ResourceNotFoundException(errorMsg) {};

        ResponseEntity<ExceptionResponse> responseEntity = exceptionHandler.handleResourceNotFoundException(dataAccessException);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(ResponseEntity.class, responseEntity.getClass());
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatus().value());
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assert.assertEquals(errorMsg, responseEntity.getBody().getMessage());
        Assert.assertEquals(errorMsg, responseEntity.getBody().getErrors().get(0));
    }

    @Test
    public void handleDataAccessExceptionTest() {
        String errorMsg = "Database error";
        DataAccessException dataAccessException = new DataAccessException(errorMsg) {};

        ResponseEntity<ExceptionResponse> responseEntity = exceptionHandler.handleDataAccessException(dataAccessException);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(ResponseEntity.class, responseEntity.getClass());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus().value());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Assert.assertEquals(ApplicationConstants.INTERNAL_ERROR_MESSAGE, responseEntity.getBody().getMessage());
        Assert.assertEquals(errorMsg, responseEntity.getBody().getErrors().get(0));
    }

    @Test
    public void handleMongoExceptionTest() {
        String errorMsg = "MongoDB error";
        MongoException mongoException = new MongoException(errorMsg) {};

        ResponseEntity<ExceptionResponse> responseEntity = exceptionHandler.handleMongoException(mongoException);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(ResponseEntity.class, responseEntity.getClass());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus().value());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Assert.assertEquals(ApplicationConstants.INTERNAL_ERROR_MONGO_MESSAGE, responseEntity.getBody().getMessage());
        Assert.assertEquals(errorMsg, responseEntity.getBody().getErrors().get(0));
    }

    @Test
    public void handleValidationExceptionTest() {
        String errorMsg = "Validation error";
        ValidationException dataAccessException = new ValidationException(errorMsg) {};

        ResponseEntity<ExceptionResponse> responseEntity = exceptionHandler.handleValidationException(dataAccessException);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(ResponseEntity.class, responseEntity.getClass());
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus().value());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertEquals(ApplicationConstants.VALIDATION_ERROR_MONGO_MESSAGE, responseEntity.getBody().getMessage());
        Assert.assertEquals(errorMsg, responseEntity.getBody().getErrors().get(0));
    }

    @Test
    public void handleQueryExceptionTest() {
        String errorMsg = "Query error";
        String queryStr = "findAll";
        QueryException dataAccessException = new QueryException(errorMsg, queryStr) {};

        ResponseEntity<ExceptionResponse> responseEntity = exceptionHandler.handleQueryException(dataAccessException);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(ResponseEntity.class, responseEntity.getClass());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus().value());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Assert.assertEquals(ApplicationConstants.QUERY_EXCEPTION_MESSAGE, responseEntity.getBody().getMessage());
        Assert.assertEquals(errorMsg + " [" + queryStr + "]", responseEntity.getBody().getErrors().get(0));
    }

}