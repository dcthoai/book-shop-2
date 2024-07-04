package com.springmvc.model;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseJSON {
    private static final JSONObject response = new JSONObject();

    public static ResponseEntity<?> ok(String message){
        response.put("success", true);
        response.put("message", message);
        return ResponseEntity.ok().body(response.toString());
    }

    public static ResponseEntity<?> badRequest(String message){
        response.put("success", false);
        response.put("message", message);
        return ResponseEntity.badRequest().body(response.toString());
    }

    public static ResponseEntity<?> notFound(String message){
        response.put("success", false);
        response.put("message", message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.toString());
    }

    public static ResponseEntity<?> serverError(String message){
        response.put("success", false);
        response.put("message", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.toString());
    }

    public static ResponseEntity<?> unAuthorized(String message){
        response.put("success", false);
        response.put("message", message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.toString());
    }

    public static ResponseEntity<?> accessDenied(String message){
        response.put("success", false);
        response.put("message", message);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response.toString());
    }

    public static String toJson(Boolean status, String message) {
        response.put("success", status);
        response.put("message", message);
        return response.toString();
    }
}
