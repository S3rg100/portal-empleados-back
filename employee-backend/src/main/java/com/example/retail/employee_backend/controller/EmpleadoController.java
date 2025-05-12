package com.example.retail.employee_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.employee_backend.model.Empleado;
import com.example.retail.employee_backend.service.EmpleadoService;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping(("/findAll"))
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @PostMapping("/add")
    public Empleado createEmpleado(@RequestBody Empleado empleado) {
        empleadoService.save(empleado);
        // CAMBIAR ESTE RETORNO A UNA REDIRECCIÓN
        return empleado;
    }
}
