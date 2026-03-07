package com.Lucas.Nexora.Security.dto;

public record AuthResponseDTO(
        String accessToken,
        String refreshToken
) {}