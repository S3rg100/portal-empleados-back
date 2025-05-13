package com.example.retail.employee_backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.retail.employee_backend.model.EmpleadoLogin;
import com.example.retail.employee_backend.repository.EmpleadoLoginRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private EmpleadoLoginRepository empleadoLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmpleadoLogin login = empleadoLoginRepository.findByUsername(username);

        if (login == null)
            throw new UsernameNotFoundException(username + " no encontrado");

        String rol = login.getEmpleado().getRol(); // ejemplo: "ADMIN"

        return User.builder()
            .username(login.getUsername())
            .password(login.getPassword())
            .authorities(new SimpleGrantedAuthority(rol))
            .build();
    }
}
