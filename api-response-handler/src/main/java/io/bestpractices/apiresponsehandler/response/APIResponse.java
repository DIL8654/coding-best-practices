package io.bestpractices.apiresponsehandler.response;

import io.bestpractices.apiresponsehandler.constants.UserConstants;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author dilankam date 03/12/2023
 *     <p>a human-readable message, an internal code for error identification, and the payload.
 *     Represents the response of an API operation, encapsulating status, HTTP status code,
 * @param <T> The type of data to be included in the response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse<T> {

  /** The status of the API response, indicating success or failure. */
  private String status;

  /** The HTTP status code associated with the API response. */
  private Integer httpStatus;

  /** A human-readable message providing additional information about the API response. */
  private String message;

  /**
   * An internal code or identifier for the API response, used for error identification by
   * stakeholders utilizing these APIs. This represents what is happening in the core-responsible
   * module, e.g., whether it is handled or a generic response code that helps consumers to handle
   * it.
   */
  private String internalCode;

  /** The payload included in the API response, holding the actual content. */
  private T payload;

  /**
   * Builds an APIResponse for a successful operation.
   *
   * @param payload The payload to include in the response.
   * @param responseMap A map containing response messages.
   * @param internalCode The key corresponding to the desired response message.
   * @param <T> The type of data to be included in the response.
   * @return An APIResponse indicating a successful operation.
   */
  public static <T> APIResponse<T> ok(
      T payload, Map<String, String> responseMap, String internalCode) {
    return APIResponse.<T>builder()
        .httpStatus(HttpStatus.OK.value())
        .status(UserConstants.SUCCESS)
        .message(responseMap.get(internalCode))
        .internalCode(internalCode)
        .payload(payload)
        .build();
  }

  /**
   * Builds an APIResponse for a bad request operation.
   *
   * @param payload The payload to include in the response.
   * @param responseMap A map containing response messages.
   * @param internalCode The key corresponding to the desired response message.
   * @param <T> The type of data to be included in the response.
   * @return An APIResponse indicating a failed operation.
   */
  public static <T> APIResponse<T> badRequest(
      T payload, Map<String, String> responseMap, String internalCode) {
    return APIResponse.<T>builder()
        .httpStatus(HttpStatus.BAD_REQUEST.value())
        .status(UserConstants.FAILURE)
        .message(responseMap.get(internalCode))
        .internalCode(internalCode)
        .payload(payload)
        .build();
  }

  /**
   * Builds an APIResponse for a not found operation.
   *
   * @param payload The payload to include in the response.
   * @param responseMap A map containing response messages.
   * @param internalCode The key corresponding to the desired response message.
   * @param <T> The type of data to be included in the response.
   * @return An APIResponse indicating a failed operation.
   */
  public static <T> APIResponse<T> notFound(
      T payload, Map<String, String> responseMap, String internalCode) {
    return APIResponse.<T>builder()
        .httpStatus(HttpStatus.NOT_FOUND.value())
        .status(UserConstants.FAILURE)
        .message(responseMap.get(internalCode))
        .internalCode(internalCode)
        .payload(payload)
        .build();
  }

  /**
   * Builds an APIResponse for an internal server error.
   *
   * @param payload The payload to include in the response.
   * @param responseMap A map containing response messages.
   * @param internalCode The key corresponding to the desired response message.
   * @param <T> The type of data to be included in the response.
   * @return An APIResponse indicating a failed operation.
   */
  public static <T> APIResponse<T> internalServerError(
      T payload, Map<String, String> responseMap, String internalCode) {
    return APIResponse.<T>builder()
        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .status(UserConstants.FAILURE)
        .message(responseMap.get(internalCode))
        .internalCode(internalCode)
        .payload(payload)
        .build();
  }
}
