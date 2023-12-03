package io.bestpractices.apiresponsehandler.repository;

import io.bestpractices.apiresponsehandler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dilankam date 03/12/2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {}
