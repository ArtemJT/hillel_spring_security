package com.example.hw_31_spring_security.services;

import com.example.hw_31_spring_security.dto.UserInfoDto;
import com.example.hw_31_spring_security.model.UserInfo;
import com.example.hw_31_spring_security.repository.UserInfoRepository;
import com.example.hw_31_spring_security.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Artem Kovalov on 05.02.2023
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserInfoDto userInfoDto) throws EntityExistsException {
        UserInfo userInfo = Mapper.toEntity(userInfoDto, UserInfo.class);
        userInfo.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        userInfoRepository.save(userInfo);
        userInfoDto.setId(userInfo.getId());
    }

    public List<UserInfoDto> findAllUsers() {
        Stream<UserInfo> stream = StreamSupport.stream(userInfoRepository.findAll().spliterator(), false);
        List<UserInfo> userInfoList = stream.collect(Collectors.toList());
        return Mapper.allToDto(userInfoList, UserInfoDto.class);
    }

    public boolean isUserNameExists(String name) {
        return userInfoRepository.existsUserInfoByName(name);
    }
}
