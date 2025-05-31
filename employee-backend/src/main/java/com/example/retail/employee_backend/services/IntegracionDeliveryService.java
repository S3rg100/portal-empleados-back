package com.example.retail.employee_backend.services;

import com.example.retail.employee_backend.dto.DeliveryDataDTO;

public interface IntegracionDeliveryService {

    void enviarPedido(DeliveryDataDTO dataDTO);

}
