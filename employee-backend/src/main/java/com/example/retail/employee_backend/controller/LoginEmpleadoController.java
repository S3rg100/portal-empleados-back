package com.example.retail.employee_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginEmpleadoController {

    @GetMapping("/login-empleado")
    public String showLoginPage() {
        return "login_empleado";
    }

}
