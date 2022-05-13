package com.returners.movies.util;

import com.returners.movies.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<Response> getSuccessResponse(Object data, String message) {
        Response response = new Response();
        if (message != null) {
            response.setMessage(message);
        }
        response.setStatus("SUCCESS");
        response.setData(data);

        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Response> getErrorResponse(HttpStatus status, Object data, String message) {
        Response response = new Response();
        if (message != null) {
            response.setMessage(message);
        }
        response.setStatus("ERROR");
        response.setData(data);

        return ResponseEntity
                .status(status != null ?status : HttpStatus.OK)
                .body(response);
    }
}
