package com.example.retail.employee_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.employee_backend.model.Empleado;
import com.example.retail.employee_backend.repository.EmpleadoRepository;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @PostMapping
    public Empleado createEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }
}
