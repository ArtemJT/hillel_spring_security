package com.example.hw_31_spring_security.dto;

import com.example.hw_31_spring_security.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String name;

    private String password;

    private UserRole role;
}
