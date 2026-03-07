package com.Lucas.Nexora.User.service;

import com.Lucas.Nexora.Company.entity.Company;
import com.Lucas.Nexora.Company.repository.CompanyRepository;
import com.Lucas.Nexora.User.dto.UserRequestDTO;
import com.Lucas.Nexora.User.dto.UserResponseDTO;
import com.Lucas.Nexora.User.dto.UserUpdateDTO;
import com.Lucas.Nexora.User.entity.User;
import com.Lucas.Nexora.User.mapper.UserMapper;
import com.Lucas.Nexora.User.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       CompanyRepository companyRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.userMapper = userMapper;
    }


    @Transactional
    public UserResponseDTO create(UserRequestDTO dto) {
        Company company = companyRepository.findById(dto.companyId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com o ID: " + dto.companyId()));

        if (userRepository.existsByEmailAndCompanyId(dto.email(), dto.companyId())) {
            throw new IllegalArgumentException("Já existe um usuário com o email: " + dto.email() + " nesta empresa.");
        }

        User user = userMapper.toEntity(dto, company);
        User saved = userRepository.save(user);

        return userMapper.toResponseDTO(saved);
    }


    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllByCompany(UUID companyId) {
        return userRepository.findAllByCompanyId(companyId)
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(UUID id) {
        User user = getUserOrThrow(id);
        return userMapper.toResponseDTO(user);
    }


    @Transactional
    public UserResponseDTO update(UUID id, UserUpdateDTO dto) {
        User user = getUserOrThrow(id);

        boolean emailChanged = !user.getEmail().equals(dto.email());
        if (emailChanged && userRepository.existsByEmailAndCompanyId(dto.email(), user.getCompany().getId())) {
            throw new IllegalArgumentException("Já existe um usuário com o email: " + dto.email() + " nesta empresa.");
        }

        userMapper.updateEntityFromDTO(dto, user);
        User updated = userRepository.save(user);

        return userMapper.toResponseDTO(updated);
    }


    @Transactional
    public void delete(UUID id) {
        User user = getUserOrThrow(id);
        userRepository.delete(user);
    }


    private User getUserOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
    }
}