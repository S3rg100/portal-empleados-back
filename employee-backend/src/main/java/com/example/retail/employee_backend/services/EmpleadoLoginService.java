package com.example.retail.employee_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retail.employee_backend.model.EmpleadoLogin;
import com.example.retail.employee_backend.repository.EmpleadoLoginRepository;

@Service
public class EmpleadoLoginService {
    @Autowired
    EmpleadoLoginRepository empleadoLoginRepository;

    public List<EmpleadoLogin> searchAllLogins() {
        return empleadoLoginRepository.findAll();
    }

    public EmpleadoLogin SearchByUsuario(String username) {
        return empleadoLoginRepository.findByUsername(username);
    }

    public void save(EmpleadoLogin login) {
        empleadoLoginRepository.save(login);
    }
}
