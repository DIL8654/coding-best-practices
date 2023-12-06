package io.bestpractices.apiresponsehandler.service;

import io.bestpractices.apiresponsehandler.constants.UserConstants;
import io.bestpractices.apiresponsehandler.dto.UserDto;
import io.bestpractices.apiresponsehandler.model.User;
import io.bestpractices.apiresponsehandler.repository.UserRepository;
import io.bestpractices.apiresponsehandler.response.APIResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author dilankam date 03/12/2023
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public APIResponse<UserDto> findUserById(String userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty()) {
      log.error("User with id {} not found", userId);
      return APIResponse.notFound(
          null,
          UserConstants.getUserResponseHashMap(),
          UserConstants.USER_RESPONSE_CODE_PREFIX.concat("1"));
    }

    User user = userOptional.get();

    UserDto userDTO =
        UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();

    return APIResponse.ok(
        userDTO,
        UserConstants.getUserResponseHashMap(),
        UserConstants.USER_RESPONSE_CODE_PREFIX.concat("6"));
  }

  public APIResponse<List<UserDto>> findAllUsers() {
    List<User> userList = userRepository.findAll();

    List<UserDto> userDTOList =
        userList.stream()
            .map(
                user ->
                    UserDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .build())
            .toList();

    return APIResponse.ok(
        userDTOList,
        UserConstants.getUserResponseHashMap(),
        UserConstants.USER_RESPONSE_CODE_PREFIX.concat("10"));
  }

  public APIResponse<UserDto> addUser(UserDto userDTO) {
    User user =
        User.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName()).build();

    user = userRepository.save(user);

    userDTO =
        UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();

    return APIResponse.ok(
        userDTO,
        UserConstants.getUserResponseHashMap(),
        UserConstants.USER_RESPONSE_CODE_PREFIX.concat("7"));
  }

  public APIResponse<UserDto> updateUser(UserDto userDTO) {
    Optional<User> userOptional = userRepository.findById(userDTO.getId());
    if (userOptional.isEmpty())
      return APIResponse.notFound(
          null,
          UserConstants.getUserResponseHashMap(),
          UserConstants.USER_RESPONSE_CODE_PREFIX.concat("1"));

    User user = userOptional.get();

    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());

    user = userRepository.save(user);

    userDTO =
        UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();

    return APIResponse.ok(
        userDTO,
        UserConstants.getUserResponseHashMap(),
        UserConstants.USER_RESPONSE_CODE_PREFIX.concat("8"));
  }

  public APIResponse<UserDto> deleteUserById(String userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty())
      return APIResponse.notFound(
          null,
          UserConstants.getUserResponseHashMap(),
          UserConstants.USER_RESPONSE_CODE_PREFIX.concat("1"));

    userRepository.deleteById(userId);

    return APIResponse.ok(
        null,
        UserConstants.getUserResponseHashMap(),
        UserConstants.USER_RESPONSE_CODE_PREFIX.concat("9"));
  }

  public APIResponse<Void> voidDeleteUserById(String userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty())
      return APIResponse.notFound(
          null,
          UserConstants.getUserResponseHashMap(),
          UserConstants.USER_RESPONSE_CODE_PREFIX.concat("1"));

    userRepository.deleteById(userId);
    return APIResponse.noContent();
  }

  public void voidDeleteUserById2(String userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    userRepository.deleteById(userId);
  }

  public APIResponse<Void> delete(String userId) {

    try {
      userRepository.deleteById(userId); // Replace this with your actual method
      return APIResponse.ok(
          null,
          UserConstants.getUserResponseHashMap(),
          UserConstants.USER_RESPONSE_CODE_PREFIX.concat("9"));
    } catch (Exception e) {
      log.error("Error deleting user: {}", e.getMessage());
      return APIResponse.internalServerError(
          null, UserConstants.getUserResponseHashMap(), UserConstants.GENERIC_RESPONSE_CODE);
    }
  }
}
