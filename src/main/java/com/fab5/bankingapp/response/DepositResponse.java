package com.fab5.bankingapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepositResponse {
    public static ResponseEntity<Object> createdDepositBuilder(HttpStatus httpStatus, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message","Successfully created the deposit and added to the account");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response,httpStatus);
    }
    public static  ResponseEntity<Object> getDepositBuilder(HttpStatus httpStatus, Object object){
        Map<String,Object> response  = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response,httpStatus);
    }
    public static ResponseEntity<Object> getAllDepositsBuilder(HttpStatus httpStatus, List<?> object){
        Map<String,Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }
    public static ResponseEntity<Object> deleteDepositBuilder(HttpStatus httpStatus){
        Map<String,Object> response  = new HashMap<>();
        response.put("message", "The deposit has been successfully deleted");
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
    public static ResponseEntity<Object> putDepositBuilder(HttpStatus httpStatus){
        Map<String,Object> response = new HashMap<>();
        response.put("HttpStatus code", httpStatus);
        response.put("message", "Accepted deposit modification");
        return new ResponseEntity<>(response, httpStatus);
    }
}
