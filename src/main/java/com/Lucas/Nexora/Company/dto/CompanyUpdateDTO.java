package com.Lucas.Nexora.Company.dto;

import com.Lucas.Nexora.Company.enums.PlanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyUpdateDTO(

        @NotBlank(message = "O nome da empresa é obrigatório.")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
        String name,

        // CNPJ não pode ser alterado após o cadastro

        @NotBlank(message = "O slug é obrigatório.")
        @Size(max = 100, message = "O slug deve ter no máximo 100 caracteres.")
        @Pattern(regexp = "^[a-z0-9-]+$", message = "O slug deve conter apenas letras minúsculas, números e hífens.")
        String slug,

        @NotNull(message = "O plano é obrigatório.")
        PlanType plan
) {}