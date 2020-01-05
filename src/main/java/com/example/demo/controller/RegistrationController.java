package com.example.demo.controller;

import com.example.demo.form.LoginForm;
import com.example.demo.form.UserForm;
import com.example.demo.sevice.RegistrationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class RegistrationController {

    private final RegistrationServiceImpl registrationService;

    public RegistrationController(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserForm userForm){
        return ResponseEntity.ok(registrationService.registration(userForm));
    }
}
