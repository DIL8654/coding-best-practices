package io.bestpractices.apiresponsehandler.exception;

import io.bestpractices.apiresponsehandler.constants.UserConstants;
import io.bestpractices.apiresponsehandler.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author dilankam date 03/12/2023
 */
@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<APIResponse<String>> handleCustomException(Exception ex) {
    APIResponse<String> apiResponse =
        APIResponse.<String>builder()
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .status(UserConstants.FAILURE)
            .message(ex.getMessage())
            .internalCode(UserConstants.GENERIC_RESPONSE_CODE)
            .payload(null)
            .build();

    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
