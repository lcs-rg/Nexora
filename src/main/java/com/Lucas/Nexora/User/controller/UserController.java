package com.Lucas.Nexora.User.controller;

import com.Lucas.Nexora.User.dto.UserRequestDTO;
import com.Lucas.Nexora.User.dto.UserResponseDTO;
import com.Lucas.Nexora.User.dto.UserUpdateDTO;
import com.Lucas.Nexora.User.entity.User;
import com.Lucas.Nexora.User.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto){
        UserResponseDTO responseDTO = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<UserResponseDTO> response = userService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id){
       UserResponseDTO dto =  userService.findById(id);
       return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO dto){
        UserResponseDTO responseDTO = userService.update(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
