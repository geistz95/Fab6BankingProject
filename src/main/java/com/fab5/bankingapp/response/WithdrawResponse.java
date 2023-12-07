package com.fab5.bankingapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WithdrawResponse {

    public static ResponseEntity<Object> createdWithdrawBuilder(HttpStatus httpStatus, Object responseObject){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message","Created withdrawal and deducted it from the account");
        response.put("code", httpStatus.value());
        response.put("data", responseObject);

        return new ResponseEntity<>(response,httpStatus);
    }
    public static  ResponseEntity<Object> getWithdrawBuilder(HttpStatus httpStatus, Object object){
        Map<String,Object> response  = new LinkedHashMap<>();
        response.put("code", httpStatus.value());
        response.put("data", object);
        return new ResponseEntity<>(response,httpStatus);
    }
    public static ResponseEntity<Object> getAllWithdrawalsBuilder(HttpStatus httpStatus, Iterable<?> object){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("code", httpStatus.value());
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> putWithdrawalBuilder(HttpStatus httpStatus){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("code", httpStatus.value());
        response.put("message", "Accepted withdrawal modification");
        return new ResponseEntity<>(response, httpStatus);
    }
}
