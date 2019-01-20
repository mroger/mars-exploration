package org.nasa.exploration.api.exception.handler;

import org.nasa.exploration.model.exception.PositionAlreadyTakenException;
import org.nasa.exploration.model.exception.PositionOutOfBoundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private ResponseEntity<Object> createErrorResponseEntity(HttpStatus status, String message) {
        ErrorResponse errorResponse = createErrorResponse(status, message);

        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        String message = messageSource.getMessage(error.getDefaultMessage(), null, null);

        return createErrorResponseEntity(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(PositionOutOfBoundsException.class)
    public final ResponseEntity<Object> positionOutOfBounds(final PositionOutOfBoundsException ex) {
        return createErrorResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(PositionAlreadyTakenException.class)
    public final ResponseEntity<Object> positionAlreadyTaken(final PositionAlreadyTakenException ex) {
        return createErrorResponseEntity(HttpStatus.CONFLICT, ex.getMessage());
    }

    private ErrorResponse createErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setError(status.getReasonPhrase());
        errorResponse.setMessage(message);
        return errorResponse;
    }
}
