package com.user.application.exceptionhandler;

import com.user.application.dto.exception.ExceptionDTO;
import com.user.application.exceptionhandler.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> genericException(Exception ex) {
        final ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDTO> notFoundException(RuntimeException ex) {
        final ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
