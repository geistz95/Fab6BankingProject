package com.fab5.bankingapp.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerResponse {

    public static ResponseEntity<Object> createdCustomerBuilder(HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully created the customer");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getCustomerBuilder(HttpStatus httpStatus, Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAllCustomersBuilder(HttpStatus httpStatus, List<?> object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> updateCustomerBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus code", httpStatus);
        response.put("message", "Accepted customer modification");
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> deleteCustomerBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "The customer has been successfully deleted");
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
}
