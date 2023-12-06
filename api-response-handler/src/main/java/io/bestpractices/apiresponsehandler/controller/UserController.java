package io.bestpractices.apiresponsehandler.controller;

import io.bestpractices.apiresponsehandler.dto.UserDto;
import io.bestpractices.apiresponsehandler.response.APIResponse;
import io.bestpractices.apiresponsehandler.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author dilankam date 03/12/2023
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<APIResponse<UserDto>> findUserById(@PathVariable String id) {
    APIResponse<UserDto> response = userService.findUserById(id);
    return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus())).body(response);
  }

  @GetMapping
  public ResponseEntity<APIResponse<List<UserDto>>> findAllUsers() {
    APIResponse<List<UserDto>> response = userService.findAllUsers();
    return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus())).body(response);
  }

  @PostMapping
  public ResponseEntity<APIResponse<UserDto>> addUser(@RequestBody UserDto userDTO) {
    APIResponse<UserDto> response = userService.addUser(userDTO);
    return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus())).body(response);
  }

  @PutMapping
  public ResponseEntity<APIResponse<UserDto>> updateUser(@RequestBody UserDto userDTO) {
    APIResponse<UserDto> response = userService.updateUser(userDTO);
    return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus())).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<APIResponse<UserDto>> deleteUser(@PathVariable String id) {
    APIResponse<UserDto> response = userService.deleteUserById(id);
    return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus())).body(response);
  }

  @DeleteMapping("/{id}/custom-void")
  public APIResponse<Void> voidDeleteUser(@PathVariable String id) {
    return userService.voidDeleteUserById(id);
  }

  @DeleteMapping("/{id}/standard-void")
  public ResponseEntity<Void> voidDeleteUser2(@PathVariable String id) {
    userService.deleteUserById(id);
    // Assuming successful deletion, return HTTP 204 No Content
    return ResponseEntity.noContent().build();
  }
}
