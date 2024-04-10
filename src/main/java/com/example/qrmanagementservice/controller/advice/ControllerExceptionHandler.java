package com.example.qrmanagementservice.controller.advice;

import com.example.qrmanagementservice.model.dto.ApiErrorDto;
import com.example.qrmanagementservice.model.exception.QrManagerException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(QrManagerException.class)
    public ResponseEntity<?> qrManagerException(Exception e) {
        return ResponseEntity.internalServerError()
                .body(new ApiErrorDto(e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> badRequestException(Exception e) {
        return ResponseEntity.badRequest()
                .body(new ApiErrorDto(e.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> expiredJwtException(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiErrorDto(e.getMessage()));
    }
}
