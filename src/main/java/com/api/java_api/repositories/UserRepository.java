package com.api.java_api.repositories;

import com.api.java_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //JPA

    //@Query("select * from Users")
    //List<User> findById(Long id); // modificar una funcion establecida JPA consulta SQL

    //@Query("select '*' from user")
    //List<User> funcionHuevos();
}
