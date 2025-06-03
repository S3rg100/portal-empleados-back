package com.example.retail.employee_backend.services;

import org.springframework.http.ResponseEntity;

import com.example.retail.employee_backend.dto.DeliveryDataDTO;
import com.example.retail.employee_backend.dto.RetailDataDTO;

public interface IntegracionDeliveryService {

    void enviarPedido(DeliveryDataDTO dataDTO);

    ResponseEntity<RetailDataDTO> getPedido(String id);

}
