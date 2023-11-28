package com.fab5.bankingapp.response;

import com.fab5.bankingapp.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountResponse {

    public static ResponseEntity<Object> createdAccountBuilder(HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully created the account");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAccountBuilder(HttpStatus httpStatus, Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAllAccountsBuilder(HttpStatus httpStatus, List<?> object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> deleteAccountBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
       /* if (account.isPresent()) {
            response.put("message", "Account has been successfully deleted");
        } else {
            response.put("message", "Account does not exist");
        }
        response.put("HttpStatus Code", httpStatus);*/
        response.put("message", "Account has been successfully deleted");
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> putAccountBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus code", httpStatus);
        response.put("message", "Accepted account modification");
        return new ResponseEntity<>(response, httpStatus);
    }
}
