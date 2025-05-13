package com.example.retail.employee_backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired 
    private JWTAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)

            // Permitir uso del H2 console (opcional, solo en desarrollo)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))

            // Configuración de rutas y acceso
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas
                .requestMatchers("/login").permitAll()
                .requestMatchers("/login-empleado").permitAll()
                .requestMatchers("/test/**").permitAll()
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/producto/**").hasAuthority("ADMIN")

                // Rutas protegidas por rol
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/empleado/**").hasAnyAuthority("ADMIN", "EMPLEADO")

                // Todas las demás requieren autenticación
                .anyRequest().authenticated()
            )

            .sessionManagement(sess -> sess.sessionCreationPolicy(
                org.springframework.security.config.http.SessionCreationPolicy.STATELESS))

            .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))

            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
