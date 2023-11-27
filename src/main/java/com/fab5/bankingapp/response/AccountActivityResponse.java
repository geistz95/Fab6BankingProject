package com.fab5.bankingapp.response;

import com.fab5.bankingapp.model.AccountActivity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountActivityResponse {

    public static ResponseEntity<Object> createdActivityBuilder(HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully created the account activity");
        response.put("data result", responseObject);
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getActivityBuilder(HttpStatus httpStatus, Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data result", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> getAllActivitiesBuilder(HttpStatus httpStatus, List<?> object) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus Code", httpStatus);
        response.put("data", object);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> deleteActivityBuilder(HttpStatus httpStatus, Optional<AccountActivity> activity) {
        Map<String, Object> response = new HashMap<>();
        if (activity.isPresent()) {
            response.put("message", "Activity has been successfully deleted");
        } else {
            response.put("message", "Activity does not exist");
        }
        response.put("HttpStatus Code", httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> putActivityBuilder(HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("HttpStatus code", httpStatus);
        response.put("message", "Accepted activity modification");
        return new ResponseEntity<>(response, httpStatus);
    }
}
