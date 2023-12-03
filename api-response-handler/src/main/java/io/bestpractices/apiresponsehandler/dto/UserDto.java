package io.bestpractices.apiresponsehandler.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author dilankam date 03/12/2023
 */
@Builder
@ToString
@Data
public class UserDto {

  private String id;
  private String firstName;
  private String lastName;
  private Instant dataOfBirth;
}
