package com.fab5.bankingapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class P2PResponse {
    public static ResponseEntity<Object> createP2PBuilder(HttpStatus httpStatus, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message","Successfully created the transfer and added to the accounts");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus+" "+httpStatus.value());
        return new ResponseEntity<>(response,httpStatus);
    }

    public static  ResponseEntity<Object> getP2PBuilder(HttpStatus httpStatus, Object object){
        Map<String,Object> response  = new HashMap<>();
        response.put("HttpStatus Code", httpStatus+" "+httpStatus.value());
        response.put("data result", object);
        return new ResponseEntity<>(response,httpStatus);
    }

    public static ResponseEntity<Object> deleteP2PBuilder(HttpStatus httpStatus){
        Map<String,Object> response  = new HashMap<>();
        response.put("message", "The P2P Transfer has been successfully cancelled");
        response.put("HttpStatus Code", httpStatus + " " + httpStatus.value());
        return new ResponseEntity<>(response, httpStatus);
    }
}
