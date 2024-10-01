package com.api.java_api.controllers;

import com.api.java_api.entities.User;
import com.api.java_api.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    //Servicio -> logica de negocio -> se comunica con el Controlador con la logica de negocio

    private final UserService userService;

    @GetMapping("/test")
    public String nombregenerico(){
        return "HolaMundo";
    }

    @PostMapping("test")
    public User poneralgo(@RequestBody User user){
        return userService.getName(user);
    }
}
