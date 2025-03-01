package com.ivoyant.secure_wallet.exception;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final CustomExceptionObject customExceptionObject;

    public GlobalExceptionHandler(CustomExceptionObject customExceptionObject) {
        this.customExceptionObject = customExceptionObject;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomExceptionObject> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        customExceptionObject.setStatus(HttpStatus.NOT_FOUND.value());
        customExceptionObject.setMessage(e.getMessage());
        customExceptionObject.setTimestamp(new Timestamp(System.currentTimeMillis()));
        customExceptionObject.setDetails("uri=" + request.getRequestURI());
        customExceptionObject.setException(e.getClass().getSimpleName());
        customExceptionObject.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customExceptionObject);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionObject> handleGenericException(Exception e, HttpServletRequest request) {
        customExceptionObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        customExceptionObject.setMessage(e.getMessage());
        customExceptionObject.setTimestamp(new Timestamp(System.currentTimeMillis()));
        customExceptionObject.setDetails("uri=" + request.getRequestURI());
        customExceptionObject.setException(e.getClass().getSimpleName());
        customExceptionObject.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customExceptionObject);
    }

    @ExceptionHandler(TransactionIncompleteException.class)
    public ResponseEntity<CustomExceptionObject> handleTransactionIncomplete(TransactionIncompleteException e, HttpServletRequest request) {
        customExceptionObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        customExceptionObject.setMessage(e.getMessage());
        customExceptionObject.setTimestamp(new Timestamp(System.currentTimeMillis()));
        customExceptionObject.setDetails("uri=" + request.getRequestURI());
        customExceptionObject.setException(e.getClass().getSimpleName());
        customExceptionObject.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customExceptionObject);
    }
}
