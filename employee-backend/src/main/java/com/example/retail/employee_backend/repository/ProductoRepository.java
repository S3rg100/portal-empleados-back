package com.example.retail.employee_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.retail.employee_backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {

}
