package com.fab5.bankingapp.response;

import com.fab5.bankingapp.model.AccountActivity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountActivityResponse {



    public static ResponseEntity<Object> getActivityBuilder(HttpStatus httpStatus, Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }




}
