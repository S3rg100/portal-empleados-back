package com.example.retail.employee_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.employee_backend.model.Empleado;
import com.example.retail.employee_backend.repository.EmpleadoRepository;

@RestController
@RequestMapping("/test")
public class TestDataController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public List<Empleado> getEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/test-role")
    public String testRole(Authentication authentication) {
        return authentication.getAuthorities().toString();
    }
}
