package com.returners.movies.util;

import com.returners.movies.model.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<DataResponse> getSuccessResponse(Object data, String message) {
        DataResponse dataResponse = new DataResponse();
        if (message != null) {
            dataResponse.setMessage(message);
        }
        dataResponse.setStatus("SUCCESS");
        dataResponse.setData(data);

        return ResponseEntity.ok(dataResponse);
    }

    public static ResponseEntity<DataResponse> getErrorResponse(HttpStatus status, Object data, String message) {
        DataResponse dataResponse = new DataResponse();
        if (message != null) {
            dataResponse.setMessage(message);
        }
        dataResponse.setStatus("ERROR");
        dataResponse.setData(data);

        return ResponseEntity
                .status(status != null ?status : HttpStatus.OK)
                .body(dataResponse);
    }
}
