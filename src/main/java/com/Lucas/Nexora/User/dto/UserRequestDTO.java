package com.Lucas.Nexora.User.dto;

import com.Lucas.Nexora.User.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserRequestDTO(

        @NotBlank(message = "O nome é obrigatório.")
        String name,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "Email inválido.")
        String email,

        @NotNull(message = "O cargo é obrigatório.")
        Role role,

        @NotNull(message = "O ID da empresa é obrigatório.")
        UUID companyId

) {}