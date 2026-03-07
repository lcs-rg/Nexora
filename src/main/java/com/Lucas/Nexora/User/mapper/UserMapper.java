package com.Lucas.Nexora.User.mapper;

import com.Lucas.Nexora.Company.entity.Company;
import com.Lucas.Nexora.User.dto.UserRequestDTO;
import com.Lucas.Nexora.User.dto.UserResponseDTO;
import com.Lucas.Nexora.User.dto.UserUpdateDTO;
import com.Lucas.Nexora.User.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto, Company company) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setRole(dto.role());
        user.setCompany(company);
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCompany().getId(),
                user.getCompany().getName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public void updateEntityFromDTO(UserUpdateDTO dto, User user) {
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setRole(dto.role());
    }
}