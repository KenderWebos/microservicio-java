package com.api.java_api.services;

import com.api.java_api.dtos.UserRequest;
import com.api.java_api.dtos.UserResponse;
import com.api.java_api.entities.User;
import com.api.java_api.exceptions.DuplicateResourceException;
import com.api.java_api.exceptions.ResourceNotFoundException;
import com.api.java_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        User savedUser = userRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .toList();
    }
    
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return mapToUserResponse(user);
    }
    
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Usuario no encontrado con email: " + email);
        }
        return mapToUserResponse(user);
    }
    
    public UserResponse updateUser(String id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
                
        // Verificar si el email ya existe y no pertenece a este usuario
        if (!existingUser.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("El email ya está registrado por otro usuario");
        }
        
        existingUser.setUsername(request.username());
        existingUser.setEmail(request.email());
        // Solo actualizar la contraseña si se proporcionó una nueva
        if (request.password() != null && !request.password().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(request.password()));
        }
        
        User updatedUser = userRepository.save(existingUser);
        return mapToUserResponse(updatedUser);
    }
    
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }
    
    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
            user.getId(), 
            user.getUsername(), 
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}