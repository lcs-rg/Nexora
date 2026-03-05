package com.Lucas.Nexora.Company.mapper;

import com.Lucas.Nexora.Company.dto.CompanyRequestDTO;
import com.Lucas.Nexora.Company.dto.CompanyResponseDTO;
import com.Lucas.Nexora.Company.dto.CompanyUpdateDTO;
import com.Lucas.Nexora.Company.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequestDTO dto) {
        Company company = new Company();
        company.setName(dto.name());
        company.setCnpj(dto.cnpj());
        company.setSlug(dto.slug());
        company.setPlan(dto.plan());
        return company;
    }

    public CompanyResponseDTO toResponseDTO(Company company) {
        return new CompanyResponseDTO(
                company.getId(),
                company.getName(),
                company.getCnpj(),
                company.getSlug(),
                company.getStatus(),
                company.getPlan(),
                company.getCreatedAt(),
                company.getUpdatedAt()
        );
    }

    public void updateEntityFromDTO(CompanyUpdateDTO dto, Company company) {
        company.setName(dto.name());
        company.setSlug(dto.slug());
        company.setPlan(dto.plan());
    }
}