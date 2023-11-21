package com.fab5.bankingapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillResponse {
    public static ResponseEntity<Object> createdBillBuilder(HttpStatus httpStatus, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("message","Successfully created the bill and added to the account");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response,httpStatus);
    }
    public static  ResponseEntity<Object> getBillBuilder(HttpStatus httpStatus, Object object){
        Map<String,Object> response  = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response,httpStatus);
    }
    public static ResponseEntity<Object> getAllBillBuilder(HttpStatus httpStatus, List<?> object){
        Map<String,Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }
    public static ResponseEntity<Object> deleteBillBuilder(HttpStatus httpStatus){
        Map<String,Object> response  = new HashMap<>();
        response.put("message", "The bill has been successfully deleted");
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
    public static ResponseEntity<Object> putBillBuilder(HttpStatus httpStatus){
        Map<String,Object> response = new HashMap<>();
        response.put("HttpStatus code", httpStatus);
        response.put("message", "Accepted bill modification");
        return new ResponseEntity<>(response, httpStatus);
    }
}
