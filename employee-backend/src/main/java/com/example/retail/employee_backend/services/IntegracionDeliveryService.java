package com.example.retail.employee_backend.services;

import org.springframework.http.ResponseEntity;

import com.example.retail.employee_backend.dto.DeliveryDataDTO;

public interface IntegracionDeliveryService {

    void enviarPedido(DeliveryDataDTO dataDTO);

    ResponseEntity<DeliveryDataDTO> getPedido(String id);

}
