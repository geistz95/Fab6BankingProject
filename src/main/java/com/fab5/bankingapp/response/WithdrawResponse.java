package com.fab5.bankingapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WithdrawResponse {

    public static ResponseEntity<Object> createdWithdrawBuilder(HttpStatus httpStatus, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message","Successfully created the withdrawal and updated the account balance");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response,httpStatus);
    }
    public static  ResponseEntity<Object> getWithdrawBuilder(HttpStatus httpStatus, Object object){
        Map<String,Object> response  = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response,httpStatus);
    }
    public static ResponseEntity<Object> getAllWithdrawalsBuilder(HttpStatus httpStatus, Iterable<?> object){
        Map<String,Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }
    public static ResponseEntity<Object> deleteWithdrawalBuilder(HttpStatus httpStatus){
        Map<String,Object> response  = new HashMap<>();
        response.put("message", "The withdrawal has been successfully deleted");
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
    public static ResponseEntity<Object> putWithdrawalBuilder(HttpStatus httpStatus){
        Map<String,Object> response = new HashMap<>();
        response.put("HttpStatus code", httpStatus);
        response.put("message", "Accepted withdrawal modification");
        return new ResponseEntity<>(response, httpStatus);
    }
}
