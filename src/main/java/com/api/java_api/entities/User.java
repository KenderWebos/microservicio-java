package com.api.java_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @NotEmpty
    @Size(max = 30, message = "debe tener maximo 30 caracteres")
    private String username;

    @Column
    @Email(message = "Email no valido")
    @NotEmpty
    private String email;

    @Column
    @NotEmpty
    private String password;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts; // Un usuario puede tener múltiples posts

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments; // Un usuario puede tener múltiples comentarios
}
