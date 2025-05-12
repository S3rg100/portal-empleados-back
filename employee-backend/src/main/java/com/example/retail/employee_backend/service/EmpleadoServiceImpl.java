package com.example.retail.employee_backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retail.employee_backend.model.Empleado;
import com.example.retail.employee_backend.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado findById(UUID id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public void save(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

}
