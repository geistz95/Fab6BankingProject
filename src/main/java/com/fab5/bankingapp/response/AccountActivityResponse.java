package com.fab5.bankingapp.response;

import com.fab5.bankingapp.model.AccountActivity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class AccountActivityResponse {



    public static ResponseEntity<Object> getActivityBuilder(HttpStatus httpStatus, Object object) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAllActivityBuilder(HttpStatus httpStatus, Object object){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }

}
