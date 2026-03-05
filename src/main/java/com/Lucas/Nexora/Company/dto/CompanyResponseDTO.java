package com.Lucas.Nexora.Company.dto;

import com.Lucas.Nexora.Company.enums.CompanyStatus;
import com.Lucas.Nexora.Company.enums.PlanType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyResponseDTO(

        UUID id,
        String name,
        String cnpj,
        String slug,
        CompanyStatus status,
        PlanType plan,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}