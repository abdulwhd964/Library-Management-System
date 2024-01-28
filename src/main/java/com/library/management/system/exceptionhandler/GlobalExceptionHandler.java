package com.library.management.system.exceptionhandler;

import com.library.management.system.exception.BookNotFoundException;
import com.library.management.system.exception.BorrowRecordNotFoundException;
import com.library.management.system.exception.ErrorResponse;
import com.library.management.system.exception.PatronNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.StringJoiner;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String SPACE = " ";

    @ExceptionHandler(value = BookNotFoundException.class)
    ResponseEntity<ErrorResponse> handleBookNotFoundException(final BookNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = PatronNotFoundException.class)
    ResponseEntity<ErrorResponse> handlePatronNotFoundException(final PatronNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ErrorResponse> handleUnAuthorized(final Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value = BorrowRecordNotFoundException.class)
    ResponseEntity<ErrorResponse> handleBorrowRecordNotFoundException(final BorrowRecordNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = DataIntegrityViolationException.class)
    ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(final DataIntegrityViolationException exception) {
        return new ResponseEntity<>(new ErrorResponse("Book Already Exist!!" ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    ResponseEntity<ErrorResponse> handleConstrainViolation(final ConstraintViolationException exception) {
        return new ResponseEntity<>(new ErrorResponse(buildErrors(exception).toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ErrorResponse(buildErrors(exception).toString()), HttpStatus.BAD_REQUEST);
    }

    private StringJoiner buildErrors(final ConstraintViolationException exception) {
        var errors = new StringJoiner(",");
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            var propertyPath = constraintViolation.getPropertyPath().toString();
            var errorViolation = propertyPath.substring(propertyPath.lastIndexOf(".") + 1);
            errors.add(errorViolation + SPACE + constraintViolation.getMessage());
        }
        return errors;
    }

    private StringJoiner buildErrors(final MethodArgumentNotValidException exception) {
        var errors = new StringJoiner(",");
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + SPACE + fieldError.getDefaultMessage());
        }
        return errors;
    }

}
