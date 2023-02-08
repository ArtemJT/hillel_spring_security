package com.example.hw_31_spring_security.repository;

import com.example.hw_31_spring_security.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

    Optional<UserInfo> findUserInfoByName(String name);
    boolean existsUserInfoByName(String name);
}
