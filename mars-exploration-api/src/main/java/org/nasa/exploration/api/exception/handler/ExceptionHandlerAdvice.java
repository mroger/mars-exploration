package org.nasa.exploration.api.exception.handler;

import org.nasa.exploration.api.exception.ProbeCollisionException;
import org.nasa.exploration.api.exception.ProbeNotFoundByIdException;
import org.nasa.exploration.api.exception.ProbeNotFoundByPositionException;
import org.nasa.exploration.model.exception.PositionAlreadyTakenException;
import org.nasa.exploration.model.exception.PositionOutOfBoundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Locale;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private ResponseEntity<Object> createErrorResponseEntity(HttpStatus status, String message, String messageCode, Locale locale,
            Object... params) {

        String translatedMessage = message;
        if (messageCode != null) {
            translatedMessage = messageSource.getMessage(messageCode, params, locale);
        }

        ErrorResponse errorResponse = createErrorResponse(status, translatedMessage);

        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(error.getDefaultMessage(), null, locale);

        return createErrorResponseEntity(HttpStatus.BAD_REQUEST, message, null, locale);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Locale locale = LocaleContextHolder.getLocale();
        return createErrorResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), null, locale);
    }

    @ExceptionHandler(PositionOutOfBoundsException.class)
    public final ResponseEntity<Object> positionOutOfBounds(final PositionOutOfBoundsException ex, Locale locale) {
        return createErrorResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), null, locale);
    }

    @ExceptionHandler(PositionAlreadyTakenException.class)
    public final ResponseEntity<Object> positionAlreadyTaken(final PositionAlreadyTakenException ex, Locale locale) {
        return createErrorResponseEntity(HttpStatus.CONFLICT, ex.getMessage(), null, locale);
    }

    @ExceptionHandler(ProbeNotFoundByIdException.class)
    public final ResponseEntity<Object> probeNotFoundById(final ProbeNotFoundByIdException ex, Locale locale) {
        return createErrorResponseEntity(HttpStatus.NOT_FOUND, null, ex.getMessage(), locale, ex.getId());
    }

    @ExceptionHandler(ProbeNotFoundByPositionException.class)
    public final ResponseEntity<Object> probeNotFoundByPosition(final ProbeNotFoundByPositionException ex, Locale locale) {
        return createErrorResponseEntity(HttpStatus.NOT_FOUND, null, ex.getMessage(), locale, ex.getX(), ex.getY());
    }

    @ExceptionHandler(ProbeCollisionException.class)
    public final ResponseEntity<Object> probeCollision(final ProbeCollisionException ex, Locale locale) {
        return createErrorResponseEntity(HttpStatus.CONFLICT, null, ex.getMessage(), locale, ex.getProbeId());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> uncaughtException(final Exception ex, Locale locale) {
        return createErrorResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), null, locale);
    }

    private ErrorResponse createErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(status.value());
        errorResponse.setError(status.getReasonPhrase());
        errorResponse.setMessage(message);
        return errorResponse;
    }
}
