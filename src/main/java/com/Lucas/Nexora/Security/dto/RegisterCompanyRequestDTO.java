package com.Lucas.Nexora.Security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterCompanyRequestDTO(

        // Dados da empresa
        @NotBlank(message = "O nome da empresa é obrigatório.")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
        String companyName,

        @NotBlank(message = "O CNPJ é obrigatório.")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido. Use o formato: XX.XXX.XXX/XXXX-XX")
        String cnpj,

        @NotBlank(message = "O slug é obrigatório.")
        @Size(max = 100, message = "O slug deve ter no máximo 100 caracteres.")
        @Pattern(regexp = "^[a-z0-9-]+$", message = "O slug deve conter apenas letras minúsculas, números e hífens.")
        String slug,

        // Dados do admin
        @NotBlank(message = "O nome do administrador é obrigatório.")
        String adminName,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "Email inválido.")
        String adminEmail,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        String adminPassword
) {}