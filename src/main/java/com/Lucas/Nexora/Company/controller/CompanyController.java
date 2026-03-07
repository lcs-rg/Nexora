package com.Lucas.Nexora.Company.controller;

import com.Lucas.Nexora.Company.dto.CompanyRequestDTO;
import com.Lucas.Nexora.Company.dto.CompanyResponseDTO;
import com.Lucas.Nexora.Company.dto.CompanyUpdateDTO;
import com.Lucas.Nexora.Company.enums.CompanyStatus;
import com.Lucas.Nexora.Company.repository.CompanyRepository;
import com.Lucas.Nexora.Company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> create(@RequestBody @Valid CompanyRequestDTO requestDTO){
        CompanyResponseDTO responseDTO = companyService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> findAll(CompanyResponseDTO companyResponseDTO){
        List<CompanyResponseDTO> responseDTOS = companyService.findAll();
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> findById(@PathVariable UUID id){
        CompanyResponseDTO responseDTO = companyService.findById(id);
        return ResponseEntity.ok(responseDTO);

        }
        @GetMapping("/cnpj/{cnpj}")
                public ResponseEntity<CompanyResponseDTO> findByCnpj(@PathVariable String cnpj) {
            CompanyResponseDTO responseDTO = companyService.findByCnpj(cnpj);
            return ResponseEntity.ok(responseDTO);
        }

        @GetMapping("/slug/{slug}")
    public ResponseEntity<CompanyResponseDTO> findBySlug(@PathVariable String slug){
            CompanyResponseDTO responseDTO = companyService.findBySlug(slug);
            return ResponseEntity.ok(responseDTO);
        }

@PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> update(@PathVariable UUID id, @RequestBody CompanyUpdateDTO dto){
        CompanyResponseDTO companyResponseDTO = companyService.update(id, dto);
        return ResponseEntity.ok(companyResponseDTO);
        }

        @PutMapping("/{id}/status")
    public ResponseEntity<CompanyResponseDTO> updateStatus(@PathVariable UUID id, @RequestBody CompanyStatus companyStatus){
        CompanyResponseDTO companyResponseDTO = companyService.updatedStatus(id, companyStatus);
        return ResponseEntity.ok(companyResponseDTO);
        }

@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        companyService.delete(id);
        return ResponseEntity.noContent().build();
}

}

