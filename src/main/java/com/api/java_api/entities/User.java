package com.api.java_api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Un algo en base de datos
@Table // se define como tabla de base de datos
@Builder // constructor de objetos
@Data // getter y setters toEquals, toHash, toString
@NoArgsConstructor // agrega el constructor vacio
@AllArgsConstructor // agrega el constructor completo

public class User {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column()
    private String nombre;
    private int edad;

}
