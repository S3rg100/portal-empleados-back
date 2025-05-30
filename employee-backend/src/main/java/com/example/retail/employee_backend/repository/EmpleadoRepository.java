package com.example.retail.employee_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.retail.employee_backend.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, UUID> {

}
