package com.fab5.bankingapp.handler;

import com.fab5.bankingapp.errors.ErrorDetail;
import com.fab5.bankingapp.errors.ValidationError;
import com.fab5.bankingapp.exceptions.InsufficientFundsException;
import com.fab5.bankingapp.exceptions.InvalidDepositAmount;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.NoSuchElementFoundException;
import com.fab5.bankingapp.utility.ExceptionTypeExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NoSuchElementFoundException.class)
    public ResponseEntity<?> handleNoSuchElementFoundException(NoSuchElementFoundException
                                                                       ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException
                                                                       ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorDetail, headers, status, request); //what is handleExceptionInternal? Seems like a method used to return a body for any exception handling?
    }

    @ExceptionHandler({InvalidDepositAmount.class, InsufficientFundsException.class})
    public ResponseEntity<Object> handleDepositExceptions(RuntimeException ex, WebRequest request) {
        ErrorDetail errorDetail = generateBasicErrorDetailEndingInException(ex, HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }

    private ErrorDetail generateBasicErrorDetailEndingInException(RuntimeException ex, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage(ex.getMessage());
        return errorDetail;
    }
}
