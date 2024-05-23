package com.assigment.miniproject.Repository;

import com.assigment.miniproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<User,String> {
    Optional<User> findByName(String username);
}
