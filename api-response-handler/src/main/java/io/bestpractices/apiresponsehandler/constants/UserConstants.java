package io.bestpractices.apiresponsehandler.constants;

import java.util.Map;

/**
 * @author dilankam date 03/12/2023
 */
public final class UserConstants {
  public static final String SUCCESS = "SUCCESS";
  public static final String FAILURE = "FAILURE";
  public static final String GENERIC_RESPONSE_CODE = "USER-GENERIC-ERROR";
  public static final String USER_RESPONSE_CODE_PREFIX = "USER-";

  public static Map<String, String> getUserResponseHashMap() {
    return Map.of(
        USER_RESPONSE_CODE_PREFIX + "1", "User not found",
        USER_RESPONSE_CODE_PREFIX + "2", "User already exists",
        USER_RESPONSE_CODE_PREFIX + "3", "User not created",
        USER_RESPONSE_CODE_PREFIX + "4", "User not updated",
        USER_RESPONSE_CODE_PREFIX + "5", "User not deleted",
        USER_RESPONSE_CODE_PREFIX + "6", "User found",
        USER_RESPONSE_CODE_PREFIX + "7", "User successfully created",
        USER_RESPONSE_CODE_PREFIX + "8", "User successfully updated",
        USER_RESPONSE_CODE_PREFIX + "9", "User successfully deleted",
        USER_RESPONSE_CODE_PREFIX + "10", "Users list found");
  }
}
