package com.example.retail.employee_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.retail.employee_backend.model.EmpleadoLogin;

@Repository
public interface EmpleadoLoginRepository extends JpaRepository <EmpleadoLogin, UUID>{
    public EmpleadoLogin findByUsername(String username);
}
