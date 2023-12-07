package com.fab5.bankingapp.response;

import com.fab5.bankingapp.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class AccountResponse {

    public static ResponseEntity<Object> createdAccountBuilder(HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("HttpStatus Code", httpStatus.value());
        response.put("message", "Account created");
        response.put("data result", responseObject);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAccountBuilder(HttpStatus httpStatus, Object object) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("HttpStatus Code", httpStatus.value());
        response.put("message", "Fetching Account");
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAllAccountsBuilder(HttpStatus httpStatus, List<?> object) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("HttpStatus Code", httpStatus.value());
        response.put("message", "Success");
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> deleteAccountBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new LinkedHashMap<>();

       /* if (account.isPresent()) {
            response.put("message", "Account has been successfully deleted");
        } else {
            response.put("message", "Account does not exist");
        }
        response.put("HttpStatus Code", httpStatus);*/

        response.put("HttpStatus Code", httpStatus.value());
        response.put("message", "Account successfully deleted");
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> putAccountBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("HttpStatus code", httpStatus.value());
        response.put("message", "Customer Account Updated");
        return new ResponseEntity<>(response, httpStatus);
    }
}
