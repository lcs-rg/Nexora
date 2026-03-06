package com.Lucas.Nexora.Company.service;

import com.Lucas.Nexora.Company.dto.CompanyRequestDTO;
import com.Lucas.Nexora.Company.dto.CompanyResponseDTO;
import com.Lucas.Nexora.Company.dto.CompanyUpdateDTO;
import com.Lucas.Nexora.Company.entity.Company;
import com.Lucas.Nexora.Company.enums.CompanyStatus;
import com.Lucas.Nexora.Company.mapper.CompanyMapper;
import com.Lucas.Nexora.Company.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper){
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public CompanyResponseDTO create(CompanyRequestDTO requestDTO){
        if (companyRepository.existsByCnpj(requestDTO.cnpj())){
            throw new IllegalArgumentException("Já existe uma empresa cadastrada com o CNPJ: " + requestDTO.cnpj());
        }

        if (companyRepository.existsBySlug(requestDTO.slug())){
            throw new IllegalArgumentException("Já existe uma empresa cadastrada com o Slug: " + requestDTO.slug());
        }

        Company company = companyMapper.toEntity(requestDTO);
        Company saved = companyRepository.save(company);

        return companyMapper.toResponseDTO(saved);

    }

    @Transactional()
    public List<CompanyResponseDTO> findAll(){
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponseDTO)
                .toList();
    }

@Transactional
public CompanyResponseDTO findById(UUID id){
        Company company = getCompanyOrThrow(id);
        return companyMapper.toResponseDTO(company);
}

@Transactional
public CompanyResponseDTO findByCnpj(String cnpj){
        Company company = companyRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com o CNPJ: " + cnpj));
        return companyMapper.toResponseDTO(company);
}

@Transactional
public CompanyResponseDTO findBySlug(String slug){
        Company company = companyRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com o Slug: " + slug));
        return companyMapper.toResponseDTO(company);
}

@Transactional
public CompanyResponseDTO update(UUID id, CompanyUpdateDTO updateDTO){
        Company company = getCompanyOrThrow(id);
        if (!company.getSlug().equals(updateDTO.slug()) && companyRepository.existsBySlug(updateDTO.slug())){
            throw new IllegalArgumentException("Já existe uma empresa cadastrada com o Slug: " + updateDTO.slug());
        }

        companyMapper.updateEntityFromDTO(updateDTO, company);
        Company updated = companyRepository.save(company);

        return companyMapper.toResponseDTO(company);
}

@Transactional
public CompanyResponseDTO updatedStatus(UUID id, CompanyStatus status){
        Company company = getCompanyOrThrow(id);
        company.setStatus(status);
        Company updated = companyRepository.save(company);
        return companyMapper.toResponseDTO(updated);
}

@Transactional
public void delete(UUID id){
        Company company = getCompanyOrThrow(id);
        companyRepository.delete(company);
}

private Company getCompanyOrThrow(UUID id){
        return companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com o ID: " + id));
}
}
