package com.jw.clushtest.calendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                AppErrorCode.INVALID_REQUEST.getStatusNum(),
                AppErrorCode.INVALID_REQUEST.getMessage());

        return new ResponseEntity<>(appErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppErrorResponse> handleNotFoundException(AppException ex) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                AppErrorCode.NOT_FOUND.getStatusNum(),
                ex.getMessage() != null ? ex.getMessage() : AppErrorCode.NOT_FOUND.getMessage());

        return new ResponseEntity<>(appErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorResponse> handleException(Exception ex) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                AppErrorCode.INTERNAL_SERVER_ERROR.getStatusNum(),
                AppErrorCode.INTERNAL_SERVER_ERROR.getMessage());

        return new ResponseEntity<>(appErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<AppErrorResponse> handleIOException(IOException ex) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                AppErrorCode.INTERNAL_SERVER_ERROR.getStatusNum(),
                "입출력 오류가 발생했습니다.");

        return new ResponseEntity<>(appErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
