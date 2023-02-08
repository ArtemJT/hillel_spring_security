package com.example.hw_31_spring_security.repository;

import com.example.hw_31_spring_security.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Integer> {

    Optional<User> findFirstByName(String name);
}
