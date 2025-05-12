package com.example.retail.employee_backend.service;

import java.util.List;
import java.util.UUID;

import com.example.retail.employee_backend.model.Empleado;

public interface EmpleadoService {
    Empleado findById(UUID id);

    List<Empleado> getAllEmpleados();

    void save(Empleado empleado);
}
