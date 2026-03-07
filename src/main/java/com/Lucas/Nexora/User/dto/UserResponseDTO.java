package com.Lucas.Nexora.User.dto;

import com.Lucas.Nexora.User.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(

        UUID id,
        String name,
        String email,
        Role role,
        UUID companyId,
        String companyName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}