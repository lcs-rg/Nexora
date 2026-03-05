package com.Lucas.Nexora.Company.repository;

import com.Lucas.Nexora.Company.entity.Company;
import com.Lucas.Nexora.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<User> findByEmailandCompanyId(String email, UUID companyId);
}
