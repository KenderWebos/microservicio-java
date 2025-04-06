package com.api.java_api.controllers;

import com.api.java_api.dtos.UserRequest;
import com.api.java_api.dtos.UserResponse;
import com.api.java_api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class TestingController {

    @GetMapping("/holamundo")
    public String nombregenerico(){
        return "Hola Mundo!";
    }
}
