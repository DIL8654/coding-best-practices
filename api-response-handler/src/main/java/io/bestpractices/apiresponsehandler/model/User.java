package io.bestpractices.apiresponsehandler.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author dilankam date 03/12/2023
 */
@Entity
@Table(name = "users")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "firstName", length = 1000, nullable = false)
  private String firstName;

  @Column(name = "lastName", length = 1000, nullable = false)
  private String lastName;
}
